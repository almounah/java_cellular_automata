/** This module is used for events in
 *  Balls simulation.
 *  Either to move all balls or just the blue
 *  balls or just the red balls.
 * */
package fr.glob.event.eventBalls;

import java.awt.Color;
import fr.balls.*;
import fr.glob.event.*;

/** EventMoveBlueBalls.
 */
public class EventMoveBlueBalls extends EventMoveBalls {

    /** The speed of blue balls. */
    private static long moveBlueBallsPeriod = 3;

    /** The 2nd Contructor with datetoplay.
     * @param eventManager
     * @param balls
     * @param dateToPlay
     * */
    public EventMoveBlueBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager, balls);
    }

    /** The Contructor.
     * @param eventManager
     * @param balls
     * */
    public EventMoveBlueBalls(EventManager eventManager, Balls balls) {
        super(0, eventManager, balls);
    }

    /** the execute method. */
    public void execute() {
        for (Ball b : balls.getballsList()) {
            if (b.getColor() == Color.BLUE) {
                b.update(balls.getWidth(), balls.getHeight());
            }
        }
        EventManager em = getEventManager();
        long nextDate = getDateToPlay() + moveBlueBallsPeriod;
        em.addEvent(new EventMoveBlueBalls(nextDate, em, balls));
    }

    /** The toString method.
     * @return a string representing the balls update
     *         with the event.
     */
    @Override
    public String toString() {
        return super.toString() + " move blue Balls";
    }

}
