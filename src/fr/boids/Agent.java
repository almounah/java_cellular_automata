package fr.boids;

import fr.glob.MyVector;
import java.awt.Color;

public class Agent {

    private MyVector position;
    private MyVector vitesse;
    private MyVector acceleration;
    private int rayon;
    private double masse = 30.0;
    private Color color;

    static public double accelMax = 5.0; // Méga camion de 2 tonnes ou F1?

    //Constructeur

    public Agent(MyVector position, int rayon, Color c) {
        this.position = position;
        this.vitesse = new MyVector(10,0);
        this.acceleration = new MyVector(0,0);
        this.rayon = rayon;
        this.color = c;
    }

    //Methodes    

    public void appliquerForce(MyVector force){
        MyVector f = new MyVector(force); //Pour ne pas changer la variable force
        f.div(masse); // 2eme loi de Newton
        System.out.println("Applique f = "+ force + " | Soit-> " + f);
        acceleration.add(f);
    }

    //Algo qui permet de rejoindre un point à l'aide de la 'steer force' de Reynolds
    public void rejoindre(MyVector cible){
        double vitMax = 10.0;
        MyVector desired = MyVector.sub(cible, position); //variation de pos = vitesse
        desired.normalize();
        desired.mult(vitMax); // Pour ne pas foncer à toutes berzingue
        MyVector steer = MyVector.sub(desired, vitesse); //'steer force' de Reynolds
        appliquerForce(steer);
    }

    //Met à jour l'accel en fct de la proximité au bords
    public void checkBounds(int w, int h, int limiteBord){
        int forceBord = 0;
        int x = (int)position.x, y = (int)position.y;
        if(x < rayon+limiteBord){
            System.out.println("GAUCHE");
            forceBord = limiteBord - (x -rayon);
            // plus on est proche, plus on est poussé fort
            appliquerForce(new MyVector(forceBord, 0));
            forceBord = 0;
        } 
        if(x > w-limiteBord-rayon){
            System.out.println("DROITE");
            forceBord = x+rayon - (w-limiteBord);
            appliquerForce(new MyVector(-forceBord, 0));
            forceBord = 0; 
        }
        if(y < rayon+limiteBord){
            System.out.println("HAUT");
            forceBord = limiteBord - (y -rayon);
            appliquerForce(new MyVector(0, forceBord));
            forceBord = 0;
        }
        if(y > h-limiteBord-rayon){
            System.out.println("BAS");
            forceBord = y+rayon - (h-limiteBord);
            appliquerForce(new MyVector(0,-forceBord));
            forceBord = 0;
        }

        if(x<0) position.x = 0;
        if(y<0) position.y = 0;
        if(x>w) position.x = w-1;
        if(y>h) position.y = h-1;
    }

    public void update(int w, int h){
        System.out.println("==========");
        System.out.println("Avant -> "+ this.toString());

        checkBounds(w,h,50);
        rejoindre(new MyVector(w/2, h/2));
        
        acceleration.limit(accelMax);//On limite l'accel max après avoir appliqué toutes les forces
        vitesse.add(acceleration);
        position.add(vitesse);

        //A la fin on multiplie par 0 (1ere loi Newton)
        System.out.println("Après -> "+ this.toString());
        acceleration.mult(0);
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


    @Override
    public String toString() {
        return
            "Agents = position=" + getPosition() + 
            ", vitesse=" + getVitesse() + 
            ", acceleration=" + getAcceleration();
    }
    
  
}
