package fr.tests;

import fr.Conway.*;
import fr.Immigration.ImmigrationSimulator;
import gui.GUISimulator;

/**
 * TestImmigrationSimulator
 */
public class TestImmigrationSimulator {

    public static void main(String[] args) {
        GUISimulator gui = new ImmigrationSimulator(20, 30, 100, 4);
    }
}
