package balls;

import java.awt.Point;
import java.awt.Color;

public class Ball {

    private Point p;
    private int dx, dy;
    private Color c;
    private int rayon;


    public Ball(Point p, int dx, int dy, Color c, int rayon) {
        this.p = p;
        this.dx = dx;
        this.dy = dy;
        this.c = c;
        this.rayon = rayon;
    }

    private int getNewX(int w){
        int maxX = w-1-rayon;
        int minX = rayon;
        int x = (int) getPoint().getX();
        int nx = x + dx;
        if (nx < minX){
            nx = minX + (minX-nx); // nx = limite + dépassement
            dx = -dx; //A changer pour physique
        } else if (nx > maxX){
            nx = maxX - (nx - maxX);
            dx = -dx;
        }
        return nx;
    }

    private int getNewY(int h){
        int maxY = h-1-rayon;
        int minY = rayon;
        int y = (int) getPoint().getY();
        int ny = y + dy;
        if (ny < minY){
            ny = minY + (minY-ny);
            dy = -dy;
        } else if (ny > maxY){
            ny = maxY - (ny - maxY);
            dy = -dy;
        }
        return ny;
    }
    
    public void translate(int w, int h){
        p.setLocation(getNewX(w),getNewY(h));
    }

    public void setDirection(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public Point getPoint(){
        return p;
    }

    public Color getColor() {
        return this.c;
    }

    public int getRayon() {
        return this.rayon;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    @Override
    public String toString() {
        return "{" +
            " p='" + getPoint() + "'" +
            ", dx='" + getDx() + "'" +
            ", dy='" + getDy() + "'" +
            ", c='" + getColor() + "'" +
            ", rayon='" + getRayon() + "'" +
            "}";
    }


}
