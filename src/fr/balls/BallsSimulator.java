package fr.balls;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.Color;

import fr.glob.Simulateur;

public class BallsSimulator extends Simulateur{
    private Balls balls;

    public BallsSimulator(int width, int height, int nbBalls, GUISimulator win) {
        super(width, height, win);
        this.balls = new Balls(nbBalls, width, height);
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
        balls.translate(w, h);
        win.reset();
        drawEdge(Color.white);
        drawBalls();
    }
    @Override
    public void restart(){
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
