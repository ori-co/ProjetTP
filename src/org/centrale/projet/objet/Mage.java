/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
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
    public Mage(Point2D pos, int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag, int dist) {
        super(pos, ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, nom, ptMana, pourcentageMag, pourcentageResistMag, degMag, dist);
    }

    public Mage() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(20)+10);
        this.setPourcentagePar(rand.nextInt(30)+30);
        this.setPtMana(10);
        this.setPourcentageMag(rand.nextInt(40)+40);
        this.setDegMag(rand.nextInt(30)+20);
        this.setDistAttMax(rand.nextInt(20)+5);
    }

    public Mage(Mage m) {
        super(m);
    }

    
    /**
     * lance un combat magique entre le mage et la créature attaquée
     * @param def la créature attaquée
     */
    @Override
    public void combattre(Creature def) {   
        System.out.print("\n Combat magique :\n Adversaire :");
        def.affiche();
        Random rand = new Random();
        float dist = this.pos.distance(def.pos);
        if (dist >= 2 && dist < this.distAttMax) {
            // le combat peut avoir lieu

            // tirage de l'attaquant
            if (rand.nextInt(100) <= this.pourcentageMag) {
                // attaque réussie

                if (this.ptMana > 0) {
                    this.ptMana--;
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

        @Override
    public ArrayList<Creature> listeAdversaire(World monde) {
        ArrayList<Creature> liste = new ArrayList<>();
        
        for (ElementPhysique element : monde.lesBots){
            if (element instanceof Creature){
                float dist = element.getPos().distance(this.getPos());
                if (dist >= 2 && dist < this.distAttMax){
                    liste.add((Creature) element);
                }
            }
        }
        return liste;
    }
}
