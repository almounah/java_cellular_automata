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
        
        if (list_neighboor[(grid[y][x])] <= 8 - seuil) {
            return true;
        }
        return false;
    }


    @Override
    public void update_grid(HashMap<String,ArrayList<Integer>> map) {
        
        ArrayList<Integer> list_tochange_x = map.get("x_coord");
        ArrayList<Integer> list_tochange_y = map.get("y_coord");
        int x_empty, y_empty;
        for (int i = 0; i < list_tochange_x.size(); i++) {
            if (list_empty_x.size() == 0) {
                return;
            }
            x_empty = list_empty_x.get(0);
            y_empty = list_empty_y.get(0);
            list_empty_x.remove(0);
            list_empty_y.remove(0);
            
            grid[y_empty][x_empty] = grid[list_tochange_y.get(i)][list_tochange_x.get(i)];
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)] = 0;

            list_empty_x.add(list_tochange_x.get(i));
            list_empty_y.add(list_tochange_y.get(i));
        
        }

    }
    
    @Override
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
            grid[y][x] = ThreadLocalRandom.current().nextInt(0, nombre_couleur);
        }
    }



}
