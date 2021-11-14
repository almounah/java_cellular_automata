package fr.glob.event.eventBalls;

import java.awt.Color;
import fr.balls.*;
import fr.glob.event.*;

public class EventMoveBlueBalls extends EventMoveBalls {

    private static long moveBlueBallsPeriod = 3;

    public EventMoveBlueBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager, balls);
    }
    public EventMoveBlueBalls(EventManager eventManager, Balls balls) {
        super(0, eventManager, balls);
    }

    public void execute() {
        for (Ball b : balls.getballsList()) {
            if (b.getColor() == Color.BLUE) {
                b.update(balls.getWidth(), balls.getHeight());
            }
        } 
        EventManager em = getEventManager();
        long nextDate = getDateToPlay()+moveBlueBallsPeriod;
        em.addEvent(new EventMoveBlueBalls(nextDate, em, balls));
    }

    @Override
    public String toString() {
        return super.toString() + " move blue Balls";
    }
    
}
