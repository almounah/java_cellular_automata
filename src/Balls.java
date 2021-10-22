import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Balls {
    private ArrayList<Point> ballsList;
    private ArrayList<Point> ballsCopy;

    public Balls(){
        ballsList = new ArrayList<Point>();
        ballsCopy = new ArrayList<Point>();
    }

    public Balls(int nbToAdd, int maxCoo){
        ballsList = new ArrayList<Point>();
        ballsCopy = new ArrayList<Point>();
        addRandomBalls(nbToAdd, maxCoo);
    }

    public ArrayList<Point> getballsList(){
        return ballsList;
    }

    public void reInit(){
        if(ballsCopy.size() != ballsList.size()) throw new RuntimeException("reInit : Listes de taille différentes!");
        Iterator<Point> itL = ballsList.iterator();
        Iterator<Point> itC = ballsCopy.iterator();
        Point pt,cp;
        while(itL.hasNext() && itC.hasNext()){
            pt = itL.next();
            cp = itC.next();
            pt.setLocation(cp.getX(), cp.getY());
        }
    }

    public void addRandomBalls(int nbToAdd, int maxCoo){
        Random r = new Random();
        for (int i = 0; i < nbToAdd; i++){
            addBall(r.nextInt(maxCoo), r.nextInt(maxCoo));
        }
    }

    public void addBall(int x, int y){
        ballsList.add(new Point(x,y));
        ballsCopy.add(new Point(x,y));
    }

    public void translate(int dx,int dy){
        for (Point p : ballsList) p.translate(dx, dy);
    }

    public String toStringCP(){
        String str = "BallsCopy ("+this.ballsList.size()+"): ";
        for (Point p : ballsCopy) str += "["+ p.getX()+";"+p.getY()+"]";
        return str;
    }

    @Override
    public String toString(){
        String str = "BallsList ("+this.ballsList.size()+"): ";
        for (Point p : ballsList) str += "["+ p.getX()+";"+p.getY()+"]";
        return str;
    }

}