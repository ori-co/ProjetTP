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
        World monde = new World();
        //monde.placer(new Mage());
        monde.creeMondeAlea(15);
        monde.afficheWorld();

        // Test création personnage
//        for (int i =0; i<5; i++) {
//            monde.creationJoueur();
//            monde.lesJoueurs.get(i).getPerso().affiche();
//        }
        
        // Test combat toxique
//        Archer robin = new Archer();robin.setNom("Robin des Bois");
//        NuageToxique gaz = new NuageToxique(10,new Point2D(),"Gaz Mortel");
//        monde.placer(robin,new Point2D(5,5));
//        monde.placer(gaz,new Point2D(5,6));
//        monde.afficheWorld();
//        
//        gaz.combattre(robin);
        
//        // Test utlisation Potion
//        Archer robin = new Archer();robin.setNom("Robin des Bois");
//        monde.placer(robin);
//        Soin p1 = new Soin(new Point2D(),"Potion de test",10);
//        monde.placer(p1,robin.getPos());
//        
//        monde.afficheWorld();
//        System.out.println(robin.getPtVie());
//        robin.utilise(p1);
//        System.out.println(robin.getPtVie());
        
//        // Test du gosier
//        Archer robin = new Archer();robin.setNom("Robin des Bois");
//        Biscuit petitLu = new Biscuit(15,3,new Point2D(),"Paquet de Petit Lu");
//        monde.placer(robin);
//        monde.placer(petitLu, robin.getPos());
//        System.out.println("Pourcentage Att de Robin : "+robin.getPourcentageAtt());
//        monde.afficheWorld();
//        robin.utilise(petitLu);
//        for (int i=0;i<6;i++){
//        robin.digere();
//        robin.deplace();
//        System.out.println("Pourcentage Att de Robin : "+robin.getPourcentageAtt());
//        }
    }
}
