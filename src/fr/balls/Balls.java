package fr.balls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Color;
import fr.glob.MyVector;

public class Balls {
    private ArrayList<Ball> ballsList;
    private ArrayList<Ball> ballsCopy;

    public Balls(int nbToAdd, int w, int h){
        ballsList = new ArrayList<Ball>();
        ballsCopy = new ArrayList<Ball>();
        addRandomBalls(nbToAdd, w, h);
    }

    public ArrayList<Ball> getballsList(){
        return ballsList;
    }

    public void reInit(){
        if(ballsCopy.size() != ballsList.size()) throw new RuntimeException("reInit : Listes de taille différentes!");
        Iterator<Ball> itL = ballsList.iterator();
        Iterator<Ball> itC = ballsCopy.iterator();
        Ball b, bc; MyVector v, vc;
        while(itL.hasNext() && itC.hasNext()){
            b = itL.next();
            bc = itC.next();
            v = b.getPosition();
            vc = bc.getPosition();
            v.x = vc.x; v.y = vc.y; //Reset pos
            v = b.getvitesse();
            vc = bc.getvitesse();
            v.x = vc.x; v.y = vc.y; //Reset velocité
        }
    }

    public void addRandomBalls(int nbToAdd, int w, int h){
        Random r = new Random();
        int x, y, dx, dy, radius;
        Color c;
        int maxSpeed = (h+w)/100; // moyenne/50
        int radiusMin = 20, radiusMax = 30;        
        Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
            Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK,};
        
        for (int i = 0; i < nbToAdd; i++){
            radius = r.nextInt(radiusMax-radiusMin) + radiusMin;
            x = r.nextInt(w-radius) + radius; // Intervalle [radius; w-radius]
            y = r.nextInt(h-radius) + radius;
            dx =  r.nextInt(maxSpeed) - maxSpeed/2; //soustraction pour centrer l'intervalle en 0 
            dy = r.nextInt(maxSpeed) - maxSpeed/2;
            c = colors[i%colors.length];
            addBall(new MyVector(x, y), new MyVector(dx, dy), c, radius);
        }
    }

    public void addBall(MyVector position, MyVector vitesse, Color c, int rayon){
        ballsList.add(new Ball(position, vitesse, c, rayon));
        ballsCopy.add(new Ball(new MyVector(position), new MyVector(vitesse), c, rayon));
    }

    public void translate(int w, int h){
        for (Ball b : ballsList) b.translate(w, h);
    }

    public String toStringCP(){
        String str = "BallsCopy ("+this.ballsList.size()+"): ";
        for (Ball b : ballsCopy) str += "["+ b.toString()+"]";
        return str;
    }

    @Override
    public String toString(){
        String str = "BallsList ("+this.ballsList.size()+"): ";
        for (Ball b : ballsList) str += "["+ b.toString() +"]";
        return str;
    }

}