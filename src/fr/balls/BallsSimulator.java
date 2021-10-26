package fr.balls;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.Color;

public class BallsSimulator extends GUISimulator implements Simulable{
    private Balls balls;


    public BallsSimulator(int width, int height, Color bgColor, int nbBalls) {
        super(width, height, bgColor);
        this.balls = new Balls(nbBalls, width, height);
        setSimulable(this);
    }

    //Dessine les bords du "panel", où les balls peuvent se déplacer
    private void drawEdge(Color c){
        int w = getPanelWidth();
        int h = getPanelHeight();
        Rectangle r = new Rectangle(w/2,0, c, c, w, 1);
        addGraphicalElement(r);
        r = new Rectangle(0,h/2, c, c, 1, h);
        addGraphicalElement(r);
        r = new Rectangle(w/2,h-1, c, c, w, 1);
        addGraphicalElement(r);
        r = new Rectangle(w-1,h/2, c, c, 1, h);
        addGraphicalElement(r);
    }

    private void drawBalls(){
        for(Ball b : balls.getballsList())
            addGraphicalElement(
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
        balls.translate(getPanelWidth(), getPanelHeight());
        reset();
        drawEdge(Color.white);
        drawBalls();
    }

    @Override
    public String toString() {
        return "{" +
            " balls='" + balls + "'" +
            "}";
    }

    @Override
    public void restart(){
        balls.reInit();
        reset();
        drawEdge(Color.white);
        drawBalls();
    }

}
