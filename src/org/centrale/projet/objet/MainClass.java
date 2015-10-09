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
        //monde.placer(new Mage());
        //monde.creeMondeAlea(25);
        
        
//        for (int i =0; i<5; i++) {
//            monde.creationJoueur();
//            monde.lesJoueurs.get(i).getPerso().affiche();
//        }
        
        
        Archer robin = new Archer();robin.setNom("Robin des Bois");
        NuageToxique gaz = new NuageToxique(10,new Point2D(),"Gaz Mortel");
        monde.placer(robin,new Point2D(5,5));
        monde.placer(gaz,new Point2D(5,6));
        monde.afficheWorld();
        
        gaz.combattre(robin);
    }
}
