/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author Sacha
 */
public class Lapin extends Monstre {

    /**
     * 
     * @param pV points de vie
     * @param ptPar point de parade
     * @param pA pourcentage de réussite des attaques
     * @param pP pourcentage de réussite des parades
     * @param dA dégats attaque
     * @param pos position
     */
    public Lapin(int pV,int ptPar, int pA, int pP, int dA, Point2D pos) {
        super(pV,ptPar, pA, pP, dA, pos);
    }

    public Lapin(Lapin lapin) {
        super(lapin);
    }
    public Lapin() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(5));
        this.setPourcentagePar(rand.nextInt(15));
    }
    
    /**
     * Affiche le nom et la position de l'archer
     */
    @Override
    public void affiche() {
        System.out.println("Il y a un lapin en " + this.pos);
    }
}
