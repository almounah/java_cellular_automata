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

        if(args[0].equals("boids")){
            gui.setSimulable(new AgentsSim(w, h, nbAgents, gui));
        } 

        if(args[0].equals("balls")){
            boolean useBlueRedBalls = false; //Démonstration de l'utilisation des Events (Rouges->rapides, Bleues ->lentes)
            gui.setSimulable(new BallsSimulator(w, h, nbAgents, gui, useBlueRedBalls));
        } 

        if(args[0].equals("conway")) {
            int size_of_square = 20, number_of_square = 20, init_alive = 50;
            gui.setSimulable(new ConwaySimulator(size_of_square, number_of_square, init_alive, gui));
        }

        if (args[0].equals("immigration")) {
            int size_of_square = 25, number_of_square = 15; 
            Color[] list_color = new Color[]{Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK};
            gui.setSimulable(new ImmigrationSimulator(size_of_square, number_of_square, list_color, gui));

        }

        if (args[0].equals("schelling")) {
            int size_of_square = 25, number_of_square=15, init_alive=2000, seuil = 3; 
            Color[] list_color = new Color[]{Color.WHITE, Color.RED, Color.BLUE, Color.GREEN};
            gui.setSimulable(new SchellingSimulator(size_of_square, number_of_square, list_color, seuil, init_alive, gui));
        }
    }
}
