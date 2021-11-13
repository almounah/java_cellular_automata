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

import java.util.Iterator;
import java.util.LinkedList;
import fr.glob.MyVector;
import java.util.Random;
import java.awt.Color;

/** AgentsBoids.
 */
public class AgentsBoids {

    /** The list of boids. */
    private LinkedList<AgentBoids> agentsList;

    /** The copy of list of boids. */
    private LinkedList<AgentBoids> copy;

    /** The width. */
    private int w;

    /** The height. */
    private int h;

    /** The color of boids. */
    public static Color[] colors = {Color.RED};

    /** The constructor of the many Boids.
     * @param nbToAdd : the number of boids
     * @param w : the width
     * @param h : the height
     * */
    public AgentsBoids(final int nbToAdd, final int w, final int h) {
        agentsList = new LinkedList<AgentBoids>();
        copy = new LinkedList<AgentBoids>();
        this.w = w;
        this.h = h;
        addRandomAgents(nbToAdd);
    }

    /** Get the list of boids.
     * @return the list.
     * */
    public LinkedList<AgentBoids> getAgents() {
        return this.agentsList;
    }

    /** Get the width.
     * @return the width.
     * */
    public int getWidth() {
        return this.w;
    }

    /** Get the height.
     * @return the height.
     * */
    public int getHeight() {
        return this.h;
    }

    /** Restitute the initial list saved in the copy. */
    public void reInit() {
        if (agentsList.size() != copy.size()) {
            throw new RuntimeException("reInit : Listes de taille différentes!");
        }
        Iterator<AgentBoids> itL = agentsList.iterator();
        Iterator<AgentBoids> itC = copy.iterator();
        AgentBoids a;
        AgentBoids c;

        while (itL.hasNext() && itC.hasNext()) {
            a = itL.next();
            c = itC.next();
            a.getPosition().setXY(c.getPosition());
            a.getVitesse().setXY(c.getVitesse());
            a.getAcceleration().setXY(c.getAcceleration());
        }
    }


    /** Add random agent boids to the list.
     * @param nbToAdd : the number of boids to add. */
    public void addRandomAgents(final int nbToAdd) {
        Random r = new Random();
        Color c;
        int x;
        int y;
        int dx;
        int dy;
        int maxSpeed = (int) AgentBoids.vitMax;
        int radius = 5;
        //int radiusMin = 10, radiusMax = 15;
        for (int i = 0; i < nbToAdd; i++) {
            //radius = r.nextInt(radiusMax-radiusMin) + radiusMin;
            // Intervalle [radius; w-radius]
            x = r.nextInt(w - radius * 2) + radius;
            y = r.nextInt(h - radius * 2) + radius;
            //soustraction pour centrer l'intervalle en 0
            dx = r.nextInt(maxSpeed) - maxSpeed / 2;
            dy = r.nextInt(maxSpeed) - maxSpeed / 2;
            c = colors[i % colors.length];
            addAgent(new MyVector(x, y), new MyVector(dx, dy), radius, c);
        }
    }


    /** Add one boids agent to the list.
     * @param position : the position of the boids
     * @param rayon : the radius of the boids
     * @param c : the color of the boids
     * */
    public void addAgent(final MyVector position,
                         final int rayon,
                         final Color c) {
        agentsList.add(new AgentBoids(position, rayon, c));
        //Copie de position necessaire
        copy.add(new AgentBoids(new MyVector(position), rayon, c));
    }

    /** Add one boids agent to the list but with speed.
     * @param position : the position of the boids
     * @param rayon : the radius of the boids
     * @param c : the color of the boids
     * @param vitesse : the speed of the boids
     * */
    public void addAgent(final MyVector position,
                         final MyVector vitesse,
                         final int rayon,
                         final Color c) {
        agentsList.add(new AgentBoids(position, vitesse, rayon, c));
        copy.add(new AgentBoids(new MyVector(position),
                                new MyVector(vitesse),
                                rayon,
                                c)); //Copie de position necessaire
    }

    /** Update the list of boids Agents. */
    public void update() {
        for (AgentBoids a : agentsList) {
            a.update(w, h, agentsList);
        }
    }
}
