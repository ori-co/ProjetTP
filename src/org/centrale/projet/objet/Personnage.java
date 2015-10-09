/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oriane
 */
public abstract class Personnage extends Creature {
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
    /**
     * Nourriture que le personnage a avalés et qui sont encore actives
     */
    protected List<Nourriture> gosier;

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
     * @param distMaxAtt
     */
    public Personnage(int ptVie, int ptPar, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos, String nom, int ptMana, int pourcentageMag, int pourcentageResistMag, int degMag, int distMaxAtt) {
        super(ptVie, ptPar, pourcentageAtt, pourcentagePar, degAtt, pos);
        this.nom = nom;
        this.ptMana = ptMana;
        this.pourcentageMag = pourcentageMag;
        this.pourcentageResistMag = pourcentageResistMag;
        this.degMag = degMag;
        this.distAttMax = distMaxAtt;
        this.gosier = new LinkedList<>();
    }

    public Personnage() {
        super();
        this.nom = "";
        this.ptMana = 0;
        this.pourcentageMag = 0;
        this.pourcentageResistMag = 0;
        this.degMag = 0;
        this.distAttMax = 0;
        this.gosier = new LinkedList<>();
    }

    public Personnage(Personnage p) {
        super(p);
        this.nom = p.nom;
        this.ptMana = p.ptMana;
        this.pourcentageMag = p.pourcentageMag;
        this.pourcentageResistMag = p.pourcentageResistMag;
        this.degMag = p.degMag;
        this.distAttMax = p.distAttMax;
        this.gosier = new LinkedList<>(p.gosier);
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

    public List<Nourriture> getGosier() {
        return gosier;
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

    public void setGosier(List<Nourriture> gosier) {
        this.gosier = gosier;
    }

    /**
     * Affiche le nom et la position du personnage
     */
    @Override
    public abstract void affiche();

    /**
     * le personnage courant utilise ou ramasse l'objet utilisable passé en paramètre puis
     * cet obbjet est détruit
     *
     * @param u
     */
    public void utilise(Utilisable u) {
        if (pos.distance(u.getPos())==0) {
            u.estUtilise(this);

        // destruction de l'objet utlisé 
            // ??
        } else {
            System.out.println("Utilisation impossible");
        }

    }
    
    /**
     * Parcourt le gosier du Personnage et élimine les aliments consommés en annulant leurs effets
     */
    public void digere(){
//        Parcours Gosier Perso courant 
        for (Nourriture aliment : gosier){           
//		Si 0 sur la nouriture courante Faire
            if (aliment.nbTours == 0){              
//			enlever la nourriture du gosier
                gosier.remove(aliment);
//			appeler estComsomé pour revenir à l’état initial
                aliment.estConsomme(this);
//		Fin si
            } else {
//		-1 sur nbTours de la nourriture courante
            aliment.nbTours -= 1; 
            }
//	Fin Parcours Gosier
        }
    }
}
