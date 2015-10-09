/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author oriane école
 */
public class Guerrier extends Personnage implements Combattant {

    public Guerrier(int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag, int dist) {
        super(ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, pos, nom, ptMana, pourcentageMag, pourcentageResistMag, degMag, dist);
    }

    public Guerrier() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(30));
        this.setPourcentageAtt(rand.nextInt(70));
        this.setPourcentagePar(rand.nextInt(40));
        this.setDegAtt(rand.nextInt(60));
        this.setPourcentageMag(rand.nextInt(10));
        this.setDegMag(rand.nextInt(15));
        this.setDistAttMax(rand.nextInt(3));
        
    }

    public Guerrier(Guerrier g) {
        super(g);
    }

    /* Méthodes*/
    /**
     * Affiche le nom et la position de l'archer
     */
    @Override
    public void affiche() {
        System.out.println("Je suis un guerrier du nom de " + this.nom + " positionné en " + this.pos);
    }

    /**
     * lance un combat au corps à corps entre le guerrier et la créature
     * attaquée
     *
     * @param def la créature attaquée
     */
    @Override
    public void combattre(Creature def) {
        System.out.println("\n Combat au corps à corps :");
        Random rand = new Random();
        float dist = this.pos.distance(def.pos);
        if (dist < 2) {
            // le combat peut avoir lieu

            // tirage de l'attaquant
            if (rand.nextInt(100) <= this.pourcentageAtt) {
                // attaque réussie

                // tirage du défenseur
                if (rand.nextInt(100) <= def.pourcentagePar) {
                    // parade réussie
                    int deg = this.degAtt - def.ptPar;
                    if (deg < 0) {
                        System.out.println("L'adversaire est touché ! Mais il se défend télemment bien qu'il ne perd aucun point de vie.");
                    } else {
                        def.ptVie -= deg;
                        System.out.println("L'adversaire est touché ! Mais il se défend bien, il ne perd que " + deg + " points de vie.");
                    }
                } else {
                    //parade échouée
                    def.ptVie -= this.degAtt;
                    System.out.println("L'adversaire est touché ! Il perd " + this.degAtt + " points de vie.");
                }
            } else {
                // attache échoue
                System.out.println("L'attaque a échoué !");
            }

        } else {
            // le combat échoue
            System.out.println("L'adversaire est trop loin pour être attaqué !");
        }
    }
}
