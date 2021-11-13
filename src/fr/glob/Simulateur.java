/** This is our abstract class for simulation. */

package fr.glob;

import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

import fr.glob.event.EventManager;

/** Simulateur. */
public abstract class Simulateur implements Simulable {

    /** The width. */
    protected int w;

    /** The height. */
    protected int h;

    /** The screen where we want to draw. */
    protected GUISimulator win;

    /** Our event manager. */
    protected EventManager em;

    /** The constructor of abstract simulator.
     *  @param w : the width
     *  @param h : the height
     *  @param win : where we want to Draw.
     * */
    public Simulateur(final int w,
                      final int h,
                      final GUISimulator win) {
        this.w = w;
        this.h = h;
        this.win = win;
        this.em = new EventManager();
    }


    /** Draw the panel where agents and ball can move.
     *  @param c : the color of the edge.
     */
    protected void drawEdge(final Color c) {
        Rectangle r = new Rectangle(w / 2, 0, c, c, w, 1);
        win.addGraphicalElement(r);
        r = new Rectangle(0, h / 2, c, c, 1, h);
        win.addGraphicalElement(r);
        r = new Rectangle(w / 2, h - 1, c, c, w, 1);
        win.addGraphicalElement(r);
        r = new Rectangle(w - 1, h / 2, c, c, 1, h);
        win.addGraphicalElement(r);
    }

    /** The next method. */
    public abstract void next();

    /** The restart method. */
    public abstract void restart();


    /** The toString method.
     * @return the string representing the screen.
     */
    @Override
    public String toString() {
        return "{"
            + " w='" + w + "'"
            + ", h='" + h + "'"
            + "}";
    }


}
