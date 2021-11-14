/** This module is used for events in
 *  Balls simulation.
 *  Either to move all balls or just the blue
 *  balls or just the red balls.
 * */
package fr.glob.event.eventBalls;

import fr.balls.*;
import fr.glob.event.*;

/** EvenMoveBalls.
 *  Either to move all balls or just the blue
 *  balls or just the red balls.
 */
public class EventMoveBalls extends Event {

    /** The balls we want to update with event. */
    protected Balls balls;

    /** The Contructor.
     * @param eventManager
     * @param balls
     * */
    public EventMoveBalls(EventManager eventManager, Balls balls) {
        super(0, eventManager);
        this.balls = balls;
    }

    /** The 2nd Contructor with datetoplay.
     * @param eventManager
     * @param balls
     * @param dateToPlay
     * */
    public EventMoveBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager);
        this.balls = balls;
    }

    /** the execute method. */
    public void execute() {
        for (Ball b : balls.getballsList()) {
            b.update(balls.getWidth(), balls.getHeight());
        }
        EventManager em = getEventManager();
        em.addEvent(new EventMoveBalls(getDateToPlay() + 1, em, balls));
    }

    /** The toString method.
     * @return a string representing the balls update
     *         with the event.
     */
    @Override
    public String toString() {
        return super.toString() + " move all Balls";
    }
}
