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
public class NuageToxique extends Objet implements Deplacable, Combattant {

    /**
     * charge du nuage toxique, degats infligés à une créature
     */
    protected int charge;
    
    public NuageToxique() {
        super();
        charge = 0;
    }

    public NuageToxique(int ch, Point2D p, String et) {
        super(p, et);
        charge=ch;
    }
    
    @Override
    public void affiche() {
        System.out.println("Il y a un nuage Toxique en "+ pos);
    }

    @Override
    public void deplace() {
        Random rand = new Random();
        pos.translate(rand.nextInt(3)-1, rand.nextInt(3)-1);
    }

    @Override
    public void deplace(char dir, int nbCases) {
                switch(dir){
            case 'h' :
                pos.translate(0,nbCases);
            break;
            case 'b':
                pos.translate(0, -nbCases);
                break;
            case 'd':
                pos.translate(nbCases, 0);
                break;
            case 'g':
                pos.translate(-nbCases, 0);
                break;
            default:
                ;   
        }
    }

    @Override
    public void combattre(Creature c) {
        System.out.println("\n Combat toxique  :");
        Random rand = new Random();
        float dist = this.pos.distance(c.getPos());
        // si l'aversaire est sur une case adjacente
        if (dist<2){
            // l'attaque est un succès
            if (rand.nextInt(100)<= c.pourcentagePar){
                // la parade est un succès
                int deg = this.charge - c.getPtPar();
                c.setPtVie(c.getPtVie()-deg);
                System.out.println("L'adversaire est intoxiqué ! Il ne perd que "+ deg +" points de vie en se protegeant avec son mouchoir.");
            } else {
                // la parade est un echec
                c.setPtVie(c.getPtVie()-this.charge);
                System.out.println("L'adversaire est intoxiqué ! Il perd "+ this.charge +" points de vie.");
            }
            // disparition du nuage ?? / diminution de sa charge ?
        } else {
            // l'attaque est un échec
            System.out.println("L'adversaire est trop loin pour être intoxiqué.");
        }
    }
    
}
