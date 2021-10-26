package fr.tests;

import fr.Conway.*;
import gui.GUISimulator;
import java.awt.Color;

/**
 * TestConwaySimulator
 */
public class TestConwaySimulator {
    public static void main(String[] args) {
        GUISimulator gui = new ConwaySimulator(100, 500, 2, 2, 3); 
    }
}
