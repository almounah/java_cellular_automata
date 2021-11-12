package fr.Conway;

import fr.glob.*;
import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

public class ConwaySimulator extends Simulateur {
    public int size_of_square, rows; //taille d'un petit carre et le nombre de ligne
    public int init_alive;
    public int half_square; 
    private Color list_Colors[]; 
    private ConwayGrid grid; 

    public ConwaySimulator(int size_of_square, int rows, int init_alive, GUISimulator win) { 
        this(
            size_of_square, 
            rows, init_alive, 
            new ConwayGrid(rows, rows, init_alive), 
            new Color[]{Color.WHITE, Color.BLACK},
            win); // we chose a squared grid always 
    }


    protected ConwaySimulator(
            int size_of_square, 
            int rows, 
            int init_alive, 
            ConwayGrid grid, 
            Color list_Colors[],
            GUISimulator win) {
        super(size_of_square*rows, size_of_square*rows, win);
        this.size_of_square = size_of_square;
        this.rows = rows;
        this.init_alive = init_alive;
        this.grid = grid;
        this.list_Colors = list_Colors;
        this.win = win;
        half_square = size_of_square/2;
        this.grid.initialize();
    }

    public void draw_grid_line() {
        Rectangle r;
        int size = size_of_square*rows;
        for (int i = 0; i <= size; i+=size_of_square) {
            r = new Rectangle(i, size/2, Color.GRAY, Color.WHITE, 1, size);
            win.addGraphicalElement(r);
            r = new Rectangle(size/2, i, Color.GRAY, Color.WHITE, size, 1); 
            win.addGraphicalElement(r);
        } 
    }
    

    public void draw_cube(int x, int y, Color c) {
        /*x and y are the coordinate of the top left*/    
        Rectangle r;
        int cube_size = size_of_square-4;
        int x_center = x + half_square;
        int y_center = y + half_square;
        r = new Rectangle(x_center, y_center, c, c, cube_size, cube_size);
        win.addGraphicalElement(r);
    }

    public void draw_grid()  {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                draw_cube(j*size_of_square, i*size_of_square, list_Colors[grid.grid[j][i]]); 
            }
        }
    }


    @Override
    public String toString() {
        return ("The board of size " + size_of_square*rows);
    }

    @Override
    public void next() {
        HashMap<String,ArrayList<Integer>> map = this.grid.get_to_change_list();
        win.reset();
        this.grid.update_grid(map);
        this.draw_grid();
        this.draw_grid_line();

    }

    @Override
    public void restart() {
        this.grid.reInit();
        win.reset();
        this.draw_grid();
        this.draw_grid_line();
    }
}


