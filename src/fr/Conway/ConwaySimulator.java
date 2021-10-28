package fr.Conway;

import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

public class ConwaySimulator extends GUISimulator implements Simulable {
    public int size_of_square, rows; //taille d'un petit carre et le nombre de ligne
    public int init_alive;
    public ConwayGrid grid;
    public int half_square; 

    public ConwaySimulator(int size_of_square, int rows, int init_alive) {
        super(size_of_square*rows, size_of_square*rows, Color.BLACK);
        this.size_of_square = size_of_square;
        this.rows = rows;
        this.init_alive = init_alive;
        grid = new ConwayGrid(rows, rows, init_alive); // we chose a squared grid always 
        setSimulable(this);
        half_square = size_of_square/2;
    }

    public void draw_grid_line() {
        Rectangle r;
        int size = size_of_square*rows;
        for (int i = 0; i <= size; i+=size_of_square) {
            r = new Rectangle(i, size/2, Color.GRAY, Color.GRAY, 1, size);
            addGraphicalElement(r);
            r = new Rectangle(size/2, i, Color.GRAY, Color.GRAY, size, 1); 
            addGraphicalElement(r);
        } 
    }
    

    public void draw_cube(int x, int y, Color c) {
        /*x and y are the coordinate of the top left*/    
        Rectangle r;
        int cube_size = size_of_square - 4;
        int x_center = x + half_square;
        int y_center = y + half_square;
        r = new Rectangle(x_center, y_center, c, c, cube_size, cube_size);
        addGraphicalElement(r);
    }

    public void draw_grid(HashMap<String,ArrayList<Integer>> map)  {
        ArrayList<Integer> list_x = map.get("x_coord");
        ArrayList<Integer> list_y = map.get("y_coord");
        for (int i = 0; i < list_x.size(); i++) {
            int x = list_x.get(i);
            int y = list_y.get(i);
            if (grid.grid[y][x]==1) {
                draw_cube(x*size_of_square, y*size_of_square, Color.BLACK); 
                } else {
                   draw_cube(x*size_of_square, y*size_of_square, Color.WHITE); 
                }
            } 
        }

    public void draw_grid_init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {  
                if (grid.grid[j][i]==1) {
                    draw_cube(i*size_of_square, j*size_of_square, Color.BLACK); 
                } else {
                    draw_cube(i*size_of_square, j*size_of_square, Color.WHITE); 
                } 
            } 
        }
    }

    @Override
    public String toString() {
        return ("The board of size " + size_of_square*rows);
    }
    @Override
    public void next() {
        HashMap<String,ArrayList<Integer>> map = this.grid.update_grid();
        this.draw_grid(map);

    }

    @Override
    public void restart() {
        this.grid.initialize();
        this.draw_grid_line();
        this.draw_grid_init();
    }
}


