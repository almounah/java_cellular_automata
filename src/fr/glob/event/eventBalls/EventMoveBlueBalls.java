package fr.glob.event.eventBalls;

import java.awt.Color;
import fr.balls.*;

public class EventMoveBlueBalls extends EventMoveBalls {

    private static long moveBlueBallsPeriod = 3;

    public EventMoveRedBalls(long dateToPlay, EventManager eventManager, Balls balls) {
        super(dateToPlay, eventManager, balls);
    }

    public void execute(){
        for (Ball b : ballsList){
            if (b.getColor() == Color.BLUE) b.update(balls.getWidth,balls.getHeight);
        } 
        EventManager em = getEventManager();
        long nextDate = getDateToPlay()+moveBlueBallsPeriod;
        em.addEvent(new EventMoveRedBalls(nextDate, em, balls));
    }

    @Override
    public String toString() {
        return super.toString() + " move blue Balls";
    }
    
}
