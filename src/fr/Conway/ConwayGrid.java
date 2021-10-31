package fr.Conway;


import java.util.concurrent.ThreadLocalRandom;
import fr.glob.*;
import java.util.HashMap;
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

        x_before = (rows + x-1)%rows;
        x_after = (x+1)%rows;
        y_before = (column + y-1)%column;
        y_after = (y+1)%column;
        
        int neighboor_sum = 0;
        neighboor_sum += grid[y_before][x_before] + grid[y_before][x] + grid[y_before][x_after];
        neighboor_sum += grid[y][x_before] + grid[y][x_after];
        neighboor_sum += grid[y_after][x_before] + grid[y_after][x] + grid[y_after][x_after];
        
        if (neighboor_sum == 3 && grid[y][x] == 0) {
            return true;
        } else if ((neighboor_sum > 3 || neighboor_sum < 2) && grid[y][x] == 1) {
            return true;
        }

        return false;
    }

    // return the map of the coordinate of the cells to change
    public HashMap<String,ArrayList<Integer>> get_to_change_list() {
        ArrayList<Integer> list_tochange_x = new ArrayList<Integer>();
        ArrayList<Integer> list_tochange_y = new ArrayList<Integer>();;
       
        HashMap<String,ArrayList<Integer>> map = new HashMap<String,ArrayList<Integer>>();
        
        map.put("x_coord",list_tochange_x);
        map.put("y_coord",list_tochange_y);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                 if (update_cell(i, j)) {
                     list_tochange_x.add(0, i);
                     list_tochange_y.add(0, j);
                 }
            }
        }
        
        return map;
    }

    public void update_grid(HashMap<String,ArrayList<Integer>> map) {
        
        ArrayList<Integer> list_tochange_x = map.get("x_coord");
        ArrayList<Integer> list_tochange_y = map.get("y_coord");
        
        for (int i = 0; i < list_tochange_x.size(); i++) {
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)]++;
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)] %= 2;
        }
    }


}
