/** This module is used for events in
 *  Balls simulation.
 *  Either to move all balls or just the blue
 *  balls or just the red balls.
 * */
package fr.glob.event.eventBalls;

import java.awt.Color;
import fr.balls.*;
import fr.glob.event.*;

/** EventMoveRedBalls.
 */
public class EventMoveRedBalls extends EventMoveBalls {

    /** The speed of blue balls. */
    private static long moveRedBallsPeriod = 1;

    /** The 2nd Contructor with datetoplay.
     * @param eventManager
     * @param balls
     * @param dateToPlay
     * */
    public EventMoveRedBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager, balls);
    }

    /** The Contructor.
     * @param eventManager
     * @param balls
     * */
    public EventMoveRedBalls(EventManager eventManager, Balls balls) {
        super(0, eventManager, balls);
    }

    /** the execute method. */
    public void execute() {
        for (Ball b : balls.getballsList()) {
            if (b.getColor() == Color.RED) {
                b.update(balls.getWidth(), balls.getHeight());
            }
        }
        EventManager em = getEventManager();
        long nextDate = getDateToPlay() + moveRedBallsPeriod;
        em.addEvent(new EventMoveRedBalls(nextDate, em, balls));
    }

    /** The toString method.
     * @return a string representing the balls update
     *         with the event.
     */
    @Override
    public String toString() {
        return super.toString() + " move red Balls";
    }

}
