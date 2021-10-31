package fr.Immigration;

import fr.Conway.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


/**
 * ImmigrationGrid
 */
public class ImmigrationGrid extends ConwayGrid {
    public int number_state;

    public ImmigrationGrid(int rows, int column, int number_state) {
        super(column, rows, 0);
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

        x_before = (rows + x-1)%rows;
        x_after = (x+1)%rows;
        y_before = (column + y-1)%column;
        y_after = (y+1)%column;
        
        list_neighboor[grid[y_before][x_before]]++;
        list_neighboor[grid[y_before][x]]++;
        list_neighboor[grid[y_before][x_after]]++;
        list_neighboor[grid[y][x_before]]++;
        list_neighboor[grid[y][x_after]]++;
        list_neighboor[grid[y_after][x_before]]++;
        list_neighboor[grid[y_after][x]]++; 
        list_neighboor[grid[y_after][x_after]]++;
        
        if (list_neighboor[(grid[y][x]+1)%number_state] >= 3) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize() {
        // Everything is randomly at a state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = ThreadLocalRandom.current().nextInt(0, number_state);
            }    
        }
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
