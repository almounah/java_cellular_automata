package fr.Conway;

import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

public class ConwaySimulator extends GUISimulator implements Simulable {
    public int width, height, column, rows;
    public int init_alive;

    public ConwaySimulator(int width, int height, int column, int rows, int init_alive) {
        super(width, height, Color.WHITE);
        this.width = width;
        this.height = height;
        this.column = column;
        this.rows = rows;
        this.init_alive = init_alive;
    }

    public void restart() {
    }
}


