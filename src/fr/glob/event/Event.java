package fr.glob.event;

import java.lang.Comparable;

public abstract class Event implements Comparable<Event>{
    private long dateToPlay;
    private EventManager em;

    public Event(long dateToPlay, EventManager eventManager) {
        this.dateToPlay = dateToPlay;
        this.em = eventManager;
    }

    public long getDateToPlay() {
        return this.dateToPlay;
    }

    public EventManager getEventManager() {
        return this.em;
    }

    @Override
    public int compareTo(Event other) {
        // On retourne -1 si la date de this est plus petite que other,
        // this aura alors une priorité plus grande
        if(this.getDateToPlay() > other.getDateToPlay()) return 1;
        if(this.getDateToPlay() < other.getDateToPlay()) return -1;
        return 0;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Event at date " + dateToPlay;
    }
}
