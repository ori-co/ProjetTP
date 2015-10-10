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
public class Biscuit extends Nourriture {

    public Biscuit() {
        super();
    }

    public Biscuit(int nbPt, int nbTours, Point2D p, String et) {
        super(nbPt, nbTours, p, et);
    }
    
    @Override
    public void estUtilise(Personnage p){
        p.gosier.add(this);
        p.setPourcentageAtt(p.getPourcentageAtt()+nbPt);
    }
    @Override
    public void estConsomme(Personnage p){
        p.gosier.remove(this);
        p.setPourcentageAtt(p.getPourcentageAtt()-nbPt);
    }
}
