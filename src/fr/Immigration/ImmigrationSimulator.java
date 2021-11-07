package fr.Immigration;

import fr.Conway.*;
import gui.GUISimulator;

import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ImmigrationSimulator
 */
public class ImmigrationSimulator extends ConwaySimulator{
    
    public int states_number;

    public ImmigrationSimulator(int size_of_square, int rows, Color[] list_color, GUISimulator win) {
        super(size_of_square, rows, 0, new ImmigrationGrid(rows, rows, list_color.length), list_color, win);
        this.states_number = list_color.length;
    }

}
