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
public class Loup extends Monstre implements Combattant {

    /**
     *
     * @param pV points de vie
     * @param ptP points de parade
     * @param pA pourcentage de réussite des attaques
     * @param pP pourcentage de réussite des parades
     * @param dA dégats attaque
     * @param pos position
     */
    public Loup(int pV,int ptP, int pA, int pP, int dA, Point2D pos) {
        super(pV, ptP,pA, pP, dA, pos);
    }

    public Loup(Loup l) {
        super(l);
    }

    public Loup() {
        super();
    }

    /**
     * Affiche le nom et la position de l'archer
     */
    @Override
    public void affiche() {
        System.out.println("Il y a un loup en " + this.pos);
    }
    
    @Override
    public void combattre(Creature c){
        System.out.println("\n Combat au corps à corps :");
        Random rand = new Random();
        float dist = this.pos.distance(c.pos);
        if (dist < 2) {
            // le combat peut avoir lieu
            
            // tirage de l'attaquant
            if (rand.nextInt(100)<= this.pourcentageAtt){
                // attaque réussie
                
                // tirage du défenseur
                if (rand.nextInt(100)<= c.pourcentagePar){
                    // parade réussie
                    int deg = this.degAtt - c.ptPar; 
                    c.ptVie -= deg;
                    System.out.println("L'adversaire est touché ! Mais il se défend bien, il ne perd que "+ deg +" points de vie.");
                }else{
                    //parade échouée
                    c.ptVie -= this.degAtt;
                    System.out.println("L'adversaire est touché ! Il perd "+ this.degAtt+" points de vie.");
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
