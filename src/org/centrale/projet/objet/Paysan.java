/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author oriane 
 */
public class Paysan extends Personnage{


    /**
     * 
     * @param ptVie points de vie
     * @param pourcentageAtt pourcentage de réussite des attaques
     * @param pourcentagePar pourcentage de réussite des parades
     * @param degAtt dégats attaques
     * @param pos position
     * @param nom nom
     * @param ptPar point de parade
     * @param ptMana point de magie
     * @param pourcentageMag pourcentage de réussite des attaques magiques
     * @param pourcentageResistMag pourcentage de résistance à la magie
     * @param degMag dégats magie
     */

    public Paysan(int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag, int dist) {
        super(ptVie, ptPar,pourcentageAtt, pourcentagePar, degAtt, pos, nom, ptMana, pourcentageMag, pourcentageResistMag, degMag, dist);
    }

    public Paysan(Paysan perso) {
        super(perso);
    }

    public Paysan() {
        super();
        Random rand = new Random();
        this.setPtVie(100);
        this.setPtPar(rand.nextInt(20));
        this.setPourcentagePar(rand.nextInt(30)+10);
    }
    
    
}
