package fr.Immigration;

import fr.Conway.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ImmigrationGrid
 */
public class ImmigrationGrid extends ConwayGrid {
    public int column;
    public int rows;
    public int init_alive;
    public int number_state;

    public ImmigrationGrid(int rows, int column, int init_alive, int number_state) {
        super(column, rows, init_alive);
        this.number_state = number_state;
    }
    

    @Override
    public boolean update_cell(int x, int y) {
        int x_before, x_after;
        int y_before, y_after;
        int list_neighboor[] = new int[number_state];

        for (int i = 0; i < list_neighboor.length; i++) {
            list_neighboor[i] = 0; 
        }

        x_before = (x == 0) ? rows-1 : x-1;
        x_after = (x == rows-1) ? 0 : x+1;
        y_before = (y == 0) ? column-1 : y-1;
        y_after = (y == column-1) ? 0 : y+1;
        
        list_neighboor[grid[y_before][x_before]]++;
        list_neighboor[grid[y_before][x]]++;
        list_neighboor[grid[y_before][x_after]]++;
        list_neighboor[grid[y][x_before]]++;
        list_neighboor[grid[y][x_after]]++;
        list_neighboor[grid[y_after][x_before]]++;
        list_neighboor[grid[y_after][x]]++; 
        list_neighboor[grid[y_after][x_after]]++;
        
        if (list_neighboor[(grid[y][x]+1)%number_state] > 3) {
            return true;
        }
        return false;
    }



    @Override
    public void update_grid(HashMap<String,ArrayList<Integer>> map) {
        
        ArrayList<Integer> list_tochange_x = map.get("x_coord");
        ArrayList<Integer> list_tochange_y = map.get("y_coord");
        
        for (int i = 0; i < list_tochange_x.size(); i++) {
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)]++;
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)] %= number_state;
        }
    }

}
