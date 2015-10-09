/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author oriane 
 */
public class Archer extends Personnage implements Combattant{
    /* attributs */
/**
 * Nombre de flèches de l'archer
 */
    protected int nbFleches;
    
    /* constrcuteurs */
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
     * @param dist distance max d'attaque
     * @param nbFleches nombre de flèches
     */
    public Archer( int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag,int dist,int nbFleches) {
        super(ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, pos, nom, ptMana, pourcentageMag, pourcentageResistMag,dist, degMag);
        this.nbFleches = nbFleches;
    }

    public Archer() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(20));
        this.setPourcentageAtt(rand.nextInt(70));
        this.setPourcentagePar(rand.nextInt(30));
        this.setDegAtt(rand.nextInt(50));
        this.setPourcentageMag(rand.nextInt(10));
        this.setDegMag(rand.nextInt(15));
        this.setDistAttMax(rand.nextInt(15));
        
        this.nbFleches=10;
    }

    public Archer(Archer a) {
        super(a);
        this.nbFleches = 0;
    }

    
    
    /* methodes */

    public int getNbFleches() {
        return nbFleches;
    }

    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }
    
    
/** 
 * Affiche le nom et la position de l'archer
 */
    @Override
    public void affiche() {
        System.out.println("Je suis un archer du nom de "+ this.nom + " positionné en " + this.pos);
    }

     /**
     * lance un combat à distance entre l'archer et la créature attaquée
     * @param def la créature attaquée
     */
    @Override
    public void combattre(Creature def) {
        System.out.println("\n Combat à distance :");
        Random rand = new Random();
        float dist = this.pos.distance(def.pos);
        if (dist >= 2 && dist < this.distAttMax) {
            // le combat peut avoir lieu
            if (this.nbFleches > 0) {
                this.nbFleches--;
                // tirage de l'attaquant
                if (rand.nextInt(100) <= this.pourcentageAtt) {
                    // attaque réussie
                    def.ptVie -= this.degAtt;
                    System.out.println("L'adversaire est touché ! Il perd " + this.degAtt + " points de vie.");
                } else {
                    // attache échoue
                    System.out.println("L'attaque a échoué !");
                }
            } else { 
                    //pas assez de flèches 
                    System.out.println("L'attaqua a échoué car l'attaquant n'a plus de flèches");
                    }
        } else {
            // le combat échoue
            System.out.println("L'adversaire est trop loin ou trop proche pour être attaqué !");
        }
    }
    
}
