import gui.Simulable;

public class BallsSimulator implements Simulable {
    private Balls balls;


    public BallsSimulator() {
        this.balls = new Balls();
    }

    @Override
    public void next(){
        balls.translate(5, 5);
    }
    
    @Override
    public void restart(){
        balls.reInit();
    }

    @Override
    public String toString() {
        return "{" +
            " balls='" + balls + "'" +
            "}";
    }



}
