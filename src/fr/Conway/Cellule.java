package fr.Conway;

import java.awt.Point;

class Cellule {
    public boolean dead;
    public int neighbour_number;
    public Point top_left;

    public Cellule(int x, int y, int neighbour_number, boolean dead) {
        this.dead = dead;
        this.neighbour_number = neighbour_number;
        this.top_lelf = new Point(x, y);
    }


}
