/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author Sacha
 */
public class MainClass {
    public static void main(String[] args)
    {
        World monde = new World(20);
        monde.placer(new Mage());
        monde.creeMondeAlea(10);
        
        monde.afficheMatrice();
    }
}
