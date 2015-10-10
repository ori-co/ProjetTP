/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet.Contraintes;

import org.centrale.projet.objet.*;

/**
 * Cette contrainte impose qu'il y ait une distance minimum entre 
 * la creature(ou objet) envoyée en parametre et les autres Creatures(ou objets) presents dans le monde qu'on enverra en parametre
 * @author Sacha
 */
public class DistanceMin extends PlaceAccessible implements Contrainte {
    
    private int distanceMin;

    public DistanceMin(int distanceMin) {
        this.distanceMin = distanceMin;
    }
   
    /**
     * Trace un disque de true dans la matrice envoyée - inutile pour l'instant
     * @param matriceElementPhysique
     * @return 
     */
    protected boolean[][] tracerCercle(ElementPhysique[][] matriceElementPhysique)
    {
        boolean[][] matrice = null;
        int ligne;
        int colonne;
        for( ligne = 0; ligne < matriceElementPhysique.length; ligne++)
            for( colonne = 0; colonne < matriceElementPhysique[0].length; colonne++){
                matrice[ligne][colonne] = matriceElementPhysique[ligne][colonne] != null;
            }
        return matrice;
    }
    
    @Override
    public boolean respecteContrainte(World monde, Point2D p){
        return true;
    }
}
