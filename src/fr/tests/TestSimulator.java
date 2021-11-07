package fr.tests;

import fr.balls.*;
import fr.boids.*;
import gui.GUISimulator;
import java.awt.Color;

public class TestSimulator {
    public static void main(String[] args) {
        int w=600, h=400, nbAgents = 10;
        GUISimulator gui = new GUISimulator(w, h, Color.BLACK);
        if(args[0].equals("boids")) gui.setSimulable(new AgentsSim(w, h, nbAgents, gui));
        if(args[0].equals("balls")) gui.setSimulable(new BallsSimulator(w, h, nbAgents, gui));
    }
}
