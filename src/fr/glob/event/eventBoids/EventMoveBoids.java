/** This module is used for events in
 *  all boids simulation.
 * */
package fr.glob.event.eventBoids;

import fr.boids.AgentBoids;
import fr.boids.AgentsBoids;
import fr.glob.event.Event;
import fr.glob.event.EventManager;

/** EventMoveBoids.
 */
public class EventMoveBoids extends Event {

    /** The agent we want to modify with events. */
    protected AgentsBoids agents;

    /** The Contructor.
     * @param eventManager
     * @param agents
     * */
    public EventMoveBoids(EventManager eventManager, AgentsBoids agents) {
        super(0, eventManager);
        this.agents = agents;
    }

    /** The 2nd Contructor with datetoplay.
     * @param eventManager
     * @param agents
     * @param dateToPlay
     * */
    public EventMoveBoids(long dateToPlay, EventManager eventManager, AgentsBoids agents) {
        super(dateToPlay, eventManager);
        this.agents = agents;
    }

    /** The execute method. */
    public void execute() {
        for (AgentBoids a : agents.getAgents()) {
            a.update(agents.getWidth(), agents.getHeight(), agents.getAgents());
        }
        EventManager em = getEventManager();
        em.addEvent(new EventMoveBoids(getDateToPlay() + 1, em, agents));
    }

    /** The toString method.
     * @return a string representing the boids update
     *         with the event.
     */
    @Override
    public String toString() {
        return super.toString() + " move all boids Agents";
    }
}
