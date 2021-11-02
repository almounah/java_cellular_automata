package fr.boids;

import java.util.Iterator;
import java.util.LinkedList;
import fr.boids.Agent;
import fr.glob.MyVector;
import java.util.Random;
import java.util.Vector;
import java.awt.Color;

public class Agents {
    private LinkedList<Agent> agentsList;
    private LinkedList<Agent> copy;

    public Agents(int nbToAdd, int w, int h){
        agentsList = new LinkedList<Agent>();
        copy = new LinkedList<Agent>();
        addRandomAgents(nbToAdd, w, h);
    }

    public LinkedList<Agent> getAgents(){
        return this.agentsList;
    }

    public void reInit(){
        if(agentsList.size() != copy.size()) 
            throw new RuntimeException("reInit : Listes de taille différentes!");
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
        Color c;
        int x, y,dx, dy, maxSpeed=(int)Agent.vitMax, radius =10;
        //int radiusMin = 10, radiusMax = 15;
        Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
            Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK,};
        for (int i = 0; i < nbToAdd; i++){
            //radius = r.nextInt(radiusMax-radiusMin) + radiusMin;
            x = r.nextInt(w-radius*2) + radius; // Intervalle [radius; w-radius]
            y = r.nextInt(h-radius*2) + radius;
            dx =  r.nextInt(maxSpeed) - maxSpeed/2; //soustraction pour centrer l'intervalle en 0 
            dy = r.nextInt(maxSpeed) - maxSpeed/2;
            c = colors[i%colors.length];
            addAgent(new MyVector(x,y),new MyVector(dx,dy), radius, c);
        }
    }

    public void addAgent(MyVector position, int rayon, Color c){
        agentsList.add(new Agent(position, rayon, c));
        copy.add(new Agent(new MyVector(position), rayon, c)); //Copie de position necessaire
    }

    public void addAgent(MyVector position, MyVector vitesse, int rayon, Color c){
        agentsList.add(new Agent(position,vitesse, rayon, c));
        copy.add(new Agent(new MyVector(position), new MyVector(vitesse), rayon, c)); //Copie de position necessaire
    }
    
    public void update(int w, int h){
        for (Agent a : agentsList) a.update(w, h, agentsList);
    }
}
