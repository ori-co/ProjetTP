/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author oriane école
 */
public class Loup extends Monstre {

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

}
