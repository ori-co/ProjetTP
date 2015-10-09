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
public abstract class Nourriture extends Objet implements Utilisable {
    /**
     * Nombre de point que la nourriture va donner ou faire perdre temporairement au personnage
     */ 
    protected int nbPt;
    /**
     * Nombre de tours durant lesquels la nourriture impacte le personnage
     */
    protected int nbTours;


    public Nourriture() {
        super();
        this.nbPt = 0;
        this.nbTours = 0;
    }

    public Nourriture(int nbPt, int nbTours, Point2D p, String et) {
        super(p, et);
        this.nbPt = nbPt;
        this.nbTours = nbTours;
    }

    public int getNbPt() {
        return nbPt;
    }

    public int getNbTours() {
        return nbTours;
    }

    public void setNbPt(int nbPt) {
        this.nbPt = nbPt;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
     
    @Override
    public abstract void affiche();
    
    @Override
    public abstract void estUtilise(Personnage p);
    
    public abstract void estConsomme(Personnage p);
     
    
}
