package fr.Schelling;

import java.awt.Color;

import fr.Conway.*;
/**
 * SchellingSimulator
 */
public class SchellingSimulator extends ConwaySimulator{

    public int nombre_couleur;
    public int seuil;

    public SchellingSimulator(int size_of_square, int rows, int nombre_couleur, int seuil, int init_alive) {
        super(size_of_square, rows, init_alive, new SchellingGrid(rows, rows, init_alive, seuil, nombre_couleur), new Color[]{Color.WHITE, Color.RED, Color.BLUE});
        this.nombre_couleur = nombre_couleur;
        this.seuil = seuil;
    }
}
