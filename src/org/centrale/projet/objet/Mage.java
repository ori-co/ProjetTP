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
public class Mage extends Personnage implements Combattant{

    /**
     *
     * @param ptVie points de vie
     * @param pourcentageAtt pourcentage de réussite des attaques
     * @param pourcentagePar pourcentage de réussite des parades
     * @param degAtt dégats attaques
     * @param pos position
     * @param nom nom
     * @param ptPar point de parade
     * @param ptMana point de magie
     * @param pourcentageMag pourcentage de réussite des attaques magiques
     * @param pourcentageResistMag pourcentage de résistance à la magie
     * @param degMag dégats magie
     * @param dist
     */
    public Mage(int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag, int dist) {
        super(ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, pos, nom, ptMana, pourcentageMag, pourcentageResistMag, degMag, dist);
    }

    public Mage() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(15));
        this.setPourcentageAtt(rand.nextInt(10));
        this.setPourcentagePar(rand.nextInt(30));
        this.setDegAtt(rand.nextInt(10));
        this.setPtMana(10);
        this.setPourcentageMag(rand.nextInt(70));
        this.setDegMag(rand.nextInt(50));
        this.setDistAttMax(rand.nextInt(15));
    }

    public Mage(Mage m) {
        super(m);
    }

    /**
     * Affiche le nom et la position de l'archer
     */
    @Override
    public void affiche() {
        System.out.println("Je suis un mage du nom de " + this.nom + " positionné en " + this.pos);
    }
    
    /**
     * lance un combat magique entre le mage et la créature attaquée
     * @param def la créature attaquée
     */
    @Override
    public void combattre(Creature def) {   
        System.out.println("\n Combat magique :");
        Random rand = new Random();
        float dist = this.pos.distance(def.pos);
        if (dist >= 2 && dist < this.distAttMax) {
            // le combat peut avoir lieu

            // tirage de l'attaquant
            if (rand.nextInt(100) <= this.pourcentageMag) {
                // attaque réussie

                System.out.println(this.getPtMana());

                if (this.ptMana > 0) {
                    this.ptMana--;
                    System.out.println(this.getPtMana());
                    def.ptVie -= this.degMag;
                    System.out.println("L'adversaire est touché ! Il perd " + this.degMag + " points de vie.");
                } else {
                    // attache échoue
                    System.out.println("L'attaque a échoué !");
                }
            } else {
                // pas assez de points magie
                System.out.println("L'attaque a échoué car l'attaquant n'a plus de point magie");
            }
        } else {
            // le combat échoue
            System.out.println("L'adversaire est trop loin ou trop proche pour être attaqué !");
        }
    }

}
