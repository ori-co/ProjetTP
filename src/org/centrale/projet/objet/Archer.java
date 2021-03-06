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
    public Archer( Point2D pos, int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag, int dist, int nbFleches) {
        super(pos, ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, nom, ptMana, pourcentageMag, pourcentageResistMag,dist, degMag);
        this.nbFleches = nbFleches;
    }

    public Archer() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(20)+10);
        this.setPourcentageAtt(rand.nextInt(40)+40);
        this.setPourcentagePar(rand.nextInt(30)+30);
        this.setDegAtt(rand.nextInt(30)+20);
        this.setDistAttMax(rand.nextInt(20)+5);
        
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
     * lance un combat à distance entre l'archer et la créature attaquée
     * @param def la créature attaquée
     */
    @Override
    public void combattre(Creature def) {
        System.out.print("\n Combat à distance : \n Adversaire :");
        def.affiche();
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
