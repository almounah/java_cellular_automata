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

import fr.Conway.ConwaySimulator;
import gui.GUISimulator;

import java.awt.Color;

/**
 * ImmigrationSimulator.
 *   <li>ImmigrationSimulator : the part responsible of drawing</li>
 */
public class ImmigrationSimulator extends ConwaySimulator {

    /** The number of state we need in the game. */
    public int statesNumber;

    /** The constructor of the simulator.
     *  @param sizeOfSquare the size of a single square
     *  @param rows the number of rows
     *  @param listColor the list of the color
     *  @param win the GUISimulator we want to draw on
     */
    public ImmigrationSimulator(final int sizeOfSquare,
                                final int rows,
                                final Color[] listColor,
                                final GUISimulator win) {
        super(sizeOfSquare,
              rows,
              0,
              new ImmigrationGrid(rows,
                                  rows,
                                  listColor.length),
              listColor, win);

        this.statesNumber = listColor.length;
    }

}
