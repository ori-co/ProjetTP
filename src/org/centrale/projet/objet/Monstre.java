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
public abstract class Monstre extends Creature {


    /* Constructeurs */
    /**
     * 
     * @param pV points de vie
     * @param ptP points de parade
     * @param pA pourcentage de réussite des attaques
     * @param pP pourcentage de réussite des parades
     * @param dA dégats attaque
     * @param pos position 
     */
    public Monstre(int pV, int ptP, int pA, int pP, int dA, Point2D pos) {
        super(pV,ptP,pA,pP,dA,pos);
    }
    public Monstre(Monstre monstre) {
        super(monstre);
    }

    public Monstre() {
        super();
    }

    
    /** 
     * affiche la position du monstre
     */
    public void affiche(){
        System.out.println("Il y a un monstre en : " + pos);
    }
}
