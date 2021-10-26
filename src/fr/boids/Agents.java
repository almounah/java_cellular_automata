package fr.boids;

import java.util.Iterator;
import java.util.LinkedList;
import boids.Agent;
import fr.glob.MyVector;
import java.util.Random;
import java.awt.Color;

public class Agents {
    private LinkedList<Agent> agentsList;
    private LinkedList<Agent> copy;

    public Agents(int nbToAdd, int w, int h){
        agentsList = new LinkedList<Agents>();
        copy = new LinkedList<Agents>();
        addRandomAgents(nbToAdd, w, h);
    }

    public LinkedList<Agent> getAgents(){
        return this.agentsList;
    }

    public void reInit(){
        if(agentsList.size() != copy.size()) throw new RuntimeException("reInit : Listes de taille différentes!");
        Iterator<Agent> itL = agentsList.iterator();
        Iterator<Agent> itC = copy.iterator();
        Agent a,c;
        while(itL.hasNext() && itC.hasNext()){
            a = itL.next();
            c = itC.next();
            a.getPosition().setXY(c.getPosition());
            a.getVitesse().setXY(c.getVitesse());
            a.getAcceleration().setXY(c.getAcceleration());
        }
    }

    public void addRandomAgents(int nbToAdd, int w, int h){
        Random r = new Random();
        int x, y, radius;
        int radiusMin = 10, radiusMax = 15;
        for (int i = 0; i < nbToAdd; i++){
            radius = r.nextInt(radiusMax-radiusMin) + radiusMin;
            x = r.nextInt(w-radius) + radius; // Intervalle [radius; w-radius]
            y = r.nextInt(h-radius) + radius;
            addAgent(new MyVector(x,y), radius);
        }
    }

    public void addAgent(MyVector position, int rayon){
        agentsList.add(new Agent(position, rayon));
        copy.add(new Agent(position, rayon));
    }
    
    public void update(int w, int h){
        for (Agent a : agentsList) a.update(w, h);
    }
}
