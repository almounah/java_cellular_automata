import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;
import java.awt.Color;

public class BallsSimulator extends GUISimulator implements Simulable{
    private Balls balls;


    public BallsSimulator(int width, int height, java.awt.Color bgColor) {
        super(width, height, bgColor);
        this.balls = new Balls(10, width, height);
        setSimulable(this);
    }

    @Override
    public void next(){
        balls.translate(5, 5);
        reset();
        for(Ball b : balls.getballsList())
            addGraphicalElement(new Oval((int)b.getPoint().getX(), (int)b.getPoint().getY(), b.getColor(), b.getColor(), b.getRayon()));
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
        for(Ball b : balls.getballsList())
            addGraphicalElement(new Oval((int)b.getPoint().getX(), (int)b.getPoint().getY(), b.getColor(), b.getColor(), b.getRayon()));
    }

}
