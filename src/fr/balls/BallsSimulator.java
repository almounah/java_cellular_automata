package fr.balls;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.Color;

import fr.glob.Simulateur;
import fr.glob.event.*;
import fr.glob.event.eventBalls.*;

public class BallsSimulator extends Simulateur{
    private Balls balls;

    public BallsSimulator(int width, int height, int nbBalls, GUISimulator win) {
        super(width, height, win);
        this.balls = new Balls(nbBalls, width, height, false);
        em.addEvent(new EventMoveBalls(this.em, balls));
        em.setInitialStatus();
    }

    public BallsSimulator(int width, int height, int nbBalls, GUISimulator win, boolean useRedBlueBalls) {
        super(width, height, win);
        this.balls = new Balls(nbBalls, width, height, useRedBlueBalls);
        em.addEvent(new EventMoveRedBalls(this.em, balls));
        em.addEvent(new EventMoveBlueBalls(this.em, balls));
        em.setInitialStatus();
    }

    private void drawBalls(){
        for(Ball b : balls.getballsList())
            win.addGraphicalElement(
                new Oval(
                    (int)b.getPosition().x,
                    (int)b.getPosition().y,
                    b.getColor(), 
                    b.getColor(), 
                    b.getRayon()*2
                )
            );
    }

    @Override
    public void next(){
        em.next();
        win.reset();
        drawEdge(Color.white);
        drawBalls();
    }
    @Override
    public void restart(){
        em.restart();
        balls.reInit();
        win.reset();
        drawEdge(Color.white);
        drawBalls();
    }

    @Override
    public String toString() {
        return "{" +
            " balls='" + balls + "'" +
            "}";
    }

}
