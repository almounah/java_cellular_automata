package fr.Conway;

import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

public class ConwaySimulator extends GUISimulator implements Simulable {
    public int size, rows;
    public int init_alive;
    public ConwayGrid grid;

    public ConwaySimulator(int size, int rows, int init_alive) {
        super(size, size, Color.WHITE);
        this.rows = rows;
        this.init_alive = init_alive;
        grid = new ConwayGrid(rows, rows, init_alive); // we chose a squared grid always 
    }

    public void draw_grid_line() {
        for (int i = 0; i < size; i += size/rows) {
            Rectangle r = new Rectangle(i, size, Color.BLACK, Color.BLACK, 5, size);
            addGraphicalElement(r);
            r = new Rectangle(size, i, Color.GRAY, Color.GRAY, size, 10); 
            addGraphicalElement(r);
        } 
    }

    @Override
    public void next() {
        this.draw_grid_line();
    }


    @Override
    public void restart() {
        this.draw_grid_line();
    }
}


