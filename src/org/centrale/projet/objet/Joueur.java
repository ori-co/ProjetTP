/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author 
 */
public class Joueur {
    protected Personnage perso;

    public Joueur(Personnage perso) {
        this.perso = perso;
    }
    
    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    public Personnage getPerso() {
        return perso;
    }
    
    public void joue(World monde){
        
    }
}
