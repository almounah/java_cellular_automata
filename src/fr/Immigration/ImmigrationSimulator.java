package fr.Immigration;

import fr.Conway.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ImmigrationSimulator
 */
public class ImmigrationSimulator extends ConwaySimulator{
    
    public int states_number;
    private Color list_Colors[] = {Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK};

    public ImmigrationSimulator(int size_of_square, int rows, int states_number) {
        super(size_of_square, rows, 0, new ImmigrationGrid(rows, rows, states_number), new Color[]{Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.BLACK});
        this.states_number = states_number;
    }

}
