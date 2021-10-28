package fr.Conway;


import java.util.concurrent.ThreadLocalRandom;
import fr.glob.*;

import java.util.ArrayList;

/**
 * ConwayGrid
 */
public class ConwayGrid {

    public int column;
    public int rows;
    public int init_alive;
    public int grid[][];

    public ConwayGrid(int column, int rows, int init_alive) {
        this.column = column;
        this.rows = rows;
        this.init_alive = init_alive;
        this.grid = new int[column][rows];
    }

    public void initialize() {
        // Everything is dead, so 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = 0;
            }    
        }

        // Some are randomly alive, so 1
        int x, y;
        for (int i = 0; i < init_alive; i++) {
            x = ThreadLocalRandom.current().nextInt(0, rows);
            y = ThreadLocalRandom.current().nextInt(0, column);
            grid[y][x] = 1;
        }
    }

    /*Tell us if you should update a cell*/
    public boolean update_cell(int x, int y) {
        int x_before, x_after;
        int y_before, y_after;

        x_before = (x == 0) ? rows : x-1;
        x_after = (x == rows) ? 0 : x+1;
        y_before = (y == 0) ? column : y-1;
        y_after = (y == column) ? 0 : y-1;

        int neighboor_sum = 0;
        for (int i = x_before; i <= x_after; i++) {
            for (int j = y_before; j < y_after; j++) {
                neighboor_sum += grid[j][i];
            }
        }
        
        neighboor_sum -= grid[y][x];
        if (neighboor_sum == 3 && grid[y][x] == 0) {
            return true;
        } if (neighboor_sum != 3 && neighboor_sum !=2 && grid[y][x] == 1) {
            return true;
        }

        return false;
    }

    public void update_grid() {
        ArrayList<Integer> list_tochange_x = new ArrayList<Integer>();
        ArrayList<Integer> list_tochange_y = new ArrayList<Integer>();;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                 if (update_cell(i, j)) {
                     list_tochange_x.add(0, i);
                     list_tochange_y.add(0, j);
                 }
            }
        }
        
        for (int i = 0; i < list_tochange_x.size(); i++) {
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)] += 1;
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)] %= 2;
        }
    }
}