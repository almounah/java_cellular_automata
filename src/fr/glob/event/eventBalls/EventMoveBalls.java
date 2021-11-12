package fr.glob.event.eventBalls;

import fr.balls.*;
import fr.glob.event.*;

public class EventMoveBalls extends Event {
    protected Balls balls;

    public EventMoveBalls(EventManager eventManager, Balls balls){
        super(0, eventManager);
        this.balls = balls;
    }

    public EventMoveBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager);
        this.balls = balls;
    }

    public void execute(){
        for (Ball b : balls.getballsList()) b.update(balls.getWidth(), balls.getHeight());
        EventManager em = getEventManager();
        em.addEvent(new EventMoveBalls(getDateToPlay()+1, em, balls));
    }

    @Override
    public String toString() {
        return super.toString() + " move all Balls";
    }
}
