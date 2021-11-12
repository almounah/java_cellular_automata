package fr.balls;

import java.awt.Point;
import java.awt.Color;
import fr.glob.MyVector;

public class Ball {

    private MyVector position;
    private MyVector vitesse;
    private Color c;
    private int rayon;

    public Ball(MyVector position, MyVector vitesse, Color c, int rayon) {
        this.position = position;
        this.vitesse = vitesse;
        this.c = c;
        this.rayon = rayon;
    }
    
    public void update(int width, int height){
        position.add(vitesse);
        if ((position.x > width-rayon) || (position.x < rayon)) {
            if (position.x > width-rayon) position.x = width-rayon;
            if (position.x < rayon) position.x = rayon;
            vitesse.x = -vitesse.x;
        }
        if ((position.y > height-rayon) || (position.y < rayon)) {
            if (position.y > height-rayon) position.y = height-rayon;
            if (position.y < rayon) position.y = rayon;
            vitesse.y = -vitesse.y;
        }
    }

    public MyVector getPosition() {
        return this.position;
    }

    public MyVector getvitesse() {
        return this.vitesse;
    }

    public Color getColor() {
        return this.c;
    }

    public int getRayon() {
        return this.rayon;
    }

    @Override
    public String toString() {
        return "{" +
            " position='" + position + "'" +
            ", vitesse='" + vitesse + "'" +
            ", c='" + c + "'" +
            ", rayon='" + rayon + "'" +
            "}";
    }
    

}
