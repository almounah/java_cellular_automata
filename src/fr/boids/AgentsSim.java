package fr.boids;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.Color;

public class AgentsSim extends GUISimulator implements Simulable {
    private Agents agents;

    public AgentsSim(int width, int height, Color bgColor, int nbAgents) {
        super(width, height, bgColor);
        this.balls = new Agents(nbAgents, width, height);
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

    private void drawAgents(){
        for(Agent a : agents.getAgents())
            addGraphicalElement(
                new Oval(x, y, drawColor, fillColor, size);
    }
}
