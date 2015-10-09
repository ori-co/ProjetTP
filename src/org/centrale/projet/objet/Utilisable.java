/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author oriane école
 */
public interface Utilisable {
    /**
     * comportement de l'objet lors de son utilisation par le personnage P
     * @param p Personnage qui utilise l'objet
     */
    public void estUtilise(Personnage p);
    
    public Point2D getPos();
}
