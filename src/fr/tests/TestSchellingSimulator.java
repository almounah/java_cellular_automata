package fr.tests;

import fr.Schelling.SchellingSimulator;
import gui.GUISimulator;
import java.awt.Color;
/**
 * TestSchellingSimulator
 */
public class TestSchellingSimulator {

   public static void main(String[] args) {
        Color[] list_color = new Color[]{Color.WHITE, Color.RED, Color.BLUE, Color.GREEN};
        GUISimulator gui = new SchellingSimulator(20, 30, list_color, 3, 2000);
   } 
}
