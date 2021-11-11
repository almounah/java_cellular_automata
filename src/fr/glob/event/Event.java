package fr.glob.event;

import java.lang.Comparable;

public abstract class Event implements Comparable<Event>{
    private long dateToPlay;

    public Event(long dateToPlay) {
        this.dateToPlay = dateToPlay;
    }

    public long getDateToPlay() {
        return this.dateToPlay;
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
