package boids;

import fr.glob.MyVector;
import java.awt.Color;

public class Agent {

    private MyVector position;
    private MyVector vitesse;
    private MyVector acceleration;
    private float rayon;
    private float masse = 1;
    private Color color;

    static public double vitMax = 10.0;
    static public double accelMax = 1.0;

    //Constructeur

    public Agent(MyVector position, float rayon, Color c) {
        this.position = position;
        this.vitesse = new MyVector(0,0);
        this.acceleration = new MyVector(0,0);
        this.rayon = rayon;
        this.color = c;
    }

    public Agent(double x, double y, float rayon, Color c) {
        this.position = new MyVector(x,y);
        this.vitesse = new MyVector(0,0);
        this.acceleration = new MyVector(0,0);
        this.rayon = rayon;
        this.color = c;
    }

    //Methodes    

    public void appliquerForce(MyVector force){
        MyVector f = new MyVector(force); //Pour ne pas changer la variable force
        f.div(masse); // 2eme loi de Newton
        acceleration.add(force);
    }

    //Algo qui permet de rejoindre un point à l'aide de la 'steer force' de Reynolds
    public void rejoindre(MyVector cible){
        MyVector desired = MyVector.sub(cible, position);
        desired.normalize();
        desired.mult(vitMax); // Pour ne pas foncer à toutes berzingue
        MyVector steer = MyVector.sub(desired, vitesse); //'steer force' de Reynolds
        steer.limit(accelMax);
        appliquerForce(steer);
    }

    //Met à jour l'accel en fct de la proximité au bords
    public void checkBounds(int w, int h, int limiteBord){
        int forceBord = 0;
        if(position.x < rayon+limiteBord){
            forceBord = limiteBord - (position.x -rayon);
            // plus on est proche, plus on est poussé fort
            appliquerForce(new MyVector(forceBord, 0));
            forceBord = 0;
        } 
        if(position.x > w-limiteBord-rayon){
            forceBord = position.x+rayon - (w-limiteBord);
            appliquerForce(new MyVector(-forceBord, 0));
            forceBord = 0;
        }
        if(position.y < rayon+limiteBord){
            forceBord = limiteBord - (position.y -rayon);
            appliquerForce(new MyVector(0, forceBord));
            forceBord = 0;
        }
        if(position.y < h-limiteBord-rayon){
            forceBord = position.y+rayon - (h-limiteBord);
            appliquerForce(new MyVector(0,-forceBord));
            forceBord = 0;
        }
    }

    public void update(int w, int h){
        checkBounds(w,h,25);
        
        acceleration.add(new MyVector(1, 5));// Vent léger a droite + gravité

        vitesse.add(acceleration);
        position.add(vitesse);
        //A la fin on multiplie par 0 (1ere loi Newton)
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

    public float getRayon() {
        return this.rayon;
    }

    public Color getColor() {
        return this.color;
    }


  
}
