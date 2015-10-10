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
import org.centrale.projet.objet.Contraintes.PlaceVide;
import org.centrale.projet.objet.Contraintes.PlaceAccessible;

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
        tailleMonde = 20; //taille par defaut
        lesJoueurs = new ArrayList<>();
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
     * Retourne vrai si la position est dispo et l'attribue à l'élément physique
     * Retourne faux sinon
     *
     * @param element
     * @param point
     * @param pl contrainte de placement de l'élement physique
     * @return True si le placement est reussi au point indiqué
     */
    public boolean placer(ElementPhysique element, Point2D point, PlaceAccessible pl) {
        if (pl.respecteContrainte(this, point)) {
            element.setPos(point);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Attribue une position dispo aléatoirement choisie à l'élément physique
     *
     * @param element l'element à placer
     * @return True si le placement est reussi, False si il n'y a pas de place
     */
    public boolean placer(ElementPhysique element, PlaceAccessible pl) {
        ArrayList<Point2D> places = pl.listeDePlaces(this);
        if (places.isEmpty()) {
            return false;
        }
        Random rnd = new Random();
        // On crée le point ou on va inserer l'element
        Point2D pointElement = places.get(rnd.nextInt(places.size())); // On choisit un point au hasard dans placesLibres
        return placer(element, pointElement, pl);
    }

    /**
     * Attribue une position à l'élement physique en fonction de la contrainte c
     *
     * @param element
     * @param c
     * @return
     */
    public boolean placer(ElementPhysique element, Contrainte c) {

        return true;
    }

    public ElementPhysique getElementByPos(Point2D p) {
        ElementPhysique[][] mat = this.toMatrice();
        int x = p.getX();
        int y = p.getY();

        return mat[x][y];
    }

    /**
     * crée un monde contenant un nombre de protagonistes donné et dont les
     * positions et les types sont aléatoirement choisis
     *
     *
     * @param nbBots nb de Bots à créer
     * @return vrai si le monde est créé, faux si le nombre de bots est trop
     * élevé
     */
    public boolean creeMondeAlea(int nbBots) {

        Random rand = new Random();
        ArrayList<Point2D> placesLibres;
        PlaceVide pl = new PlaceVide();

        try {
            ArrayList<Class> lesTypesDElements = new ArrayList<>();
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Archer"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Guerrier"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Mage"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Paysan"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Lapin"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Loup"));

            // Pondérations : chance d'avoir une Crature plus élevée que d'avoir un objet
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Archer"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Guerrier"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Mage"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Paysan"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Lapin"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Loup"));

            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Soin"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Mana"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Biscuit"));
            lesTypesDElements.add(Class.forName("org.centrale.projet.objet.Champignon"));

            for (int i = 0; i < nbBots; i++) {
                placesLibres = pl.listeDePlaces(this);
                if (placesLibres.isEmpty()) {
                    return false;
                }

                int k = rand.nextInt(lesTypesDElements.size());
                ElementPhysique element = (ElementPhysique) lesTypesDElements.get(k).newInstance();
                placer(element, new PlaceVide());
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
     * crée un nouveau joueur en demandant à l'utilisateur d'entrer le nom et le
     * type de personnage souhaité
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
                this.placer(p, new PlaceVide());
                this.lesJoueurs.add(new Joueur(p));
                break;
            case 2:
                p = new Guerrier();
                p.setNom(nom);
                this.placer(p, new PlaceVide());
                this.lesJoueurs.add(new Joueur(p));
                break;
            case 3:
                p = new Mage();
                p.setNom(nom);
                this.placer(p, new PlaceVide());
                this.lesJoueurs.add(new Joueur(p));
                break;
            case 4:
                p = new Paysan();
                p.setNom(nom);
                this.placer(p, new PlaceVide());
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
     * affiche graphiquement les types d'éléments physique à leur position dans
     * une matrice
     */
    public void afficheMatrice() {
        ElementPhysique[][] mat = this.toMatrice();

        String txt = "";
        for (int j = tailleMonde - 1; j >= 0; j--) {
            for (int i = 0; i < tailleMonde; i++) {
                if (mat[i][j] != null) {
                    txt += mat[i][j].getClass().getSimpleName();
                } else {
                    txt += "  ";
                }
                txt += "-";
            }
            txt += "\n";
        }
        System.out.println(txt);
    }

    public void tourDeJeu() {
//boucle sur les tours de jeu
//tourDeJeu()
        System.out.println("Nouveau Tour");
//	boucle sur les joueurs
        for (Joueur j : lesJoueurs) {
            System.out.println("\nA vous de jouer :");
            j.joue(this);
        }

//      boucle sur les bots
        for (ElementPhysique element : lesBots) {
            System.out.println("\nAu tour d'un "+element.getClass().getSimpleName()+" de jouer :");

            if (element instanceof Combattant) {
                // si l'élement est un Combattant, on lance un combat
                // Choix de l'adversaire
                Random rand = new Random();
                ArrayList<Creature> liste = ((Combattant) element).listeAdversaire(this);
                if (!liste.isEmpty()) {
                    System.out.print("Combat contre un adversaire aléatoirement choisi : ");
                    ((Combattant) element).combattre(liste.get(rand.nextInt(liste.size())));
                }
            } else {
                if (element instanceof Deplacable) {
                    // si l'élement est Deplacable, on le déplace, sur une case adjacente aléatoirement choisie
                    System.out.println("Déplacement aléatoire :");
                    this.placer(element, ((Deplacable) element).deplace(), new PlaceAccessible());
                } else {
                    // on ne fait rien
                }
            }

            if (element instanceof Personnage) {
                // si c'est une personnage, on digere
                ((Personnage) element).digere();
            }
        }
//fin tourDeJeu()
    }
}
