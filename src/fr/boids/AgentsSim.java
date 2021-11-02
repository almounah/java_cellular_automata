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
        this.agents = new Agents(nbAgents, width, height);
        setSimulable(this);
    }

    //Dessine les bords du "panel"
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

        //Partie perso
        // Oval o = new Oval(w/2, h/2, Color.yellow, Color.DARK_GRAY, 20);
        // addGraphicalElement(o);
    }

    private void drawAgents(){
        for(Agent a : agents.getAgents()){
            // addGraphicalElement(
            //     new Oval(
            //         (int) a.getPosition().x, 
            //         (int) a.getPosition().y, 
            //         Color.white, 
            //         Color.black, 
            //         160));
            // addGraphicalElement(
            //     new Oval(
            //         (int) a.getPosition().x, 
            //         (int) a.getPosition().y, 
            //         Color.white, 
            //         Color.black, 
            //         80));
            addGraphicalElement(
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
        agents.update(getPanelWidth(), getPanelHeight());
        reset();
        drawEdge(Color.white);
        drawAgents();
    }

    @Override
    public void restart(){
        agents.reInit();
        reset();
        drawEdge(Color.white);
        drawAgents();
    }
}
