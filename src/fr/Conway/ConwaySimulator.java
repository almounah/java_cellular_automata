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
    public int half_square; 
    private Color list_Colors[]; 
    private ConwayGrid grid;

    public ConwaySimulator(int size_of_square, int rows, int init_alive) { 

        this(size_of_square, rows, init_alive, new ConwayGrid(rows, rows, init_alive), new Color[]{Color.WHITE, Color.BLACK}); // we chose a squared grid always 
    }


    protected ConwaySimulator(int size_of_square, int rows, int init_alive, ConwayGrid grid, Color list_Colors[]) {
        super(size_of_square*rows, size_of_square*rows, Color.BLACK);
        this.size_of_square = size_of_square;
        this.rows = rows;
        this.init_alive = init_alive;
        this.grid = grid;
        this.list_Colors = list_Colors;
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
        int cube_size = size_of_square-4;
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
            draw_cube(x*size_of_square, y*size_of_square, list_Colors[grid.grid[y][x]]); 
        } 
    }

    public void draw_grid_init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {  
                draw_cube(i*size_of_square, j*size_of_square, list_Colors[grid.grid[j][i]]); 
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
        this.grid.update_grid(map);
        this.draw_grid(map);

    }

    @Override
    public void restart() {
        this.grid.initialize();
        this.draw_grid_line();
        this.draw_grid_init();
    }
}


