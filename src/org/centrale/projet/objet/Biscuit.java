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
public class Biscuit extends Nourriture {

    public Biscuit() {
        super();
        Random rand = new Random();
      this.nbTours= rand.nextInt(5)+1;
        this.nbPt=(rand.nextInt(4)+1)*5;
        this.etiquette="Petit Lu "+nbPt;
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
