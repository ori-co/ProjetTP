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
public class Mana extends Potion {

    public Mana(Point2D p, String et, int nb) {
        super(p, et, nb);
    }

    public Mana() {
        super();
        Random rand = new Random();
        this.nbPt=(rand.nextInt(3)+1)*10;
        this.etiquette="Potion de Magie "+nbPt;
    }
    
    
    @Override
    public void estUtilise(Personnage p) {
            p.setPtMana(p.getPtMana()+this.nbPt);
            System.out.println("La potion de Magie est bu par le personnage");
    }
}
