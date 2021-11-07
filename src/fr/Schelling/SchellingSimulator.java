package fr.Schelling;

import java.awt.Color;

import fr.Conway.*;
import gui.GUISimulator;
/**
 * SchellingSimulator
 */
public class SchellingSimulator extends ConwaySimulator{

    public int nombre_couleur;
    public int seuil;

    public SchellingSimulator(int size_of_square, int rows, Color[] list_color, int seuil, int init_alive, GUISimulator win) {
        super(size_of_square, rows, init_alive, new SchellingGrid(rows, rows, init_alive, seuil, list_color.length), list_color, win);
        this.nombre_couleur = list_color.length;
        this.seuil = seuil;
    }
}
