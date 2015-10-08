/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

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
    }


    public void estBu(Creature c) {

        float dist = this.pos.distance(c.getPos());
        if (dist == 0) {
            // la potion peut etre bu
            c.setPtVie(c.getPtVie()+this.nbPt);
            System.out.println("La potion de Soin est bu par la créature");
            
        } else {
            // la potion et la créature sont trop loin
            System.out.println("La potion et la creature sont trop loin");
        }
    }
}
