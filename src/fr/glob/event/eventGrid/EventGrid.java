/** This module is used for events in
 *  all the grids simulation.
 * */
package fr.glob.event.eventGrid;

import fr.glob.event.Event;
import fr.glob.event.EventManager;
import fr.Conway.ConwayGrid;
import java.util.HashMap;
import java.util.ArrayList;

/** EventGrid.
 */
public class EventGrid extends Event {

    /** The grid we want to update with events. */
    private ConwayGrid grid;

    /** The Contructor.
     * @param eventManager
     * @param grid
     * */
    public EventGrid(EventManager eventManager, ConwayGrid grid) {
        super(0, eventManager);
        this.grid = grid;
    }

    /** The 2nd Contructor with datetoplay.
     * @param eventManager
     * @param grid
     * @param dateToPlay
     * */
    public EventGrid(long dateToPlay, EventManager eventManager, ConwayGrid grid) {
        super(dateToPlay, eventManager);
        this.grid = grid;
    }

    /** the execute method. */
    public void execute() {
        HashMap<String, ArrayList<Integer>> map = this.grid.getToChangeList();
        this.grid.updateGrid(map);
        EventManager em = getEventManager();
        em.addEvent(new EventGrid(getDateToPlay() + 1, em, grid));
    }

    /** The toString method.
     * @return a string representing the grid update
     *         with the event.
     */
    @Override
    public String toString() {
        return super.toString() + " update the grid.";
    }


}
