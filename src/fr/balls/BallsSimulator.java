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

import gui.GUISimulator;
import gui.Oval;

import java.awt.Color;

import fr.glob.Simulateur;
import fr.glob.event.eventBalls.*;

/** BallSimulator.
 *   <li>BallSimulator : for drawing </li>
 */
public class BallsSimulator extends Simulateur {

    /** The list of the balls we want to simulate. */
    private Balls balls;

    /** The constructor of ball simulator.
     *  @param width
     *  @param height
     *  @param nbBalls The number of Balls
     *  @param win : where we want to Draw the Balls
     * */
    public BallsSimulator(final int width,
                          final int height,
                          final int nbBalls,
                          final GUISimulator win) {
        super(width, height, win);
        this.balls = new Balls(nbBalls, width, height, false);
        em.addEvent(new EventMoveBalls(this.em, this.balls));
        em.setInitialStatus();
    }

    /** The second constructor of ball simulator.
     *  It is used to demonstrate the event manager.
     *  @param width
     *  @param height
     *  @param nbBalls The number of Balls
     *  @param win : where we want to Draw the Balls
     *  @param useRedBlueBalls : tell us if we should use red and
     *                           blue balls for the events.
     * */
    public BallsSimulator(final int width,
                          final int height,
                          final int nbBalls,
                          final GUISimulator win,
                          final boolean useRedBlueBalls) {
        super(width, height, win);
        this.balls = new Balls(nbBalls, width, height, useRedBlueBalls);
        em.addEvent(new EventMoveRedBalls(this.em, balls));
        em.addEvent(new EventMoveBlueBalls(this.em, balls));
        em.setInitialStatus();
    }

    /** Draw all the balls in the list. */
    private void drawBalls() {
        for (Ball b : balls.getballsList()) {
            win.addGraphicalElement(new Oval((int) b.getPosition().x,
                                             (int) b.getPosition().y,
                                             b.getColor(),
                                             b.getColor(),
                                             b.getRayon() * 2));
        }
    }

    /** The next method when we press on suivant. */
    @Override
    public void next() {
        em.next();
        win.reset();
        drawEdge(Color.white);
        drawBalls();
    }

    /** The restart method when we press on debut. */
    @Override
    public void restart() {
        em.restart();
        balls.reInit();
        win.reset();
        drawEdge(Color.white);
        drawBalls();
    }

    /** The toString method.
     * @return the string representing the balls.
     */
    @Override
    public String toString() {
        return "{"
            + " balls='" + balls + "'"
            + "}";
    }

}
