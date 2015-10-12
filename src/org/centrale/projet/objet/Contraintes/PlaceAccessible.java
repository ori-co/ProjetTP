/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet.Contraintes;

import java.util.ArrayList;
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

        if (x >= 0 && x < World.tailleMonde && y >= 0 && y < World.tailleMonde) {
            if (mat[x][y] != null) {
                return mat[x][y] instanceof Utilisable;
            } else {
                return true;
            }
        } else {
            return false;
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
