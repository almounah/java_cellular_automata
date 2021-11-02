package fr.tests;

import fr.boids.*;
import gui.GUISimulator;
import java.awt.Color;

public class TestAgentsSim {
    public static void main(String[] args) {
        GUISimulator gui = new AgentsSim(600, 400, Color.BLACK, 5);
    }
}
