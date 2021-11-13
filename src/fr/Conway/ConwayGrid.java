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


import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ConwayGrid.
 */
public class ConwayGrid {

    /** The number of column in the grid. */
    public int column;

    /** The number of rows in the grid. */
    public int rows;

    /** The number of initialy alive cells. */
    public int initAlive;

    /** The grid, a table of table. */
    public int[][] grid;

    /** A copy of the inital grid. */
    public int[][] gridCopy;

    /** Constructor of the grid.
     *
     * @param column is the number of column
     * @param rows is the number of rows
     * @param initAlive is the number of initialy alive cells
     */
    public ConwayGrid(final int column, final int rows, final int initAlive) {
        this.column = column;
        this.rows = rows;
        this.initAlive = initAlive;
        this.grid = new int[column][rows];
        this.gridCopy = new int[column][rows];
    }

    /** Initialize the grid and its copy by putting 0 and 1 in its cases. */
    public void initialize() {
        // Everything is dead, so 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = 0;
                gridCopy[j][i] = 0;
            }
        }

        // Some are randomly alive, so 1
        int x;
        int y;
        for (int i = 0; i < initAlive; i++) {
            x = ThreadLocalRandom.current().nextInt(0, rows);
            y = ThreadLocalRandom.current().nextInt(0, column);
            grid[y][x] = 1;
            gridCopy[y][x] = 1;
        }
    }

    /** Restitute the initial grid saved in grod_copy. */
    public void reInit() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = gridCopy[j][i];
            }
        }
    }

    /** Tell us if you should update a cell.
     *
     * @param x is the x coordinate of the cell
     * @param y is the y coordinate of the cell
     * @return Boolean True if we should update the cell and False if not
     */
    public boolean updateCell(final int x, final int y) {
        int xBefore;
        int xAfter;
        int yBefore;
        int yAfter;

        // We are using a circular grid.
        // So we have to use modulo.
        // We are not using if statements to improve the performance.
        xBefore = (rows + x - 1) % rows;
        xAfter = (x + 1) % rows;
        yBefore = (column + y - 1) % column;
        yAfter = (y + 1) % column;

        // We just increment the neighboorSum by adding the value of the grid.
        int neighboorSum = 0;
        neighboorSum += grid[yBefore][xBefore] + grid[yBefore][x]
                        + grid[yBefore][xAfter];
        neighboorSum += grid[y][xBefore] + grid[y][xAfter];
        neighboorSum += grid[yAfter][xBefore] + grid[yAfter][x]
                        + grid[yAfter][xAfter];

        // Here we apply conway rules
        if (neighboorSum == 3 && grid[y][x] == 0) {
            return true;
        } else if ((neighboorSum > 3 || neighboorSum < 2) && grid[y][x] == 1) {
            return true;
        }

        return false;
    }

    /** Return the map of the coordinate of the cells to change.
     *  @return a dictionnary having two list of integer (X and Y)
     *
     *  When updating our list we juts change the cells that have changed.
     *  Thus we gain in performance instead of looking for the element.
     */
    public HashMap<String, ArrayList<Integer>> get_to_change_list() {
        ArrayList<Integer> list_tochange_x = new ArrayList<Integer>();
        ArrayList<Integer> list_tochange_y = new ArrayList<Integer>();

        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

        map.put("x_coord", list_tochange_x);
        map.put("y_coord", list_tochange_y);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                 if (updateCell(i, j)) {
                     list_tochange_x.add(0, i);
                     list_tochange_y.add(0, j);
                 }
            }
        }

        return map;
    }

    /** Update the grid by just checking the coordinate of the changed cell.
     *  @param map is the map giving the
     *      list of the coordinate X with the key x_coord
     *      list of the coordinate Y with the key y_coord
     */
    public void update_grid(final HashMap<String, ArrayList<Integer>> map) {

        ArrayList<Integer> list_tochange_x = map.get("x_coord");
        ArrayList<Integer> list_tochange_y = map.get("y_coord");

        for (int i = 0; i < list_tochange_x.size(); i++) {
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)]++;
            grid[list_tochange_y.get(i)][list_tochange_x.get(i)] %= 2;
        }
    }


}
