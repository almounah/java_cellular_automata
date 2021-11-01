package fr.tests;

import fr.Conway.*;
import fr.Immigration.ImmigrationSimulator;
import gui.GUISimulator;
import java.awt.Color;
/**
 * TestImmigrationSimulator
 */
public class TestImmigrationSimulator {
    public static void main(String[] args) {
        Color[] list_color = new Color[]{Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK};
        GUISimulator gui = new ImmigrationSimulator(20, 20, list_color);
    }
}
