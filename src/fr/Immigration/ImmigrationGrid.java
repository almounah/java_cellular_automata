/**
 * This module is about simulating the Immigration game.
 * It consists of two classes:
 *  <ul>
 *   <li>ImmigrationGrid : the part responsible for doing calculation</li>
 *   <li>ImmigrationSimulator : the part responsible of drawing</li>
 *  </ul>
 *
 * Note that ImmigrationGrid extends ConwayGrid and
 * that ImmigrationSimulator extends ConwaySimulator.
 *
 */
package fr.Immigration;

import fr.Conway.ConwayGrid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


/**
 * ImmigrationGrid.
 *   <li>ImmigrationGrid : the part responsible for doing calculation</li>
 */
public class ImmigrationGrid extends ConwayGrid {

    /** The number of state in the immigration game. */
    public int numberState;

    /** The maximum number of neighboors allowed. */
    private static final int MAX_NEIGHBOORS = 3;

    /** Constructor of the grid.
     *
     * @param column is the number of column
     * @param rows is the number of rows
     * @param numberState is the number of state
     */
    public ImmigrationGrid(final int rows,
                           final int column,
                           final int numberState) {
        // In the immigration game the concept of
        // a dead or alive cell is vague.
        // So we put 0 initialy alive.
        // Later we just have to change the initialize
        // function.
        super(column, rows, 0);
        this.numberState = numberState;
    }

    /** Tell us if you should update a cell.
     * We override update cell because the rule have changed.
     * @param x is the x coordinate of the cell
     * @param y is the y coordinate of the cell
     * @return Boolean True if we should update the cell and False if not
     */
    @Override
    public boolean updateCell(final int x, final int y) {
        int xBefore;
        int xAfter;
        int yBefore;
        int yAfter;

        // We create a list of neighboor.
        // In this list we are going to put the number
        // of neighboor of a specific state.
        int[] listNeighboor = new int[numberState];

        // First we initialize it to 0.
        for (int i = 0; i < listNeighboor.length; i++) {
            listNeighboor[i] = 0;
        }

        // We do the modulo to have a circular grid
        xBefore = (rows + x - 1) % rows;
        xAfter = (x + 1) % rows;
        yBefore = (column + y - 1) % column;
        yAfter = (y + 1) % column;

        // Then we just increment each neighboor.
        listNeighboor[grid[yBefore][xBefore]]++;
        listNeighboor[grid[yBefore][x]]++;
        listNeighboor[grid[yBefore][xAfter]]++;
        listNeighboor[grid[y][xBefore]]++;
        listNeighboor[grid[y][xAfter]]++;
        listNeighboor[grid[yAfter][xBefore]]++;
        listNeighboor[grid[yAfter][x]]++;
        listNeighboor[grid[yAfter][xAfter]]++;

        // Here we apply the rule of the Immigration game.
        if (listNeighboor[(grid[y][x] + 1) % numberState] >= MAX_NEIGHBOORS) {
            return true;
        }
        return false;
    }

    /** Initialize the grid and its copy by putting each cell at a state.
     *  We override it because the rule have changed.
     *  At the beginning each cell is at a random state.
     */
    @Override
    public void initialize() {
        // Everything is randomly at a state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = ThreadLocalRandom.current().nextInt(0, numberState);
                gridCopy[j][i] = grid[j][i];
            }
        }
    }

    /** Update the grid by just checking the coordinate of the changed cell.
     *  We need to override because the rule have changed.
     *  Instead of just 0 and 1 a cell can go to 4 for example.
     *  @param map is the map giving the
     *      list of the coordinate X with the key x_coord
     *      list of the coordinate Y with the key y_coord
     */
    @Override
    public void updateGrid(final HashMap<String, ArrayList<Integer>> map) {

        ArrayList<Integer> listToChangeX = map.get("x_coord");
        ArrayList<Integer> listToChangeY = map.get("y_coord");
        for (int i = 0; i < listToChangeX.size(); i++) {
            grid[listToChangeY.get(i)][listToChangeX.get(i)]++;
            grid[listToChangeY.get(i)][listToChangeX.get(i)] %= numberState;
        }
    }

}
