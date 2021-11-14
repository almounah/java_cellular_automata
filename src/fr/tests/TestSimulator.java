/** This Module is used to test stuff.
 * Here we test our simulation.
 */

package fr.tests;

import fr.balls.BallsSimulator;
import fr.Conway.ConwaySimulator;
import fr.Schelling.SchellingSimulator;
import fr.Immigration.ImmigrationSimulator;
import fr.boids.AgentsSim;
import gui.GUISimulator;
import java.awt.Color;

/**TestSimulator.
 * Here we test our simulation.
 */
public class TestSimulator {

    /** Our main.
     * @param args : for internal uses only (see makefile) */
    public static void main(final String[] args) {

        // Change these value if you want to change
        // the size of the screen.
        int w = 1000;
        int h = 800;
        int nbAgents = 20;
        GUISimulator gui = new GUISimulator(w, h, Color.BLACK);

        if (args[0].equals("boids")) {
            gui.setSimulable(new AgentsSim(w, h, nbAgents, gui));
        }

        if (args[0].equals("balls")) {
            // Démonstration de l'utilisation des Events
            // (Rouges->rapides, Bleues ->lentes)
            boolean useBlueRedBalls = false;
            gui.setSimulable(new BallsSimulator(w,
                                                h,
                                                nbAgents,
                                                gui,
                                                useBlueRedBalls));
        }

        if (args[0].equals("conway")) {
            // Change these value if you want
            // to modify conway game of life simulation.
            int sizeOfSquare = 20;
            int numberOfSquare = 20;
            int initAlive = 50;

            gui.setSimulable(new ConwaySimulator(sizeOfSquare,
                                                 numberOfSquare,
                                                 initAlive,
                                                 gui));
        }

        if (args[0].equals("immigration")) {
            // Change these value if you want
            // to modify immigration game simulation.
            int sizeOfSquare = 20;
            int numberOfSquare = 30;
            Color[] listColor = new Color[]{Color.WHITE, Color.LIGHT_GRAY,
                                            Color.GRAY, Color.BLACK};

            gui.setSimulable(new ImmigrationSimulator(sizeOfSquare,
                                                      numberOfSquare,
                                                      listColor,
                                                      gui));
        }

        if (args[0].equals("schelling")) {
            // Change these value if you want
            // to modify schelling segregation simulation.
            int sizeOfSquare = 20;
            int numberOfSquare = 30;
            int initAlive = 2000;
            int seuil = 3;
            Color[] listColor = new Color[]{Color.WHITE, Color.RED,
                                            Color.BLUE, Color.GREEN};

            gui.setSimulable(new SchellingSimulator(sizeOfSquare,
                                                    numberOfSquare,
                                                    listColor,
                                                    seuil,
                                                    initAlive,
                                                    gui));
        }
    }
}
