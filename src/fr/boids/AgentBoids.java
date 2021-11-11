package fr.boids;

import fr.glob.MyVector;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

public class AgentBoids {

    private MyVector position;
    private MyVector vitesse;
    private MyVector acceleration;
    private int rayon;
    private double masse = 30.0;
    private Color color;
    public double portee; //rayon
    public double espacePerso; // rayon

    static public double vitMax = 5.0;
    static public double vitRepultionMax = vitMax * 1.3;
    static public double accelMax = vitMax/2; // Méga camion de 2 tonnes ou F1?
    

    //Constructeur

    public AgentBoids(MyVector position, int rayon, Color c) {
        this.position = position;
        this.vitesse = new MyVector(0,0);
        this.acceleration = new MyVector(0,0);
        this.rayon = rayon;
        portee = rayon*8;
        espacePerso = portee / 2;
        this.color = c;
    }

    public AgentBoids(MyVector position, MyVector vitesse, int rayon, Color c) {
        this.position = position;
        this.vitesse = vitesse;
        this.acceleration = new MyVector(0,0);
        this.rayon = rayon;
        this.color = c;
        portee = rayon+70.0;
        espacePerso = portee *0.8;
    }

    //Methodes    

    public void appliquerForce(MyVector force){
        MyVector f = new MyVector(force); //Pour ne pas changer la variable force
        f.div(masse); // 2eme loi de Newton
        //System.out.println("Applique f = "+ force + " | Soit-> " + f);
        acceleration.add(f);
    }

    //Algo qui permet de rejoindre un point à l'aide de la 'steer force' de Reynolds
    public void rejoindre(MyVector cible){
        MyVector desired = MyVector.sub(cible, position); //variation de pos = vitesse
        desired.normalize();
        desired.mult(vitMax); // Pour ne pas foncer à toutes berzingue
        MyVector steer = MyVector.sub(desired, vitesse); //'steer force' de Reynolds
        appliquerForce(steer);
    }

    public void graviterAutour(MyVector objPos, double objMasse){
        MyVector gravitation = MyVector.sub(objPos, position);
        double dst = gravitation.masse();
        gravitation.normalize();
        double norme= (6.67 * 10e-11 * objMasse * this.masse)/(dst*dst);
        gravitation.mult(norme);// Gmm'/ carré(d)
        appliquerForce(gravitation);
    }

    private void applyCohesion(LinkedList<AgentBoids> agents){
        MyVector cohesion = new MyVector(0,0);
        int nbVoisins = 0;
        Iterator<AgentBoids> it = agents.iterator();
        while(it.hasNext()){
            AgentBoids v = it.next();
            double dst = position.dst(v.getPosition());
            if(dst > 0 && v != this && dst<portee){
                nbVoisins++;
                cohesion.add(v.getPosition());
            }
        }
        if(nbVoisins > 0){
            cohesion.div(nbVoisins);
            rejoindre(cohesion);
        }
    }

    private void applyAlignement(LinkedList<AgentBoids> agents){
        MyVector alignement = new MyVector(0,0);
        int nbVoisins = 0;
        Iterator<AgentBoids> it = agents.iterator();
        while(it.hasNext()){
            AgentBoids v = it.next();
            double dst = position.dst(v.getPosition());
            if(dst > 0 && v != this && dst<portee){
                nbVoisins++;
                alignement.add(v.getVitesse());
            }
        }
        if(nbVoisins > 0){
            alignement.div(nbVoisins);
            alignement.normalize();
            alignement.mult(vitMax);
            MyVector steer = MyVector.sub(alignement, vitesse); //'steer force' de Reynolds
            appliquerForce(steer);
        }
    }

    private void applySeparation(LinkedList<AgentBoids> agents){
        int nbConnards = 0;
        MyVector separation = new MyVector(0,0);
        Iterator<AgentBoids> it = agents.iterator();
        while(it.hasNext()){
            AgentBoids v = it.next();
            double dst = position.dst(v.getPosition());
            if(dst > 0 && v != this && dst<espacePerso){
                MyVector diff = MyVector.sub(position,v.getPosition());
                diff.normalize();
                diff.div(dst);
                separation.add(diff);
                nbConnards++;
            }
        }
        if(nbConnards > 0){
            separation.div(nbConnards);
            separation.normalize();
            separation.mult(vitRepultionMax);
            MyVector steer = MyVector.sub(separation, vitesse);
            appliquerForce(steer);
        }
    }

    private void mouvementBoidsUnits(LinkedList<AgentBoids> agents){
        applyCohesion(agents);
        applyAlignement(agents);
        applySeparation(agents);
    }

    private void mouvementBoids(LinkedList<AgentBoids> agents){
        int nbVoisins = 0;
        int nbConnards = 0;
        MyVector cohesion = new MyVector(0,0);
        MyVector alignement = new MyVector(0,0);
        MyVector separation = new MyVector(0,0);
        Iterator<AgentBoids> it = agents.iterator();
        while(it.hasNext()){
            AgentBoids v = it.next();
            double dst = position.dst(v.getPosition());
            if(dst > 0 && v != this && dst<portee){
                nbVoisins++;
                cohesion.add(v.getPosition());
                alignement.add(v.getVitesse());
                if(dst < espacePerso){
                    MyVector diff = MyVector.sub(position,v.getPosition());
                    diff.normalize();
                    diff.div(dst);
                    separation.add(diff);
                    nbConnards++;
                }
            }
        }
        if(nbVoisins > 0){
            cohesion.div(nbVoisins);
            rejoindre(cohesion);
    
            alignement.div(nbVoisins);
            alignement.normalize();
            alignement.mult(vitMax);
            MyVector steer = MyVector.sub(alignement, vitesse); //'steer force' de Reynolds
            appliquerForce(steer);
        }
        if(nbConnards > 0){
            separation.div(nbConnards);
            separation.normalize();
            separation.mult(vitRepultionMax);
            MyVector steer = MyVector.sub(separation, vitesse);
            appliquerForce(steer);
        }
        
    }

    //Met à jour l'accel en fct de la proximité au bords
    public void checkBounds(int w, int h, int limiteBord){
        int x = (int)position.x, y = (int)position.y;
        if(x < rayon+limiteBord){
            //System.out.println("GAUCHE");
            appliquerForce(new MyVector(vitMax, 0));
        } 
        if(x > w-limiteBord-rayon){
            //System.out.println("DROITE");
            appliquerForce(new MyVector(-vitMax, 0));
        }
        if(y < rayon+limiteBord){
            //System.out.println("HAUT");
            appliquerForce(new MyVector(0, vitMax));
        }
        if(y > h-limiteBord-rayon){
            //System.out.println("BAS");
            appliquerForce(new MyVector(0,-vitMax));
        }

        if(x<0) position.x = 0;
        if(y<0) position.y = 0;
        if(x>w) position.x = w-1;
        if(y>h) position.y = h-1;
    }

    public void update(int w, int h, LinkedList<AgentBoids> agents){
        //System.out.println("==========");
        //System.out.println("Avant -> "+ this.toString());

        checkBounds(w,h, (w+h)/20); //limiteBord = moyenne/10
        mouvementBoids(agents);
        //mouvementBoidsUnits(agents);
        //rejoindre(new MyVector(w/2, h/2));
        //graviterAutour(new MyVector(w/2, h/2), 300);
        
        acceleration.limit(accelMax);//On limite l'accel max après avoir appliqué toutes les forces
        vitesse.add(acceleration);
        position.add(vitesse);

        //A la fin on multiplie par 0 (1ere loi Newton)
        //System.out.println("Après -> "+ this.toString());
        acceleration.x = 0.0; acceleration.y = 0.0;
    }

    // Getters


    public MyVector getPosition() {
        return this.position;
    }

    public MyVector getVitesse() {
        return this.vitesse;
    }

    public MyVector getAcceleration() {
        return this.acceleration;
    }

    public int getRayon() {
        return this.rayon;
    }

    public Color getColor() {
        return this.color;
    }


    public String me() {
        return
            "Color:" + getColor();
    }


    @Override
    public String toString() {
        return
            "Agents = position=" + getPosition() + 
            ", vitesse=" + getVitesse() + 
            ", acceleration=" + getAcceleration();
    }
    
  
}
