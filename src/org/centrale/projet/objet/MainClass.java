/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author Sacha
 */
public class MainClass {
    public static void main(String[] args)
    {
        World monde = new World(20);
        
        for (int i =0; i<5; i++) {
            monde.creationJoueur();
            monde.lesJoueurs.get(i).getPerso().affiche();
        }
        
        monde.lesJoueurs.get(0).getPerso().affiche();
        
        monde.afficheMatrice();
    }
}
