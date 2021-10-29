package fr.Immigration;

import fr.Conway.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ImmigrationSimulator
 */
public class ImmigrationSimulator extends ConwaySimulator{
    
    public int rows;
    public int size_of_square;
    public int init_alive;
    public int states_number;
    private ImmigrationGrid grid;
    private Color list_Colors[] = {Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK};

    public ImmigrationSimulator(int size_of_square, int rows, int init_alive, int states_number) {
        super(size_of_square, rows, init_alive);
        this.rows = rows;
        this.size_of_square = size_of_square;
        this.init_alive = init_alive;
        this.states_number = states_number;
        grid = new ImmigrationGrid(rows, rows, init_alive, states_number);
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
