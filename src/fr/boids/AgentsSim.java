/**
 * This module is about simulating boids.
 * It consists of two classes:
 *  <ul>
 *   <li>AgentBoids : the part responsible for doing calculation
 *                    for 1 boids </li>
 *   <li>AgentsBoids : the part responsible of doing calculations
 *                    for a list of boids.</li>
 *   <li>AgentsSim : for drawing </li>
 *  </ul>
 *
 */
package fr.boids;

import gui.GUISimulator;
import gui.Oval;

import java.awt.Color;

import fr.glob.MyVector;
import fr.glob.Simulateur;
import fr.glob.event.eventBoids.EventMoveBoids;

/**AgentSim.
 */
public class AgentsSim extends Simulateur {

    /** The list of boids Agent. */
    private AgentsBoids agents;

    /** The constructor of the simulator.
     * @param width : the width
     * @param height : the height
     * @param nbAgents : the number of boids we want.
     * @param win : where we want to draw.
     * */
    public AgentsSim(final int width,
                     final int height,
                     final int nbAgents,
                     final GUISimulator win) {
        super(width, height, win);
        this.agents = new AgentsBoids(nbAgents, width, height);
        em.addEvent(new EventMoveBoids(em, this.agents));
        em.setInitialStatus();
    }

    /** Draw the boids. */
    private void drawAgents() {
        boolean printSpeed = true;
        for (AgentBoid a : agents.getAgents()) {
            win.addGraphicalElement(
                new Oval(
                    (int) a.getPosition().x,
                    (int) a.getPosition().y,
                    a.getColor(),
                    a.getColor(),
                    a.getRayon() * 2));
            if (printSpeed) {
                MyVector seeSpeed = new MyVector(a.getVitesse().x,
                                                 a.getVitesse().y);
                seeSpeed.normalize(); seeSpeed.mult(a.getRayon() * 2);
                win.addGraphicalElement(
                    new Oval(
                        (int) (a.getPosition().x + seeSpeed.x),
                        (int) (a.getPosition().y + seeSpeed.y),
                        Color.white,
                        Color.white,
                        a.getRayon() / 2));
            }
        }
    }

    /** The next method. */
    @Override
    public void next() {
        em.next();
        win.reset();
        drawEdge(Color.white);
        drawAgents();
    }

    /** The restart method. */
    @Override
    public void restart() {
        em.restart();
        agents.reInit();
        win.reset();
        drawEdge(Color.white);
        drawAgents();
    }
}
