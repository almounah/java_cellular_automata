package fr.boids;

import java.util.Iterator;
import java.util.LinkedList;
import fr.boids.AgentBoids;
import fr.glob.MyVector;
import java.util.Random;
import java.util.Vector;
import java.awt.Color;

public class AgentsBoids {
    private LinkedList<AgentBoids> agentsList;
    private LinkedList<AgentBoids> copy;
    private int w,h;
    // {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
    //     Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK};
    public static Color[] colors = {Color.RED};

    public AgentsBoids(int nbToAdd, int w, int h){
        agentsList = new LinkedList<AgentBoids>();
        copy = new LinkedList<AgentBoids>();
        this.w = w; this.h = h;
        addRandomAgents(nbToAdd);
    }

    public LinkedList<AgentBoids> getAgents(){
        return this.agentsList;
    }
    public int getWidth() {
        return this.w;
    }
    public int getHeight() {
        return this.h;
    }

    public void reInit(){
        if(agentsList.size() != copy.size()) 
            throw new RuntimeException("reInit : Listes de taille différentes!");
        Iterator<AgentBoids> itL = agentsList.iterator();
        Iterator<AgentBoids> itC = copy.iterator();
        AgentBoids a,c;
        while(itL.hasNext() && itC.hasNext()){
            a = itL.next();
            c = itC.next();
            a.getPosition().setXY(c.getPosition());
            a.getVitesse().setXY(c.getVitesse());
            a.getAcceleration().setXY(c.getAcceleration());
        }
    }

    public void addRandomAgents(int nbToAdd){
        Random r = new Random();
        Color c;
        int x, y,dx, dy, maxSpeed=(int)AgentBoids.vitMax, radius = 5;
        //int radiusMin = 10, radiusMax = 15;
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
        agentsList.add(new AgentBoids(position, rayon, c));
        copy.add(new AgentBoids(new MyVector(position), rayon, c)); //Copie de position necessaire
    }

    public void addAgent(MyVector position, MyVector vitesse, int rayon, Color c){
        agentsList.add(new AgentBoids(position,vitesse, rayon, c));
        copy.add(new AgentBoids(new MyVector(position), new MyVector(vitesse), rayon, c)); //Copie de position necessaire
    }
    
    public void update(){
        for (AgentBoids a : agentsList) a.update(w, h, agentsList);
    }
}
