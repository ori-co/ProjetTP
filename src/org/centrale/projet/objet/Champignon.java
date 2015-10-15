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
public class Champignon extends Nourriture {

    public Champignon() {
        super();
      Random rand = new Random();
      this.nbTours= rand.nextInt(5)+1;
        this.nbPt=(rand.nextInt(4)+1)*5;
        this.etiquette="Champi "+nbPt;
    }

    public Champignon(Point2D p, String et, int nbPt, int nbTours) {
        super(p, et, nbPt, nbTours);
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
