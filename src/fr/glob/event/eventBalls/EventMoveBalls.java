package fr.glob.event.eventBalls;

import fr.balls.*;
import fr.glob.event.*;

public class EventMoveBalls extends Event {
    protected Balls balls;

    public EventMoveAllBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager);
        this.balls = balls;
    }

    public void execute(){
        for (Ball b : ballsList) b.update(w, h);
        EventManager em = getEventManager();
        em.addEvent(new EventMoveBalls(getDateToPlay()+1, balls, em));
    }

    @Override
    public String toString() {
        return super.toString() + " move all Balls";
    }
}
