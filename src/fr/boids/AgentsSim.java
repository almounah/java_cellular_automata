package fr.boids;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.Color;

import fr.glob.Simulateur;

public class AgentsSim extends Simulateur {
    private AgentsBoids agents;

    public AgentsSim(int width, int height, int nbAgents, GUISimulator win) {
        super(width, height, win);
        this.agents = new AgentsBoids(nbAgents, width, height);
    }

    private void drawAgents(){
        for(AgentBoids a : agents.getAgents()){
            // win.addGraphicalElement(
            //     new Oval(
            //         (int) a.getPosition().x, 
            //         (int) a.getPosition().y, 
            //         Color.white, 
            //         Color.black, 
            //         160));
            // win.addGraphicalElement(
            //     new Oval(
            //         (int) a.getPosition().x, 
            //         (int) a.getPosition().y, 
            //         Color.white, 
            //         Color.black, 
            //         80));
            win.addGraphicalElement(
                new Oval(
                    (int) a.getPosition().x, 
                    (int) a.getPosition().y, 
                    a.getColor(), 
                    a.getColor(), 
                    a.getRayon()*2));  
        }         
            
    }
    @Override
    public void next(){
        agents.update(w, h);
        win.reset();
        drawEdge(Color.white);
        drawAgents();
    }

    @Override
    public void restart(){
        agents.reInit();
        win.reset();
        drawEdge(Color.white);
        drawAgents();
    }
}
