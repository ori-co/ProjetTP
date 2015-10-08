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
public class Mana extends Potion {

    public Mana(Point2D p, String et, int nb) {
        super(p, et, nb);
    }

    public Mana() {
        super();
    }
    
    public void estBu(Personnage p) {

        float dist = this.pos.distance(p.getPos());
        if (dist == 0) {
            // la potion peut etre bu
            p.setPtMana(p.getPtMana()+this.nbPt);
            System.out.println("La potion de Magie est bu par le personnage");
            
        } else {
            // la potion et la créature sont trop loin
            System.out.println("La potion et le personnage sont trop loin");
        }
    }
}
