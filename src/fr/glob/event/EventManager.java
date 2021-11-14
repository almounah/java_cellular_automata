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

import java.util.Iterator;
import java.util.PriorityQueue;

/** EventManager
 */
public class EventManager {
    /** The current date of the manager. */
    private long currentDate;
    /** The queue of Event to come, implemented using a java PriorityQueue*/
    private PriorityQueue<Event> eventQueue;
    /** The queue intitial event, used to reset eventQueue*/
    private PriorityQueue<Event> initialQueue;

    /** The constructor of an EventManager
     * */
    public EventManager() {
        this.currentDate = 0;
        this.eventQueue = new PriorityQueue<Event>();
        this.initialQueue = null;
    }

    /** Add an event to the queue, 
     * using his dateToPlay as comparative value (lower means high priority)
     * @param e Event to add
     * */
    public void addEvent(Event e){
        boolean b = eventQueue.add(e);
        if(!b) {
            System.err.println("EVENT ERROR: L'evenement ne s'est pas ajouté comme il faut!");
        }
    }

    /** Tell if the event queue is empty or not
     * @return true is the queue is empty, false otherwise
     * */
    public boolean isMoreEvent() {
        return !eventQueue.isEmpty();
    }

    /** Tell if all event with an dateToPlay lower or equal to currentDate has been played
     * @return true all concerned event has been played, false otherwise
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

    /** Increase the current date and play all Event liked
     * */
    public void next() {
        currentDate++;
        //System.out.println("New date! -> "+currentDate);
        while (!isFinished()) {
            Event e = eventQueue.poll();
            e.execute();
        }
    }

    /** Save as initial status the curent state of the event queue.
     * Use restart method to come back to this saved state.
     * */
    public void setInitialStatus() {
        if(initialQueue!=null) {
            System.err.println("ERROR : initialQueue est déjà définie!!!");
            return;
        }
        initialQueue = new PriorityQueue<Event>();
        Iterator<Event> it = eventQueue.iterator();
        while(it.hasNext()){
            Event e = it.next();
            initialQueue.add(e);
        }
    }

    /** Restore the state of the queue as the state saved by setInitialStatus() method.
     * */
    public void restart() {
        if(initialQueue==null) {
            System.err.println("ERROR : l'état initiale de la queue d'Event "+
                "n'a jamais été défini! Il faut utiliser 'setInitialStatus()'");
            return;
        }
        currentDate = 0;
        eventQueue.clear();
        Iterator<Event> it = initialQueue.iterator();
        while(it.hasNext()){
            Event e = it.next();
            eventQueue.add(e);
        }
        
    }

}