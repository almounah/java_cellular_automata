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

    /** Draw the line of the grid in Gray. */
    public void drawGridLine() {
        Rectangle r;
        int size = sizeOfSquare * rows;
        for (int i = 0; i <= size; i += sizeOfSquare) {
            // Until we reach size we keep drawing a horizontal rectangle
            // and a vertical one.
            // We give the center of the rectangle and its size.
            r = new Rectangle(i, size / 2, Color.darkGray, Color.WHITE,
                              0, size);
            win.addGraphicalElement(r);
            r = new Rectangle(size / 2, i, Color.darkGray, Color.WHITE,
                              size, 0);
            win.addGraphicalElement(r);
        }
    }

    /** Draw a cube representing a point in the grid.
     *  @param x is the x coordinate
     *  @param y is the u coordinate
     *  @param c is the color of the cube
     */
    public void drawCube(final int x, final int y, final Color c) {
        /*x and y are the coordinate of the top left*/
        Rectangle r;
        int cubeSize = sizeOfSquare - 2;
        int xCenter = x + halfSquare;
        int yCenter = y + halfSquare;
        r = new Rectangle(xCenter, yCenter, c, c, cubeSize, cubeSize);
        win.addGraphicalElement(r);
    }

    /** Go through the grid and draw everycube. */
    public void drawGrid()  {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                // We use enumerated type for the colors.
                // Eg: say listColors = {white, red, blue}
                // we map the color to a fictionnal list of state
                // state = {0, 1, 2}. A cell in the grid can either have
                // 0, 1 or 2 value.
                // So to get the correct color of a cell we just do :
                // listColors[cell_state].
                drawCube(j * sizeOfSquare, i * sizeOfSquare,
                         listColors[grid.grid[j][i]]);
            }
        }
    }

    /** Return a string giving the size of the board.
     *  @return the string.
     */
    @Override
    public String toString() {
        return ("The board of size " + sizeOfSquare * rows);
    }

    /** Implement the next method. */
    @Override
    public void next() {
        HashMap<String, ArrayList<Integer>> map = this.grid.getToChangeList();
        win.reset();
        this.grid.updateGrid(map);
        this.drawGrid();
        this.drawGridLine();
    }

    /** Implement the restart method. */
    @Override
    public void restart() {
        this.grid.reInit();
        win.reset();
        this.drawGrid();
        this.drawGridLine();
    }
}
