package fr.glob.event.eventGrid;

import fr.glob.event.*;
import fr.Conway.*;
import java.util.HashMap;
import java.util.ArrayList;

public class EventGrid extends Event {
    private ConwayGrid grid;

    public EventGrid(EventManager eventManager, ConwayGrid grid){
        super(0, eventManager);
        this.grid = grid;
    }

    public EventGrid(long dateToPlay, EventManager eventManager, ConwayGrid grid) {
        super(dateToPlay, eventManager);
        this.grid = grid;
    }

    public void execute(){
        HashMap<String, ArrayList<Integer>> map = this.grid.getToChangeList();
        this.grid.updateGrid(map);
        EventManager em = getEventManager();
        em.addEvent(new EventGrid(getDateToPlay()+1, em, grid));
    }

    @Override
    public String toString() {
        return super.toString() + " update the grid.";
    }


}
