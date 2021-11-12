package fr.glob.event.eventBalls;

import java.awt.Color;
import fr.balls.*;
import fr.glob.event.*;

public class EventMoveRedBalls extends EventMoveBalls {

    private static long moveRedBallsPeriod = 1;

    public EventMoveRedBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager, balls);
    }
    public EventMoveRedBalls(EventManager eventManager, Balls balls) {
        super(0, eventManager, balls);
    }

    public void execute(){
        for (Ball b : balls.getballsList()){
            if (b.getColor() == Color.RED) b.update(balls.getWidth(),balls.getHeight());
        } 
        EventManager em = getEventManager();
        long nextDate = getDateToPlay()+moveRedBallsPeriod;
        em.addEvent(new EventMoveRedBalls(nextDate, em, balls));
    }

    @Override
    public String toString() {
        return super.toString() + " move red Balls";
    }

}
