/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;
import java.util.logging.Logger;
/**
 *
 * @author oriane 
 */
public class Personnage extends Creature{
    /* Attributs */
/**
 * Nom du personnage
 */
    protected String nom;
/**
 * Nombre de point de parade
 */
    protected int ptMana;
/**
 * Pourcentage de réussite des attaques magiques du personnage
 */ 
    protected int pourcentageMag;
/**
 * Pourcentage de résistance aux attaques magiques du personnage
 */ 
    protected int pourcentageResistMag;
/**
 * Dégats des attaques magiques du personnage
 */ 
    protected int degMag;
/**
 * Distance d'attaque maximale du personnage
 */ 
    protected int distAttMax;
    /* Constructors */
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
    public Personnage( int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos,String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag) {
        super(ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, pos);
        this.nom = new String(nom);
        this.ptMana = ptMana;
        this.pourcentageMag = pourcentageMag;
        this.pourcentageResistMag = pourcentageResistMag;
        this.degMag = degMag;
    }

    public Personnage() {
        super();
        this.nom = "";
        this.ptMana = 0;
        this.pourcentageMag = 0;
        this.pourcentageResistMag = 0;
        this.degMag = 0;
    }

    public Personnage(Personnage p) {
        super(p);
        this.nom = p.nom;
        this.ptMana = p.ptMana;
        this.pourcentageMag = p.pourcentageMag;
        this.pourcentageResistMag = p.pourcentageResistMag;
        this.degMag = p.degMag;
    }

    /* methodes */

    public String getNom() {
        return nom;
    }

    public int getPtMana() {
        return ptMana;
    }

    public int getPourcentageMag() {
        return pourcentageMag;
    }

    public int getPourcentageResistMag() {
        return pourcentageResistMag;
    }
    public int getDegMag() {
        return degMag;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPtMana(int ptMana) {
        this.ptMana = ptMana;
    }
    public void setPourcentageMag(int pourcentageMag) {
        this.pourcentageMag = pourcentageMag;
    }

    public void setPourcentageResistMag(int pourcentageResistMag) {
        this.pourcentageResistMag = pourcentageResistMag;
    }
    public void setDegMag(int degMag) {
        this.degMag = degMag;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    

/**
 * Affiche le nom et la position du personnage
 */
    @Override
    public void affiche() {
        System.out.println("Je suis un personnage du nom de " + this.nom + " positionné en " + this.pos);
    }
}
