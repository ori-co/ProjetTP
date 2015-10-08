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
public interface Combattant {
    /**
     * lance un combat entre la créature courante et la créature c
     * @param c adversaire du combat
     */
    public void combattre(Creature c);
    
}
