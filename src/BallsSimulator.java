import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import java.awt.Color;

public class BallsSimulator extends GUISimulator implements Simulable{
    private Balls balls;


    public BallsSimulator(int width, int height, java.awt.Color bgColor) {
        super(width, height, bgColor);
        this.balls = new Balls();
        setSimulable(this);
    }

    @Override
    public void next(){
        balls.translate(5, 5);
        reset();
        for(java.awt.Point p : balls.getballsList())
            addGraphicalElement(new Oval((int)p.getX(), (int)p.getY(), Color.WHITE, Color.WHITE, 20));
    }

    @Override
    public String toString() {
        return "{" +
            " balls='" + balls + "'" +
            "}";
    }

    @Override
    public void restart(){
        balls.reInit();
        reset();
        for(java.awt.Point p : balls.getballsList())
            addGraphicalElement(new Oval((int)p.getX(), (int)p.getY(), Color.WHITE, Color.WHITE, 20));
    }

}
