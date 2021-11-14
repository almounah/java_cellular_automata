/**This Module is used to make Events.
 * It contains many classes :
 *  <ul>
 *      <li> Event an abstract </li>
 *      <li> Event manager that has many events </li>
 *      <li> Many other classes that extends The abstract Event
 *      <li> Many other classes that extends The abstract Event
 *           like eventBalls, eventgrid ... </li>
 *  </ul>
 * */
package fr.glob.event;

import java.util.Iterator;
import java.util.PriorityQueue;

/** EventManager.
 */
public class EventManager {
    /** The current time. */
    private long currentDate;

    /** The queue representing all events. */
    private PriorityQueue<Event> eventQueue;

    /** The copy of the queue representing all initial events. */
    private PriorityQueue<Event> initialQueue;

    /** The constructor of the Event Manager. */
    public EventManager() {
        this.currentDate = 0;
        this.eventQueue = new PriorityQueue<Event>();
        this.initialQueue = null;
    }

    /** Add the event to the eventManager.
     * @param e : the event to add.
     * */
    public void addEvent(Event e) {
        boolean b = eventQueue.add(e);
        if (!b) {
            System.err.println("EVENT ERROR: L'evenement ne s'est pas ajouté comme il faut!");
        }
    }

    /** Tell us if the list is not empty.
     *  @return false. if the list is empty
     *          true if not.
     * */
    public boolean isMoreEvent() {
        return !eventQueue.isEmpty();
    }

    /** The isfinished method.
     * @return True if we still have event to execute
     *         at our date. False if not.
     * */
    public boolean isFinished() {
        Event e = eventQueue.peek(); // Ne retire pas l'élément de la queue
        if (e == null) {
            return true; // queue vide
        }
        if (e.getDateToPlay() <= currentDate) {
            return false;
        }
        return true;
    }

    /** Execute the next event. */
    public void next() {
        currentDate++;
        while (!isFinished()) {
            Event e = eventQueue.poll();
            e.execute();
        }
    }

    /** Save the current state in the initialQueue.
     *  usefull for the restart.
     * */
    public void setInitialStatus() {
        if (initialQueue != null) {
            System.err.println("ERROR : initialQueue est déjà définie!!!");
            return;
        }
        initialQueue = new PriorityQueue<Event>();
        Iterator<Event> it = eventQueue.iterator();
        while (it.hasNext()) {
            Event e = it.next();
            initialQueue.add(e);
        }
    }

    /** Restart method when debut is pressed. */
    public void restart() {
        if (initialQueue == null) {
            System.err.println("ERROR : l'état initiale de la queue d'Event "
                + "n'a jamais été défini! Il faut utiliser 'setInitialStatus()'");
            return;
        }
        currentDate = 0;
        eventQueue.clear();
        Iterator<Event> it = initialQueue.iterator();
        while (it.hasNext()) {
            Event e = it.next();
            eventQueue.add(e);
        }

    }

}
