/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet.Contraintes;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.projet.objet.*;

/**
 *
 * @author Sacha
 */
public class PlaceAccessible implements Contrainte {

    /**
     * Permet de savoir si la position testée est libre ou non
     * cad si un personnage peut s'y déplacer (aucune autre créature présente mais les objets utulisables peuvent s'y trouver)
     *
     * @param monde le monde sur lequel on voudrait inserer l'objet
     * @param element l'element qu'on voudrait inserer (position renseignée !
     * @return Retourn true si la place est libre, false sinon
     */
    private boolean estLibre(World monde, Point2D p) {
        ElementPhysique[][] mat = monde.toMatrice();
        int x = p.getX();
        int y = p.getY();

        if (mat[x][y] != null) {
                if (mat[x][y] instanceof Utilisable) {
                    return true;
                } else {
                    return false;
                }
        } else {
            return true;
        }
    }

    /**
     * Retourne le vecteur des places libres dans le monde cad les places où un
     * deplacement est possible mais qui peuvent contenir un objet utilisable
     *
     * @param monde à tester
     * @return vecteurs des position libres
     */
    public ArrayList<Point2D> listeDePlaces(World monde) {
        ArrayList<Point2D> pointsLibres = new ArrayList<>();
        ElementPhysique[][] mat = monde.toMatrice();
        for (int i = 0; i < World.tailleMonde; i++) {
            for (int j = 0; j < World.tailleMonde; j++) {
                if (mat[i][j] != null) {
                        if (mat[i][j] instanceof Utilisable) {
                             pointsLibres.add(new Point2D(i, j));
                        } 
                } else {
                    pointsLibres.add(new Point2D(i, j));
                }
            }
        }
        return pointsLibres;
    }

    /**
     *
     * @param monde
     * @param p
     * @return
     */
    @Override
    public boolean respecteContrainte(World monde, Point2D p) {
        return estLibre(monde, p);
    }

}
