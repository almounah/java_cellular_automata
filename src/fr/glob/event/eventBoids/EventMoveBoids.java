package fr.glob.event.eventBoids;

import fr.boids.*;
import fr.glob.event.*;

public class EventMoveBoids extends Event {
    protected AgentsBoids agents;

    public EventMoveBoids(EventManager eventManager, AgentsBoids agents) {
        super(0, eventManager);
        this.agents = agents;
    }

    public EventMoveBoids(long dateToPlay, EventManager eventManager, AgentsBoids agents) {
        super(dateToPlay, eventManager);
        this.agents = agents;
    }

    public void execute(){
        for (AgentBoids a : agents.getAgents()) {
            a.update(agents.getWidth(), agents.getHeight(), agents.getAgents());
        }
        EventManager em = getEventManager();
        em.addEvent(new EventMoveBoids(getDateToPlay()+1, em, agents));
    }

    @Override
    public String toString() {
        return super.toString() + " move all boids Agents";
    }
}
