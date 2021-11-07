package fr.tests;

import fr.balls.*;
import fr.Conway.*;
import fr.Schelling.*;
import fr.Immigration.*;
import fr.boids.*;
import gui.GUISimulator;
import java.awt.Color;

public class TestSimulator {
    public static void main(String[] args) {
        int w=600, h=400, nbAgents = 10;
        GUISimulator gui = new GUISimulator(w, h, Color.BLACK);
        if(args[0].equals("boids")) gui.setSimulable(new AgentsSim(w, h, nbAgents, gui));
        if(args[0].equals("balls")) gui.setSimulable(new BallsSimulator(w, h, nbAgents, gui));
        if(args[0].equals("conway")) {
            int number_of_square = 20, init_alive = 50;
            gui.setSimulable(new ConwaySimulator(w/number_of_square, number_of_square, init_alive));
        }

        if (args[0].equals("immigration")) {
            int number_of_square = 20, size_of_square = 20; 
            Color[] list_color = new Color[]{Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK};
            gui.setSimulable(new ImmigrationSimulator(size_of_square, number_of_square, list_color));

        }

        if (args[0].equals("schelling")) {
            int number_of_square = 20, size_of_square = 20, init_alive=2000, seuil = 3; 
            Color[] list_color = new Color[]{Color.WHITE, Color.RED, Color.BLUE, Color.GREEN};
            gui.setSimulable(new SchellingSimulator(size_of_square, number_of_square, list_color, 3, init_alive));
        }
    }
}
