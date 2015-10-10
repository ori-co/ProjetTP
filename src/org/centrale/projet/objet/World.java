/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.projet.objet.Contraintes.Contrainte;
import org.centrale.projet.objet.Contraintes.PlaceLibre;

/**
 *
 * @author Oriane et Sacha
 */
public class World {

    /**
     * taille du monde, nombre de cases en largeur et hauteur
     */
    public static int tailleMonde;
    /**
     * liste des bots du jeu
     */
    public List<ElementPhysique> lesBots;
    /**
     * Liste des Joueurs (utilisateirs réals)
     */
    public ArrayList<Joueur> lesJoueurs;

    public World() {
        lesBots = new ArrayList<>();
        tailleMonde = 50; //taille par defaut
        lesJoueurs = new ArrayList<>();
    }

    public int getTailleMondee() {
        return tailleMonde;
    }

    public List<ElementPhysique> getLesBots() {
        return lesBots;
    }

    public ArrayList<Joueur> getLesJoueurs() {
        return lesJoueurs;
    }

        /**
     * place les éléments physique dans une matrice à leur position
     *
     * @return
     */
    public ElementPhysique[][] toMatrice() {
        ElementPhysique[][] matriceMonde = new ElementPhysique[tailleMonde][tailleMonde];
        for (ElementPhysique e : lesBots) {
            int x = e.getPos().getX();
            int y = e.getPos().getY();
            matriceMonde[x][y] = e;
        }
        for (Joueur j : lesJoueurs) {
            int x = j.getPerso().getPos().getX();
            int y = j.getPerso().getPos().getY();
            matriceMonde[x][y] = j.getPerso();
        }
        return matriceMonde;
    }
    
    /**
     * Retourne vrai si la position est libre et l'attribue à l'élément physique
     * Retourne faux sinon
     *
     * @param element
     * @param point
     * @return True si le placement est reussi
     * au point indiqué
     */
    public boolean placer(ElementPhysique element, Point2D point) {
        PlaceLibre pl = new PlaceLibre();
        if (pl.respecteContrainte(this, element)){
            element.setPos(point);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Attribue une position libre aléatoirement choisie à l'élément physique
     *
     * @param element l'element à placer
     * @return True si le placement est reussi, False si il n'y a pas de place
     */
    public boolean placer(ElementPhysique element) {
        ArrayList<Point2D> placesLibres = placesLibres();
        if (placesLibres.isEmpty()) {
            return false;
        }

        Random rnd = new Random();

        // On crée le point ou on va inserer l'element
        Point2D pointElement = placesLibres.get(rnd.nextInt(placesLibres.size())); // On choisit un point au hasard dans placesLibres
        return placer(element, pointElement);
    }

    /**
     * Attribue une position libre aléatoirement choisie à l'élément physique 
     * respectant une contrainte donnée
     * @param element élément physique à positionner
     * @param c contrainte à respecter
     * @return retourne vrai si l'élément a été placé
     */
    public boolean placer(ElementPhysique element, Contrainte c){
        
        return true;
    }
    
    /**
     * Retourner le vecteur des places libres dans le monde 
     *
     * @return un vecteur des places libres dans le monde
     */
    public ArrayList<Point2D> placesLibres() {
        ArrayList<Point2D> pointsLibres = new ArrayList<>();
        ElementPhysique[][] mat = this.toMatrice();
        for (int i = 0; i < tailleMonde; i++) {
            for (int j = 0; j < tailleMonde; j++) {
                if (mat[i][j] != null) {

                } else {
                    pointsLibres.add(new Point2D(i, j));
                }
            }
        }
        return pointsLibres;
    }

    /**
     * crée un monde contenant un nombre de protagonistes donné et dont les
     * positions et les types sont aléatoirement choisis
     *
     *
     * @param nbPersos nb de Bots à créer
     * @return vrai si le monde est créé, faux si le nombre de bots est trop élevé
     */
    public boolean creeMondeAlea(int nbPersos) {

        Random rand = new Random();
        ArrayList<Point2D> placesLibres;

        try {
            ArrayList<Class> lesTypesDeCreatures = new ArrayList<>();
            lesTypesDeCreatures.add(Class.forName("org.centrale.projet.objet.Archer"));
            lesTypesDeCreatures.add(Class.forName("org.centrale.projet.objet.Guerrier"));
            lesTypesDeCreatures.add(Class.forName("org.centrale.projet.objet.Mage"));
            lesTypesDeCreatures.add(Class.forName("org.centrale.projet.objet.Paysan"));
            lesTypesDeCreatures.add(Class.forName("org.centrale.projet.objet.Lapin"));
            lesTypesDeCreatures.add(Class.forName("org.centrale.projet.objet.Loup"));

            for (int i = 0; i < nbPersos; i++) {

                placesLibres = placesLibres();
                if (placesLibres.isEmpty()) {
                    return false;
                }

                int k = rand.nextInt(lesTypesDeCreatures.size());
                ElementPhysique element = (ElementPhysique) lesTypesDeCreatures.get(k).newInstance();
                placer(element);
                lesBots.add(element);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }


    /**
     * crée un nouveau joueur en demandant à l'utilisateur 
     * d'entrer le nom et le type de personnage souhaité
     */
    public void creationJoueur() {
        System.out.println("Creation d'un joueur");
        Scanner sc;

        System.out.println("Nom du personnage : ");
        sc = new Scanner(System.in);
        String nom = sc.nextLine();

        Personnage p;
        int type = 0;
        while (type < 1 || type > 4) {
            System.out.println("Type de personnage (1:Archer, 2:Guerrier, 3:Mage ou 4:Paysan) : ");
            type = sc.nextInt();
        }
        switch (type) {
            case 1:
                p = new Archer();
                p.setNom(nom);
                this.placer(p);
                this.lesJoueurs.add(new Joueur(p));
                break;
            case 2:
                p = new Guerrier();
                p.setNom(nom);
                this.placer(p);
                this.lesJoueurs.add(new Joueur(p));
                break;
            case 3:
                p = new Mage();
                p.setNom(nom);
                this.placer(p);
                this.lesJoueurs.add(new Joueur(p));
                break;
            case 4:
                p = new Paysan();
                p.setNom(nom);
                this.placer(p);
                this.lesJoueurs.add(new Joueur(p));
                break;
            default:
        }
    }

    /**
     * Affiche les protagonistes contenus dans le monde (position et nom s'ils
     * en ont)
     */
    public void afficheWorld() {
        System.out.println("\nDans WoECN aujourd'hui");
        for (ElementPhysique e : lesBots) {
            e.affiche();
        }
        for (Joueur joueur : lesJoueurs) {
            joueur.getPerso().affiche();
        }
    }

    /**
     * affiche graphiquement les types d'éléments physique à leur position dans une matrice
     */
    public void afficheMatrice() {
        ElementPhysique[][] mat = this.toMatrice();
        
        String txt = "";
        for (int i = 0; i < tailleMonde; i++) {
            for (int j = 0; j < tailleMonde; j++) {
                if (mat[i][j] != null) {
                    txt += mat[i][j].getClass().getSimpleName();
                } else {
                   txt += "  ";
                }
                txt +="-";
            }
            txt+="\n";
        }
        System.out.println(txt);
    }
}
