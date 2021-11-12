package fr.Schelling;

import fr.Conway.*;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
/**
 * ShellingGrid
 */
public class SchellingGrid extends ConwayGrid {
    
    int seuil;
    int nombre_couleur;
    ArrayList<Integer> list_empty_x;
    ArrayList<Integer> list_empty_y;

    public SchellingGrid(int column, int rows, int init_alive, int seuil, int nombre_couleur) {
        super(column, rows, init_alive);
        this.seuil = seuil;
        this.nombre_couleur = nombre_couleur;
        this.list_empty_x = new ArrayList<Integer>();
        this.list_empty_y = new ArrayList<Integer>();
    }


    public void initialize_vacante() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[j][i] == 0) {
                    list_empty_x.add(0, i);
                    list_empty_y.add(0, j);
                }
            }
        }
    }


    @Override
    public boolean update_cell(int x, int y) {
        if (grid[y][x] == 0) {
            return false;
        }

        int x_before, x_after;
        int y_before, y_after;
        int list_neighboor[] = new int[nombre_couleur];
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
        
        int number_neighboor = 0;
        for (int i=0; i<nombre_couleur; i++) {
            number_neighboor += list_neighboor[i];
        }

        number_neighboor -= (list_neighboor[grid[y][x]] + list_neighboor[0]);
        
        if (number_neighboor > seuil) {
            return true;
        }
        return false;
    }


    @Override
    public void update_grid(HashMap<String,ArrayList<Integer>> map) {
        
        ArrayList<Integer> list_tochange_x = map.get("x_coord");
        ArrayList<Integer> list_tochange_y = map.get("y_coord");
        int size = list_tochange_x.size();
        int x_empty, y_empty;
        int x_current, y_current;
        for (int i = 0; i < size; i++) {
            x_empty = list_empty_x.get(0);
            y_empty = list_empty_y.get(0);
            x_current = list_tochange_x.get(i);
            y_current = list_tochange_y.get(i);
            list_empty_x.remove(0);
            list_empty_y.remove(0);
            
            grid[y_empty][x_empty] = grid[y_current][x_current];
            grid[y_current][x_current] = 0;
            
            list_empty_x.add(x_current);
            list_empty_y.add(y_current);
        
            list_tochange_x.add(x_empty);
            list_tochange_y.add(y_empty);
        }

    }
    
    @Override
    public void initialize() {
        // Everything is dead, so 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = 0;
                grid_copy[j][i] = 0;
            }    
        }

        // Some are randomly alive, so 1
        int x, y;
        for (int i = 0; i < init_alive; i++) {
            x = ThreadLocalRandom.current().nextInt(0, rows);
            y = ThreadLocalRandom.current().nextInt(0, column);
            grid[y][x] = ThreadLocalRandom.current().nextInt(0, nombre_couleur);
            grid_copy[y][x] = grid[y][x];
        }

        // We initialize the empty list
        initialize_vacante();
    }
    
    @Override
    public void reInit() {
        super.reInit();
        list_empty_x = new ArrayList<Integer>(); 
        list_empty_y = new ArrayList<Integer>(); 
        initialize_vacante();
    }
}
