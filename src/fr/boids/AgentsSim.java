package fr.boids;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.Color;

import fr.glob.Simulateur;
import fr.glob.event.*;
import fr.glob.event.eventBoids.*;

public class AgentsSim extends Simulateur {
    private AgentsBoids agents;

    public AgentsSim(int width, int height, int nbAgents, GUISimulator win) {
        super(width, height, win);
        this.agents = new AgentsBoids(nbAgents, width, height);
        em.addEvent(new EventMoveBoids(em, this.agents));
        em.setInitialStatus();
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
        em.next();
        win.reset();
        drawEdge(Color.white);
        drawAgents();
    }

    @Override
    public void restart(){
        em.restart();
        agents.reInit();
        win.reset();
        drawEdge(Color.white);
        drawAgents();
    }
}
