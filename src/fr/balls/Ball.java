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

import java.awt.Color;
import fr.glob.MyVector;

/**
 * Ball.
 * */
public class Ball {

    /** The position vector. */
    private MyVector position;

    /** The speed vector. */
    private MyVector vitesse;

    /** The color of the ball. */
    private Color c;

    /** The radius. */
    private int rayon;


    /** The second constructor of a Ball.
     * This time we use the speed.
     * @param position : the position of the ball
     * @param vitesse : the speed
     * @param rayon : the radium
     * @param c : the color
     * */
    public Ball(final MyVector position,
                final MyVector vitesse,
                final Color c,
                final int rayon) {
        this.position = position;
        this.vitesse = vitesse;
        this.c = c;
        this.rayon = rayon;
    }

    /** Update the ball position.
     * @param width : the width
     * @param height : the height
     * */
    public void update(final int width, final int height) {
        position.add(vitesse);
        if ((position.x > width - rayon) || (position.x < rayon)) {
            if (position.x > width - rayon) {
                position.x = width - rayon;
            }
            if (position.x < rayon) {
                position.x = rayon;
            }
            vitesse.x = -vitesse.x;
        }
        if ((position.y > height - rayon) || (position.y < rayon)) {
            if (position.y > height - rayon) {
                position.y = height - rayon;
            }

            if (position.y < rayon) {
                position.y = rayon;
            }
            vitesse.y = -vitesse.y;
        }
    }

    /** Get the position.
     * @return the position of the ball.
     * */
    public MyVector getPosition() {
        return this.position;
    }

    /** Get the speed.
     * @return the speed of the ball.
     * */
    public MyVector getvitesse() {
        return this.vitesse;
    }

    /** Get the color.
     * @return the color of the ball.
     * */
    public Color getColor() {
        return this.c;
    }

    /** Get the radius.
     * @return the radius of the ball.
     * */
    public int getRayon() {
        return this.rayon;
    }

    /** Override the toString method.
     * @return A string representing a ball.
     * */
    @Override
    public String toString() {
        return "{"
            + " position='" + position + "'"
            + ", vitesse='" + vitesse + "'"
            + ", c='" + c + "'"
            + ", rayon='" + rayon + "'"
            + "}";
    }

}
