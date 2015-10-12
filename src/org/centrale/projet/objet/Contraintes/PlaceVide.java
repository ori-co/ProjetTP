/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet.Contraintes;

import java.util.ArrayList;
import org.centrale.projet.objet.ElementPhysique;
import org.centrale.projet.objet.Point2D;
import org.centrale.projet.objet.World;

/**
 *
 * @author oriane école
 */
public class PlaceVide extends PlaceAccessible implements Contrainte {

    /**
     * Permet de savoir si la place considérée est disponible ou non.
     * cad sans rien dessus
     * 
     * @param monde le monde sur on teste a position
     * @param point2D la position testée
     * @return Retourn true si la place est libre, false sinon
     */
    private boolean estVide(World monde, Point2D p) {
        ElementPhysique[][] mat = monde.toMatrice();
        int x = p.getX();
        int y = p.getY();

        if (x >= 0 && x < World.tailleMonde && y >= 0 && y < World.tailleMonde) {
            return mat[x][y] == null;
        } else {
            return false;
        }
    }
    
    
    /**
     * Retourne le vecteur des places disponibles dans le monde 
     * cad sans rien dessus, ni objet ni creature
     *
     * @param monde
     * @return un vecteur des places libres dans le monde
     */
    @Override
    public ArrayList<Point2D> listeDePlaces(World monde) {
        ArrayList<Point2D> pointsLibres = new ArrayList<>();
        ElementPhysique[][] mat = monde.toMatrice();
        for (int i = 0; i < World.tailleMonde; i++) {
            for (int j = 0; j < World.tailleMonde; j++) {
                if (mat[i][j] != null) {
                   
                } else {
                    pointsLibres.add(new Point2D(i, j));
                }
            }
        }
        return pointsLibres;
    }
    /**
     * @param monde
     * @param p
     * @return 
     */
    @Override
    public boolean respecteContrainte(World monde, Point2D p) {
        return estVide(monde, p);
    }
}
