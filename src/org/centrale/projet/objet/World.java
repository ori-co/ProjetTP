/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.Iterator;
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
    public int tailleMonde;

    /**
     * Matrice des créatures
     */
    public Creature[][] matriceCreatures;
    /**
     * Matrice des objets
     */
    public Objet[][] matriceObjets;
    /**
     * liste des personnages du jeu
     */
    public ArrayList<Personnage> lesPersos;
    /**
     * liste des Monstres du jeu
     */
    public ArrayList<Monstre> lesMonstres;
    /**
     * liste des Objets du jeu
     */
    public ArrayList<Objet> lesObjets;

    public ArrayList<Joueur> lesJoueurs;

    public ArrayList<Joueur> getLesJoueurs() {
        return lesJoueurs;
    }

    public Creature[][] getMatriceCreatures() {
        return matriceCreatures;
    }

    public void setMatriceCreatures(Creature[][] matriceCreatures) {
        this.matriceCreatures = matriceCreatures;
    }

    public Objet[][] getMatriceObjets() {
        return matriceObjets;
    }

    public void setMatriceObjets(Objet[][] matriceObjets) {
        this.matriceObjets = matriceObjets;
    }

    public World() {
        lesPersos = new ArrayList<>();
        lesMonstres = new ArrayList<>();
        lesObjets = new ArrayList<>();

        tailleMonde = 100; //taille par defaut
        // Initialisation du tableau
        matriceCreatures = new Creature[tailleMonde][tailleMonde];
        matriceObjets = new Objet[tailleMonde][tailleMonde];
        int ligne;
        int colonne;
        for (ligne = 0; ligne < tailleMonde; ligne++) {
            for (colonne = 0; colonne < tailleMonde; colonne++) {
                matriceCreatures[ligne][colonne] = null;
                matriceObjets[ligne][colonne] = null;
            }
        }
        lesJoueurs = new ArrayList<>();

    }

    public World(int tailleMonde) {
        lesPersos = new ArrayList<>();
        lesMonstres = new ArrayList<>();
        lesObjets = new ArrayList<>();

        this.tailleMonde = tailleMonde; //taille par defaut
        // Initialisation du tableau
        matriceCreatures = new Creature[tailleMonde][tailleMonde];
        matriceObjets = new Objet[tailleMonde][tailleMonde];
        int ligne;
        int colonne;
        for (ligne = 0; ligne < tailleMonde; ligne++) {
            for (colonne = 0; colonne < tailleMonde; colonne++) {
                matriceCreatures[ligne][colonne] = null;
                matriceObjets[ligne][colonne] = null;
            }
        }

        lesJoueurs = new ArrayList<>();
    }

    public void setTailleMondee(int tailleMonde) {
        this.tailleMonde = tailleMonde;
    }

    public int getTailleMondee() {
        return tailleMonde;
    }

    /**
     * Place l'element à l'endroit indiqué s'il n'y a rien et update la matrice.
     * Tout placement de personnage doit passer par cette methode !
     *
     * @param element
     * @param point
     * @return True si le placement est reussi, False si il n'y a pas de place
     * au point indiqué
     */
    public boolean placer(ElementPhysique element, Point2D point) {
        if (element instanceof Creature) {
            // Ca veut dire si la place n'est pas libre, PlaceLibre est la contrainte de base qu'on respecte par defaut

            if (!(new PlaceLibre().respecteContrainte(this, element))) {
                return false;
            }

            // si on arrive là c'est que ya de la place donc on insere l'element
            if (element instanceof Personnage) {
                lesPersos.add((Personnage) element);
            }
            if (element instanceof Monstre) {
                lesMonstres.add((Monstre) element);
            }
            if (element instanceof Objet) {
                lesObjets.add((Objet) element);
            }

            matriceCreatures[point.getX()][point.getY()] = (Creature) element;

            element.setPos(point);
        }
        if (element instanceof Objet) {
            if (matriceObjets[point.getX()][point.getY()] != null) {
                return false;
            }

            lesObjets.add((Objet) element);
            matriceObjets[point.getX()][point.getY()] = (Objet) element;

            element.setPos(point);
        }

        return true;
    }

    /**
     * Place un élément dans le monde à une position aléatoire où aucun
     * personnage ne se trouve
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
     * Place un élément dans le monde à une position aléatoire où aucun
     * personnage ne se trouve
     *
     * @param element l'element à placer
     * @return True si le placement est reussi, False si il n'y a pas de place
     */
    public boolean placer(ElementPhysique element, Contrainte contrainte) {
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
     * Parcours la matrice du monde pour retourner les vecteurs de places libres
     * dans le monde (pour l'instant c'est juste pour les créatures!!
     *
     * @return un vecteur des places libres dans le monde
     */
    public ArrayList<Point2D> placesLibres() {
        boolean[][] matrice = PlaceLibre.creationMatriceBoolean(matriceCreatures);

        int ligne;
        int colonne;
        ArrayList<Point2D> pointsLibres = new ArrayList<>();

        for (ligne = 0; ligne < tailleMonde; ligne++) {
            for (colonne = 0; colonne < tailleMonde; colonne++) {
                if (!matrice[ligne][colonne]) // S'il n'y a personne en x ) ligne et y = colonne....
                {
                    pointsLibres.add(new Point2D(ligne, colonne));
                }
            }
        }
        return pointsLibres;
    }

    /**
     * crée un monde contenant un nombre de protagonistes donné 
     * et dont les positions et les types sont aléatoirement choisis
     *
     *
     * @param nbPersos nb de protagonistes à créer
     * @return
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
                placer((ElementPhysique) lesTypesDeCreatures.get(k).newInstance());

            }
           return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }

    /**
     * Affiche les protagonistes contenus dans le monde (position et nom s'ils
     * en ont)
     */
    public void afficheWorld() {
        System.out.println("\nDans WoECN aujourd'hui");
        for (Personnage p : lesPersos) {
            p.affiche();
        }
        for (Monstre m : lesMonstres) {
            m.affiche();
        }
        for (Objet o : lesObjets) {
            o.affiche();
        }

    }

    public void afficheMatrice() {
        for (int ligne = 0; ligne < tailleMonde; ligne++) {
            for (int colonne = 0; colonne < tailleMonde; colonne++) {
                if (matriceCreatures[ligne][colonne] != null) {
                    System.out.print(matriceCreatures[ligne][colonne].getClass().getSimpleName() + " ");
                } else {
                    System.out.print(" - ");
                }
            }

            System.out.print("\n");
        }

    }

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
}
