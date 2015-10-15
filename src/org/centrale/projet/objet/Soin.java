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
 * 
 */
public class Soin extends Potion {

    public Soin(Point2D p, String et, int nb) {
        super(p, et, nb);
    }

    public Soin() {
        super();
        Random rand = new Random();
        this.nbPt=(rand.nextInt(3)+1)*10;
        this.etiquette="Potion de Santé "+nbPt;
    }
    

    @Override
    public void estUtilise(Personnage p) {
            p.setPtVie(p.getPtVie()+this.nbPt);
            System.out.println("La potion de Soin est bu par la créature");
    }
}
