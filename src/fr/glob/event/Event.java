/**
 * This module is about simulating Event.
 * It consists of two classes:
 *  <ul>
 *   <li>EventManager : the part responsible for dealing with Events</li>
 *   <li>Event : and abstract extended class representing Event</li>
 *  </ul>
 *
 */
package fr.glob.event;

import java.lang.Comparable;

/** Abstract Event class, implements Comparable<Event> interface.
 */
public abstract class Event implements Comparable<Event> {
    /**The date to play the event */
    private long dateToPlay;
    /**The Event manager liked to this event */
    private EventManager em;

    /** The constructor of Event.
     *  @param dateToPlay The date to play the event
     *  @param eventManager The Event manager liked to this event
     * */
    public Event(long dateToPlay, EventManager eventManager) {
        this.dateToPlay = dateToPlay;
        this.em = eventManager;
    }

    /**Getter of dateToPlay field
     * @return dateToPlay
     */
    public long getDateToPlay() {
        return this.dateToPlay;
    }

    /**Getter of eventManager field
     * @return eventManager
     */
    public EventManager getEventManager() {
        return this.em;
    }

    /**Comparator used in priority queue to deal with Event
     * @param other Event to compare with this
     * @return 1 if the dateToPlay of this event is grater than the date of other, 
     */
    @Override
    public int compareTo(Event other) {
        // On retourne -1 si la date de this est plus petite que other,
        // this aura alors une priorité plus grande
        if(this.getDateToPlay() > other.getDateToPlay()) {
            return 1;
        }
        if (this.getDateToPlay() < other.getDateToPlay()) {
            return -1;
        }
        return 0;
    }

    /** Abstract method doing what the event is build for.
     * Used by the EventManager befor deleting the Event
     */
    public abstract void execute();
    
    /** The toString method.
     * @return the string representing the vector.
     */
    @Override
    public String toString() {
        return "Event at date " + dateToPlay;
    }
}
