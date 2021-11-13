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

import java.awt.Color;

import fr.Conway.ConwaySimulator;
import gui.GUISimulator;

/**
 * SchellingSimulator.
 */
public class SchellingSimulator extends ConwaySimulator{

    /** The number of state in the immigration game. */
    public int numberColor;

    /** The seuil for a cell to move. */
    public int seuil;

    /** Constructor of the grid.
     *
     * @param sizeOfSquare the size of a single square
     * @param rows is the number of rows
     * @param initAlive is the number of random cell initialy colored
     *                  The cell choice is completely random. So if you put
     *                  for example initAlive = 2 you can have the same cell
     *                  colored twice. Thus we are sure that we have enough
     *                  empty cells to begin with.
     * @param seuil is the seuil
     * @param listColor the list of the color
     * @param win the GUISimulator we want to draw on
     */
    public SchellingSimulator(final int sizeOfSquare, 
                              final int rows, Color[] list_color,
                              final int seuil,
                              final int initAlive,
                              final GUISimulator win) {

        super(sizeOfSquare,
              rows,
              initAlive,
              new SchellingGrid(rows,
                                rows,
                                initAlive,
                                seuil,
                                list_color.length),
              list_color,
              win);

        this.numberColor = list_color.length;
        this.seuil = seuil;
    }
}
