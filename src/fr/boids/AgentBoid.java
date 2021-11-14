/**
 * This module is about simulating boids.
 * It consists of two classes:
 *  <ul>
 *   <li>AgentBoids : the part responsible for doing calculation
 *                    for 1 boids </li>
 *   <li>AgentsBoids : the part responsible of doing calculations
 *                    for a list of boids.</li>
 *   <li>AgentsSim : for drawing </li>
 *  </ul>
 *
 */

package fr.boids;

import fr.glob.MyVector;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * AgentBoid.
 *   <li>AgentBoids : the part responsible for doing calculation
 *                    for 1 boids </li>
*/
public class AgentBoid {

    /** The position vector. */
    private MyVector position;

    /** The speed vector. */
    private MyVector vitesse;

    /** The acceleration vector. */
    private MyVector acceleration;

    /** The radius. */
    private int rayon;

    /** The mass of the boids. */
    private double masse = 30.0;

    /** The color of the boids.
     * Color is used also to make different groups of boids
     * A red boid will not folow a group of blue boids
     */
    private Color color;

    /** The length of the boids. */
    public double portee; //rayon

    /** The space of the boids. */
    public double espacePerso; // rayon

    /** The maximum speed. */
    static public double vitMax = 12.0;

    /** The maximum repulsion speed. */
    //Pour ne pas avoir d'agglomerat
    static public double vitRepultionMax = vitMax * 1.3;

    /** The maximum acceleration. */
    // Méga camion de 2 tonnes ou F1?
    static public double accelMax = vitMax / 2;

    /** The maximum angle. */
    //0-> voit rien, 90->demi cercle 180->vision complète
    static public double angleLimite = 110.0;

    /** The constructor of a Boids.
     * @param position : the position of the boids
     * @param rayon : the radium
     * @param c : the color
     * */
    public AgentBoid(final MyVector position, final int rayon, final Color c) {
        this(position, new MyVector(0, 0), rayon, c);
    }

    /** The second constructor of a Boids.
     * This time we use the speed.
     * @param position : the position of the boids
     * @param vitesse : the speed
     * @param rayon : the radium
     * @param c : the color
     * */
    public AgentBoid(final MyVector position,
                      final MyVector vitesse,
                      final int rayon,
                      final Color c) {
        this.position = position;
        this.vitesse = vitesse;
        this.acceleration = new MyVector(0, 0);
        this.rayon = rayon;
        this.color = c;
        portee = rayon + 70.0;
        espacePerso = portee * 0.8;
    }


    /** Apply the force to the acceleration.
     * @param force : the force we want to apply
     */
    public void appliquerForce(final MyVector force) {
        //Pour ne pas changer la variable force
        MyVector f = new MyVector(force);
        f.div(masse); // 2eme loi de Newton
        acceleration.add(f);
    }

    /** Let the boids join another one.
     * Reynolds steer force is used.
     * @param cible : the other boid position
     * */
    public void rejoindre(final MyVector cible) {
        //variation de pos = vitesse
        MyVector desired = MyVector.sub(cible, position);

        desired.normalize();
        desired.mult(vitMax); // Pour ne pas foncer à toutes berzingue

        //'steer force' de Reynolds
        MyVector steer = MyVector.sub(desired, vitesse);
        appliquerForce(steer);
    }

    /** Tells if a boid 'v' is visible to this agent.
     * @param dst : the distance between the boids.
     * @param v : the other boids.
     * @return True if is visible and False if not.
     * */
    private boolean isVisible(final AgentBoid v, final double dst) {
        if (dst > portee) {
            return false; //Trop loin
        }
        //dst-debut
        MyVector fromMeToV = MyVector.sub(v.getPosition(), this.getPosition());
        double angle = MyVector.angle(this.getVitesse(), fromMeToV);
        if (angle > angleLimite) {
            return false; // Trop derrière
        }
        return true;
    }

    /**Apply the 3 main forces by looping through neighboors.
     * @param agents the list of the boids.
     * */
    private void mouvementBoids(final LinkedList<AgentBoid> agents) {
        int nbVoisins = 0;
        int nbTropProche = 0;
        MyVector cohesion = new MyVector(0, 0);
        MyVector alignement = new MyVector(0, 0);
        MyVector separation = new MyVector(0, 0);
        Iterator<AgentBoid> it = agents.iterator();
        while (it.hasNext()) {
            AgentBoid v = it.next();
            double dst = position.dst(v.getPosition());
            if ((v != this) && isVisible(v, dst)) { //Si visible
                if (v.getColor() == this.getColor()) { //Si c'est un ami visible
                    nbVoisins++;
                    cohesion.add(v.getPosition());
                    alignement.add(v.getVitesse());
                }
                if (dst < espacePerso) { //Dans tout les cas si il est visible
                    MyVector diff = MyVector.sub(position, v.getPosition());
                    diff.normalize();
                    diff.div(dst);
                    separation.add(diff);
                    nbTropProche++;
                }
            }
        }
        if (nbVoisins > 0) {
            cohesion.div(nbVoisins);
            rejoindre(cohesion);

            alignement.div(nbVoisins);
            alignement.normalize();
            alignement.mult(vitMax);
            //'steer force' de Reynolds
            MyVector steer = MyVector.sub(alignement, vitesse);
            appliquerForce(steer);
        }
        if (nbTropProche > 0) {
            separation.div(nbTropProche);
            separation.normalize();
            separation.mult(vitRepultionMax);
            MyVector steer = MyVector.sub(separation, vitesse);
            appliquerForce(steer);
        }
    }

    /** Update the acceleration taking in consideration the bounding.
     * @param w : the width
     * @param h : the height
     * @param limiteBord : the limit distance from the bound.
     * */
    public void checkBounds(final int w,
                            final int h,
                            final int limiteBord) {
        int x = (int) position.x;
        int y = (int) position.y;
        if (x < rayon + limiteBord) {
            appliquerForce(new MyVector(vitMax, 0));
        }
        if (x > w - limiteBord - rayon) {
            appliquerForce(new MyVector(-vitMax, 0));
        }
        if (y < rayon + limiteBord) {
            appliquerForce(new MyVector(0, vitMax));
        }
        if (y > h - limiteBord - rayon) {
            appliquerForce(new MyVector(0, -vitMax));
        }

        if (x < 0) {
            position.x = 0;
        }
        if (y < 0) {
            position.y = 0;
        }
        if (x > w) {
            position.x = w - 1;
        }
        if (y > h) {
            position.y = h - 1;
        }
    }

    /** Update the boids position.
     * @param w : the width
     * @param h : the height
     * @param agents : the linked list of boids.
     * */
    public void update(final int w,
                       final int h,
                       final LinkedList<AgentBoid> agents) {
        checkBounds(w, h, (w + h) / 20); //limiteBord = moyenne/10
        mouvementBoids(agents);

        //On limite l'accel max après avoir appliqué toutes les forces
        acceleration.limit(accelMax);
        vitesse.add(acceleration);
        position.add(vitesse);

        //A la fin on multiplie par 0 (1ere loi Newton)
        acceleration.x = 0.0; acceleration.y = 0.0;
    }

    /** Get the position.
     * @return the position of the boids.
     * */
    public MyVector getPosition() {
        return this.position;
    }

    /** Get the speed.
     * @return the speed of the boids.
     * */
    public MyVector getVitesse() {
        return this.vitesse;
    }

    /** Get the acceleration.
     * @return the acceleration of the boids.
     * */
    public MyVector getAcceleration() {
        return this.acceleration;
    }

    /** Get the radius.
     * @return the radius of the boids.
     * */
    public int getRayon() {
        return this.rayon;
    }

    /** Get the color.
     * @return the color of the boids.
     * */
    public Color getColor() {
        return this.color;
    }

    /** Get the string of the color.
     * @return the color of the boids as a string.
     * */
    public String me() {
        return
            "Color:" + getColor();
    }


    /** Override the toString method.
     * @return A string representing a boids.
     * */
    @Override
    public String toString() {
        return
            "Agents = position=" + getPosition()
            + ", vitesse=" + getVitesse()
            + ", acceleration=" + getAcceleration();
    }
}
