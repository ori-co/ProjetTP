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
public abstract class Potion extends Objet implements Utilisable {
    /**
     * nombre d'unité de la potion
     */
    protected int nbPt;
    
    /**
     * 
     * @param p Position de la potion
     * @param et Etiquette de l'objet
     * @param nb Nombre d'unité de vie de la potion
     */
    public Potion (Point2D p,String et, int nb){
        super(p,et);
        nbPt =nb;
    }
    public Potion(){
        super();
        nbPt=0;
    }

    public void setNbPt(int nbPt) {
        this.nbPt = nbPt;
    }

    public int getNbPt() {
        return nbPt;
    }
    
    
    
    @Override
    public void affiche(){
        System.out.println(this.getClass().getSimpleName()+" : "+this.etiquette +" Position : " + this.pos);
    }
    
    @Override
    public abstract void estUtilise(Personnage p);

}
