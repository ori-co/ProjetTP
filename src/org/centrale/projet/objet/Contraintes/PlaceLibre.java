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
public class PlaceLibre implements Contrainte {

    /**
     * Permet de savoir si la place que desir prendre l'element physique est libre ou non.
     * Attention, elle est bien en protected parceque par convention, seule la methode respecteContrzinte des Contrainte est publique 
     * @param monde le monde sur lequel on voudrait inserer l'objet
     * @param element l'element qu'on voudrait inserer (position renseignée !
     * @return Retourn true si la place est libre, false sinon
     */
    private boolean estLibre(World monde, ElementPhysique element) {
        ElementPhysique [][] mat = monde.toMatrice();
        int x = element.getPos().getX();
        int y = element.getPos().getY();
        
        if (mat[x][y] != null){
            return false;
        } else {
            return true;
        }
    }
//     /**
//     * On crée une matrice de boolean à la place de la matrice d'objets
//     * @param matriceElementPhysique
//     * @return une matrice qui vaut true la ou ya un element physique dans la matrice envoyée et false la ou ya rien
//     */
//    public static boolean[][] creationMatriceBoolean(ElementPhysique[][] matriceElementPhysique)
//    {
//        boolean[][] matrice = new boolean[matriceElementPhysique.length][matriceElementPhysique[0].length];
//        int ligne;
//        int colonne;
//        for( ligne = 0; ligne < matriceElementPhysique.length; ligne++)
//            for( colonne = 0; colonne < matriceElementPhysique[0].length; colonne++){
//                matrice[ligne][colonne] = matriceElementPhysique[ligne][colonne] != null;
//            }
//        return matrice;
//    }
    
    @Override
    public boolean respecteContrainte(World monde, ElementPhysique element) {
        return estLibre(monde, element);
    }
    
}
