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
public interface Deplacable {
    /**
     * déplace une creature d'une case dans n'importe quelle direction (choisie aléatoirement)
     */
    public void deplace();
    
    /**
     * déplace une creature d'un nombre de cases données dans la direction choisie
     * h:haut, b:bas, d:droite, g:gauche
     * @param dir direction du déplacement
     * @param nbCases nb de cases du déplacement
     */
    public void deplace(char dir, int nbCases);
}
