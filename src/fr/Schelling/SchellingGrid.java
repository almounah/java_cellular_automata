/**
 * This module is about simulating the Schelling segregation.
 * It consists of two classes:
 *  <ul>
 *   <li>SchellingGrid : the part responsible for doing calculation</li>
 *   <li>SchellingSimulator : the part responsible of drawing</li>
 *  </ul>
 *
 * Note that SchellingGrid extends ConwayGrid and
 * that SchellingSimulator extends ConwaySimulator.
 *
 * @author Haroun Al Mounayar
 */
package fr.Schelling;

import fr.Conway.ConwayGrid;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

/**
 * ShellingGrid.
 *   <li>SchellingGrid : the part responsible for doing calculation</li>
 */
public class SchellingGrid extends ConwayGrid {

    /** The seuil for a cell to move. */
    public int seuil;

    /** The number of state in the immigration game. */
    public int numberColor;

    /** The array of the x coordinate of the empty cases. */
    ArrayList<Integer> listEmptyX;
    /** The array of the y coordinate of the empty cases. */
    ArrayList<Integer> listEmptyY;


    /** Constructor of the grid.
     *
     * @param column is the number of column
     * @param rows is the number of rows
     * @param initAlive is the number of random cell initialy colored
     *                  The cell choice is completely random. So if you put
     *                  for example initAlive = 2 you can have the same cell
     *                  colored twice. Thus we are sure that we have enough
     *                  empty cells to begin with.
     * @param seuil is the seuil
     * @param numberColor is the number of colors we want
     */
    public SchellingGrid(final int column,
                         final int rows,
                         final int initAlive,
                         final int seuil,
                         final int numberColor) {
        super(column,
              rows,
              initAlive);

        this.seuil = seuil;
        this.numberColor = numberColor;
        // We initialize the lists of empty cases.
        this.listEmptyX = new ArrayList<Integer>();
        this.listEmptyY = new ArrayList<Integer>();
    }

    /** We initialize the list of empty cases. */
    public void intitializeEmpty() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[j][i] == 0) {
                    listEmptyX.add(0, i);
                    listEmptyY.add(0, j);
                }
            }
        }
    }


    /** Tell us if you should update a cell.
     * We override update cell because the rule have changed.
     * @param x is the x coordinate of the cell
     * @param y is the y coordinate of the cell
     * @return Boolean True if we should update the cell and False if not
     */
    @Override
    public boolean updateCell(final int x, final int y) {
        if (grid[y][x] == 0) {
            return false;
        }

        int xBefore;
        int xAfter;
        int yBefore;
        int yAfter;
        int[] listNeighboor = new int[numberColor];
        for (int i = 0; i < listNeighboor.length; i++) {
            listNeighboor[i] = 0;
        }

        xBefore = (rows + x - 1) % rows;
        xAfter = (x + 1) % rows;
        yBefore = (column + y - 1) % column;
        yAfter = (y + 1) % column;

        listNeighboor[grid[yBefore][xBefore]]++;
        listNeighboor[grid[yBefore][x]]++;
        listNeighboor[grid[yBefore][xAfter]]++;
        listNeighboor[grid[y][xBefore]]++;
        listNeighboor[grid[y][xAfter]]++;
        listNeighboor[grid[yAfter][xBefore]]++;
        listNeighboor[grid[yAfter][x]]++;
        listNeighboor[grid[yAfter][xAfter]]++;

        int numberNeighboor = 0;
        for (int i = 0; i < numberColor; i++) {
            numberNeighboor += listNeighboor[i];
        }

        numberNeighboor -= (listNeighboor[grid[y][x]] + listNeighboor[0]);

        if (numberNeighboor > seuil) {
            return true;
        }
        return false;
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
        int size = listToChangeX.size();
        int xEmpty;
        int yEmpty;
        int xCurrent;
        int yCurrent;
        for (int i = 0; i < size; i++) {
            // We get the first empty place.
            xEmpty = listEmptyX.get(0);
            yEmpty = listEmptyY.get(0);

            xCurrent = listToChangeX.get(i);
            yCurrent = listToChangeY.get(i);

            // We remove it from the list of empty case
            listEmptyX.remove(0);
            listEmptyY.remove(0);

            // We exchange the two cells
            grid[yEmpty][xEmpty] = grid[yCurrent][xCurrent];
            grid[yCurrent][xCurrent] = 0;

            // We add the current cell to the empty list
            // because after the exchange it becomes empty.
            listEmptyX.add(xCurrent);
            listEmptyY.add(yCurrent);

            // And we don't forget to make the cell
            // empty in the grid.
            listToChangeX.add(xEmpty);
            listToChangeY.add(yEmpty);
        }
    }

    /** Initialize the grid and its copy by putting each cell at a color.
     *  We override it because the rule have changed.
     *  At the beginning each cell is at a random state.
     */
    @Override
    public void initialize() {
        // Everything is dead, so 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = 0;
                gridCopy[j][i] = 0;
            }
        }

        // Some are randomly colored
        int x;
        int y;
        for (int i = 0; i < initAlive; i++) {
            x = ThreadLocalRandom.current().nextInt(0, rows);
            y = ThreadLocalRandom.current().nextInt(0, column);
            grid[y][x] = ThreadLocalRandom.current().nextInt(0, numberColor);
            gridCopy[y][x] = grid[y][x];
        }

        // We initialize the empty list
        intitializeEmpty();
    }

    /** In reInit we have to add two lines to
     *  reinitialize the list of empty cases.
     */
    @Override
    public void reInit() {
        super.reInit();
        listEmptyX = new ArrayList<Integer>();
        listEmptyY = new ArrayList<Integer>();
        intitializeEmpty();
    }
}
