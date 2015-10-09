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
public class Champignon extends Nourriture {

    public Champignon() {
        super();
    }

    public Champignon(int nbPt, int nbTours, Point2D p, String et) {
        super(nbPt, nbTours, p, et);
    }

    
    @Override
    public void affiche() {
        System.out.println("Champignon : "+ etiquette+" est situé en "+ pos);
    }
    
    @Override
    public void estUtilise(Personnage p){
        p.gosier.add(this);
        p.setDegAtt(p.getDegAtt()-nbPt);
    }
    @Override
    public void estConsomme(Personnage p){
        p.gosier.remove(this);
        p.setDegAtt(p.getDegAtt()+nbPt);
    }
    
}
