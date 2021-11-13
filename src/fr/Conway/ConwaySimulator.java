/**
 * This module is about simulating Conway game of life.
 * It consists of two classes:
 *  <ul>
 *   <li>ConwayGrid : the part responsible for doing calculation</li>
 *   <li>ConwaySimulator : the part responsible of drawing</li>
 *  </ul>
 *
 * Conway Game of life will later be extended to Schelling segregation
 * and to the Immigration game.
 *
 * @author Haroun Al Mounayar
 */
package fr.Conway;

import fr.glob.Simulateur;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ConwaySimulator.
 */
public class ConwaySimulator extends Simulateur {

    /** The size of a square. */
    public int sizeOfSquare;

    /** The number of rows in the square Grid. */
    public int rows;

    /** The number of initialy alive cells. */
    public int initAlive;

    /** The half of the sizeOfSquare. */
    private int halfSquare;

    /** The list of the colors we want to use. */
    private Color[] listColors;

    /** The grid of type ConwayGrid. */
    private ConwayGrid grid;

    /** The constructor of the game of life.
     *  @param sizeOfSquare the size of a single square
     *  @param rows the number of rows
     *  @param initAlive the number of initialy Alive cells
     *  @param win the GUISimulator we want to draw on
     */
    public ConwaySimulator(final int sizeOfSquare,
                           final int rows,
                           final int initAlive,
                           final GUISimulator win) {
        this(
            sizeOfSquare,
            rows, initAlive,
            new ConwayGrid(rows, rows, initAlive),
            new Color[]{Color.WHITE, Color.BLACK},
            win);
    }

 
     /** The constructor for all grid types.
     *  @param sizeOfSquare the size of a single square
     *  @param rows the number of rows
     *  @param initAlive the number of initialy Alive cells
     *  @param grid the ConwayGrid we want to draw
     *  @param win the GUISimulator we want to draw on
     *  @param listColors the list of the color
     */
    protected ConwaySimulator(
            final int sizeOfSquare,
            final int rows,
            final int initAlive,
            final ConwayGrid grid,
            final Color[] listColors,
            final GUISimulator win) {
        super(sizeOfSquare * rows, sizeOfSquare * rows, win);
        this.sizeOfSquare = sizeOfSquare;
        this.rows = rows;
        this.initAlive = initAlive;
        this.grid = grid;
        this.listColors = listColors;
        this.win = win;
        halfSquare = sizeOfSquare / 2;
        this.grid.initialize();
    }

    public void draw_grid_line() {
        Rectangle r;
        int size = sizeOfSquare * rows;
        for (int i = 0; i <= size; i += sizeOfSquare) {
            r = new Rectangle(i, size / 2, Color.GRAY, Color.WHITE, 1, size);
            win.addGraphicalElement(r);
            r = new Rectangle(size / 2, i, Color.GRAY, Color.WHITE, size, 1); 
            win.addGraphicalElement(r);
        } 
    }
    

    public void draw_cube(int x, int y, Color c) {
        /*x and y are the coordinate of the top left*/    
        Rectangle r;
        int cube_size = sizeOfSquare-4;
        int x_center = x + halfSquare;
        int y_center = y + halfSquare;
        r = new Rectangle(x_center, y_center, c, c, cube_size, cube_size);
        win.addGraphicalElement(r);
    }

    public void draw_grid()  {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                draw_cube(j*sizeOfSquare, i*sizeOfSquare, listColors[grid.grid[j][i]]); 
            }
        }
    }


    @Override
    public String toString() {
        return ("The board of size " + sizeOfSquare*rows);
    }

    @Override
    public void next() {
        HashMap<String,ArrayList<Integer>> map = this.grid.getToChangeList();
        win.reset();
        this.grid.updateGrid(map);
        this.draw_grid();
        this.draw_grid_line();

    }

    @Override
    public void restart() {
        this.grid.reInit();
        win.reset();
        this.draw_grid();
        this.draw_grid_line();
    }
}


