/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/* Attributs + getters + stters */
/**
 *
 * @author oriane école
 */
public abstract class Creature extends ElementPhysique implements Deplacable {
     /**
     * nombre de points de vie 
     */
    protected int ptVie;

    /**
     * Get the value of ptVie
     *
     * @return the value of ptVie
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     * Set the value of ptVie
     *
     * @param ptVie new value of ptVie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

     /**
     * nombre de points de parade 
     */
    protected int ptPar;

    /**
     * Get the value of ptPar
     *
     * @return the value of ptPar
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     * Set the value of ptPar
     *
     * @param ptPar new value of ptPar
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }
    
        /**
     * pourcentage de réussite des attaques 
     */
    protected int pourcentageAtt;

    /**
     * Get the value of pourcentageAtt
     *
     * @return the value of pourcentageAtt
     */
    public int getPourcentageAtt() {
        return pourcentageAtt;
    }

    /**
     * Set the value of pourcentageAtt
     *
     * @param pourcentageAtt new value of pourcentageAtt
     */
    public void setPourcentageAtt(int pourcentageAtt) {
        this.pourcentageAtt = pourcentageAtt;
    }
     /**
     * pourcentage de réussite des parades 
     */
    protected int pourcentagePar;

    /**
     * Get the value of pourcentagePar
     *
     * @return the value of pourcentagePar
     */
    public int getPourcentagePar() {
        return pourcentagePar;
    }

    /**
     * Set the value of pourcentagePar
     *
     * @param pourcentagePar new value of pourcentagePar
     */
    public void setPourcentagePar(int pourcentagePar) {
        this.pourcentagePar = pourcentagePar;
    }
        /** 
     *  Points de dégats des attaques 
     */
    protected int degAtt;

    /**
     * Get the value of degAtt
     *
     * @return the value of degAtt
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     * Set the value of degAtt
     *
     * @param degAtt new value of degAtt
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }
    
/* Constructeurs */
        public Creature(int ptVie,int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos) {
        this.ptVie = ptVie;
        this.ptPar = ptPar;
        this.pourcentageAtt = pourcentageAtt;
        this.pourcentagePar = pourcentagePar;
        this.degAtt = degAtt;
        this.pos = new Point2D(pos);
    }
            public Creature() {
        this.ptPar=0;
        this.degAtt = 0;
        this.pourcentagePar = 0;
        this.pourcentageAtt = 0;
        this.ptVie = 0;
        this.setPos(new Point2D());
    }
            
        public Creature(Creature c){
        this.ptPar=c.ptPar;
        this.degAtt = c.degAtt;
        this.pourcentagePar = c.pourcentagePar;
        this.pourcentageAtt = c.pourcentageAtt;
        this.ptVie = c.ptVie;
        this.setPos(c.pos);
    }
    
/* Méthodes */
    /**
     * déplace le monstre sur une case adjacente aléatoirement choisie
     */
    @Override
    public void deplace() {
        Random rand = new Random();
        pos.translate(rand.nextInt(3)-1, rand.nextInt(3)-1);
    }
    /**
     * 
     * @param dir direction dans laquelle le personnage est déplacé : 
     * h:haut b:bas d:droite ou g:gauche
     * @param nbCases nombre de cases que le personnage parcourt dans la direction choisie
     */
    public void deplace(char dir, int nbCases){
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
                //
                ;   
        }
    }

     /** 
     * affiche la position de la créature
     */
    public abstract void affiche();
}
