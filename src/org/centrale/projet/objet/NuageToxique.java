/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
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
        charge = ch;
    }

    @Override
    public void affiche() {
        System.out.println(this.getClass().getSimpleName()+" : Position : " + pos);
    }

    /**
     * déplace le monstre sur une case adjacente aléatoirement choisie
     */
    @Override
    public Point2D deplace() {
        Random rand = new Random();
        int dx = 0;
        int dy = 0;
        while (dx==0 && dy==0){
            dx =rand.nextInt(3)-1;
            dy =rand.nextInt(3)-1; 
        }
        Point2D p = new Point2D(pos);
        p.translate(dx,dy);
        return p;
    }
    /**
     * retourne la position d'un joueur après déplacement de nbCases dans la direction dir
     * 
     * @param dir direction dans laquelle le personnage est déplacé : 
     * h:haut b:bas d:droite ou g:gauche
     * @param nbCases nombre de cases que le personnage parcourt dans la direction choisie
     */
    @Override
    public Point2D deplace(char dir, int nbCases){
        Point2D p = new Point2D(pos);
        switch(dir){
            case 'h' :
                p.translate(0,nbCases);
            break;
            case 'b':
                p.translate(0, -nbCases);
                break;
            case 'd':
                p.translate(nbCases, 0);
                break;
            case 'g':
                p.translate(-nbCases, 0);
                break;
            default:
                //
                break;   
        }
        return p;
    }

    @Override
    public void combattre(Creature c) {
        System.out.print("\n Combat toxique  :\n Adversaire :");
        c.affiche();
        Random rand = new Random();
        float dist = this.pos.distance(c.getPos());
        // si l'aversaire est sur une case adjacente
        if (dist < 2) {
            // l'attaque est un succès
            if (rand.nextInt(100) <= c.pourcentagePar) {
                // la parade est un succès
                if (c.getPtPar() > this.charge) {
                    System.out.println("L'adversaire respire le nuage mais ne perd aucun point de vie en se protegeant avec son mouchoir.");
                } else {
                    int deg = this.charge - c.getPtPar();
                    c.setPtVie(c.getPtVie() - deg);
                    System.out.println("L'adversaire est intoxiqué ! Il ne perd que " + deg + " points de vie en se protegeant avec son mouchoir.");
                }
            } else {
                // la parade est un echec
                c.setPtVie(c.getPtVie() - this.charge);
                System.out.println("L'adversaire est intoxiqué ! Il perd " + this.charge + " points de vie.");
            }
            // disparition du nuage ?? / diminution de sa charge ?
        } else {
            // l'attaque est un échec
            System.out.println("L'adversaire est trop loin pour être intoxiqué.");
        }
    }
    
    @Override
    public ArrayList<Creature> listeAdversaire(World monde) {
        ArrayList<Creature> liste = new ArrayList<>();
        
        for (ElementPhysique element : monde.lesBots){
            if (element instanceof Creature){
                float dist = element.getPos().distance(this.getPos());
                if (dist <2 ){
                    liste.add((Creature) element);
                }
            }
        }
        return liste;
    }
}
