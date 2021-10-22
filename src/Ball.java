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
    
    public void translate(int w, int h){
        p.setLocation(p.getX() + dx, p.getY() + dy);
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
