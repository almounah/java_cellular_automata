/**This Module is used to make Events.
 * It contains many classes :
 *  <ul>
 *      <li> Event an abstract </li>
 *      <li> Event manager that has many events </li>
 *      <li> Event Message for strings. </li>
 *      <li> Event Message to test events later with strings. </li>
 *           like eventBalls, eventgrid ... </li>
 *  </ul>
 * */
package fr.glob.event;

import java.lang.Comparable;

/** Event.
 *      <li> Event an abstract </li>
 */
public abstract class Event implements Comparable<Event> {

    /** The date when the Event will be played. */
    private long dateToPlay;

    /** The event manager used to add the event to. */
    private EventManager em;

    /** The constructor.
     * @param dateToPlay : the date when the event will be played
     * @param eventManager : the eventmanager that manage the event
     * */
    public Event(long dateToPlay, EventManager eventManager) {
        this.dateToPlay = dateToPlay;
        this.em = eventManager;
    }

    /** Get the date when the event will be played.
     *  @return the date.
     * */
    public long getDateToPlay() {
        return this.dateToPlay;
    }

    /** Get the event manager that manage the Event.
     *  @return the event manager.
     * */
    public EventManager getEventManager() {
        return this.em;
    }

    /** We override the method compareTo.
     * It compares two events.
     * @param other : the other event.
     * @return 1 if the event is executed later that other
     *         0 if not.
     * */
    @Override
    public int compareTo(Event other) {
        // On retourne -1 si la date de this est plus petite que other,
        // this aura alors une priorité plus grande
        if (this.getDateToPlay() > other.getDateToPlay()) {
            return 1;
        }
        if (this.getDateToPlay() < other.getDateToPlay()) {
            return -1;
        }
        return 0;
    }

    /** The execute method of an event. */
    public abstract void execute();

    /** Return the string representing the event.
     *  @return the string.
     * */
    @Override
    public String toString() {
        return "Event at date " + dateToPlay;
    }
}
