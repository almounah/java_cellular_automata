import gui.GUISimulator;
import java.awt.Color;

public class TestBallsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        gui.setSimulable(new BallsSimulator());
        gui.reset();
        gui.addGraphicalElement(new gui.Oval(100, 100, Color.RED, Color.RED, 10));
    }
}
