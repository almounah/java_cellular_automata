/**
 * This module is about simulating Balls.
 * It consists of three classes:
 *  <ul>
 *   <li>Ball : the part responsible for doing calculation for one ball</li>
 *   <li>Balls : for the calculation for many balls</li>
 *   <li>BallSimulator : for drawing </li>
 *  </ul>
 *
 */

package fr.balls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Color;
import fr.glob.MyVector;

/**
 * Balls.
 *   <li>Balls : for the calculation for many balls</li>
 * */
public class Balls {
    /** The list of the balls. */
    private ArrayList<Ball> ballsList;

    /** The copy of the list of the balls. */
    private ArrayList<Ball> ballsCopy;

    /** The width. */
    private int w;

    /** The height. */
    private int h;

    /** The constructor for the balls.
     * @param nbToAdd : the number of balls
     * @param w : width
     * @param h : height
     * @param useRedBlueBalls : tells us if we use red
     *                          and blue balls for the simulation
     *                          using events.
     * */
    public Balls(final int nbToAdd,
                 final int w,
                 final int h,
                 final boolean useRedBlueBalls) {
        ballsList = new ArrayList<Ball>();
        ballsCopy = new ArrayList<Ball>();
        this.w = w;
        this.h = h;
        if (useRedBlueBalls) {
            addBlueRedBalls(nbToAdd);
        } else {
            addRandomBalls(nbToAdd);
        }
    }


    /** The constructor for the balls.
     * @param nbToAdd : the number of balls
     * @param w : width
     * @param h : height
     */
    public Balls(final int nbToAdd,
                 final int w,
                 final int h) {
        ballsList = new ArrayList<Ball>();
        ballsCopy = new ArrayList<Ball>();
        this.w = w;
        this.h = h;
        addRandomBalls(nbToAdd);
    }

    /** Get the ball list.
     *  @return the ball list.
     * */
    public ArrayList<Ball> getballsList() {
        return ballsList;
    }


    /** Reinitialize the balls.
     *  used when the debut is pressed.
     * */
    public void reInit() {
        if (ballsCopy.size() != ballsList.size()) {
            throw new RuntimeException("reInit : Listes de taille différentes!");
        }
        Iterator<Ball> itL = ballsList.iterator();
        Iterator<Ball> itC = ballsCopy.iterator();
        Ball b;
        Ball bc;
        MyVector v;
        MyVector vc;
        while (itL.hasNext() && itC.hasNext()) {
            b = itL.next();
            bc = itC.next();
            v = b.getPosition();
            vc = bc.getPosition();
            v.x = vc.x; v.y = vc.y; //Reset pos
            v = b.getvitesse();
            vc = bc.getvitesse();
            v.x = vc.x; v.y = vc.y; //Reset velocité
        }
    }

    /** Add blue and red balls used to demonstrate the events.
     *  @param nbToAdd : the number of balls to add.
     *
     * */
    public void addBlueRedBalls(final int nbToAdd) {
        Random r = new Random();
        int x;
        int y;
        int dx;
        int dy;
        int radius;
        int maxSpeed = (h + w) / 100; // moyenne/50
        int radiusRed = 20;
        int radiusBlue = 40;
        Color c;
        for (int i = 0; i < nbToAdd; i++) {
            boolean isRed = (i % 2 == 0);
            radius = isRed ? radiusRed : radiusBlue;
            x = r.nextInt(w - radius) + radius; // Intervalle [radius; w-radius]
            y = r.nextInt(h - radius) + radius;
            MyVector pos = new MyVector(x, y);

            //soustraction pour centrer l'intervalle en 0
            dx =  r.nextInt(maxSpeed) - maxSpeed / 2;
            dy = r.nextInt(maxSpeed) - maxSpeed / 2;
            MyVector vit = new MyVector(dx, dy);
            vit.normalize(); vit.mult((double) maxSpeed);
            c = isRed ? Color.RED : Color.BLUE;
            addBall(pos, vit, c, radius);
        }
    }

    /** Add random balls to the list.
     * @param nbToAdd : the number of balls to add.
     * */
    public void addRandomBalls(final int nbToAdd) {
        Random r = new Random();
        int x;
        int y;
        int dx;
        int dy;
        int radius;
        Color c;
        int maxSpeed = (h + w) / 100; // moyenne/50
        int radiusMin = 20;
        int radiusMax = 30;
        Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
            Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK};

        for (int i = 0; i < nbToAdd; i++) {
            radius = r.nextInt(radiusMax - radiusMin) + radiusMin;
            x = r.nextInt(w - radius) + radius; // Intervalle [radius; w-radius]
            y = r.nextInt(h - radius) + radius;

            //soustraction pour centrer l'intervalle en 0
            dx =  r.nextInt(maxSpeed) - maxSpeed / 2;
            dy = r.nextInt(maxSpeed) - maxSpeed / 2;
            c = colors[i % colors.length];
            addBall(new MyVector(x, y), new MyVector(dx, dy), c, radius);
        }
    }

    /** Add a single ball to the list of the balls.
     * @param position : the position of the ball
     * @param vitesse : the speed of the ball
     * @param c : the ball color
     * @param rayon : the radius of the ball
     */
    public void addBall(final MyVector position,
                        final MyVector vitesse,
                        final Color c,
                        final int rayon) {
        ballsList.add(new Ball(position, vitesse, c, rayon));
        ballsCopy.add(new Ball(new MyVector(position),
                               new MyVector(vitesse),
                               c, rayon));
    }

    /** Update the list of the balls acoording to their speed. */
    public void update() {
        for (Ball b : ballsList) {
            b.update(w, h);
        }
    }

    /** Get the width.
     * @return the width of the screen.*/
    public int getWidth() {
        return this.w;
    }

    /** Get the height.
     * @return the height of the screen.*/
    public int getHeight() {
        return this.h;
    }

    /** Return the string of a ball.
     * @return the string representing a ball.*/
    public String toStringCP() {
        String str = "BallsCopy (" + this.ballsList.size() + "): ";
        for (Ball b : ballsCopy) {
            str += "[" + b.toString() + "]";
        }
        return str;
    }

    /** Return the string of a ball list.
     * @return the string representing a ball list.*/
    @Override
    public String toString() {
        String str = "BallsList (" + this.ballsList.size() + "): ";
        for (Ball b : ballsList) {
            str += "[" + b.toString() + "]";
        }
        return str;
    }

}
