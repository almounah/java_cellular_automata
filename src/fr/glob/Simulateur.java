package fr.glob;

import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

import fr.glob.event.EventManager;

public abstract class Simulateur implements Simulable{
    protected int w,h;
    protected GUISimulator win;
    protected EventManager em;

    public Simulateur(int w, int h, GUISimulator win) {
        this.w = w;
        this.h = h;
        this.win = win;
        this.em = new EventManager();
    }


    //Dessine les bords du "panel", où les balls peuvent se déplacer
    protected void drawEdge(Color c){
        Rectangle r = new Rectangle(w/2,0, c, c, w, 1);
        win.addGraphicalElement(r);
        r = new Rectangle(0,h/2, c, c, 1, h);
        win.addGraphicalElement(r);
        r = new Rectangle(w/2,h-1, c, c, w, 1);
        win.addGraphicalElement(r);
        r = new Rectangle(w-1,h/2, c, c, 1, h);
        win.addGraphicalElement(r);
        //Partie perso
        // Oval o = new Oval(w/2, h/2, Color.yellow, Color.DARK_GRAY, 20);
        // addGraphicalElement(o);
    }

    public abstract void next();
    public abstract void restart();

    @Override
    public String toString() {
        return "{" +
            " w='" + w + "'" +
            ", h='" + h + "'" +
            "}";
    }

    
}
