/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
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
    
    public World(){
        lesPersos = new ArrayList<>();
        lesMonstres = new ArrayList<>();
        lesObjets = new ArrayList<>();
        
        tailleMonde = 100; //taille par defaut
        // Initialisation du tableau
        matriceCreatures = new Creature[tailleMonde][tailleMonde];
        matriceObjets = new Objet[tailleMonde][tailleMonde];
        int ligne;
        int colonne;
        for( ligne = 0; ligne < tailleMonde; ligne++)
            for( colonne = 0; colonne < tailleMonde; colonne++){
                matriceCreatures[ligne][colonne] = null;
                matriceObjets[ligne][colonne] = null;
            }

    }
    public World(int tailleMonde){
        lesPersos = new ArrayList<>();
        lesMonstres = new ArrayList<>();
        lesObjets = new ArrayList<>();
        
        this.tailleMonde = tailleMonde; //taille par defaut
        // Initialisation du tableau
        matriceCreatures = new Creature[tailleMonde][tailleMonde];
        matriceObjets = new Objet[tailleMonde][tailleMonde];
        int ligne;
        int colonne;
        for( ligne = 0; ligne < tailleMonde; ligne++)
            for( colonne = 0; colonne < tailleMonde; colonne++){
                matriceCreatures[ligne][colonne] = null;
                matriceObjets[ligne][colonne] = null;
            }
                
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
     * @param element
     * @param point 
     * @return True si le placement est reussi, False si il n'y a pas de place au point indiqué
     */
    public boolean placer(ElementPhysique element, Point2D point)
    {
        if(element instanceof Creature)
        {
            // Ca veut dire si la place n'est pas libre, PlaceLibre est la contrainte de base qu'on respecte par defaut
            
            if(!(new PlaceLibre().respecteContrainte(this, element)))
                return false;
        
            // si on arrive là c'est que ya de la place donc on insere l'element
            if(element instanceof Personnage)
                lesPersos.add((Personnage) element);
            if(element instanceof Monstre)
                lesMonstres.add((Monstre) element);
            if(element instanceof Objet)
                lesObjets.add((Objet) element);

            matriceCreatures[point.getX()][point.getY()] = (Creature) element;

            element.setPos(point);
        }
        if(element instanceof Objet)
        {
            if(matriceObjets[point.getX()][point.getY()] != null)
                return false;

            lesObjets.add((Objet) element);
            matriceObjets[point.getX()][point.getY()] = (Objet) element;

            element.setPos(point);
        }
        
        return true;
    }
    /**
     * Place un élément dans le monde à une position aléatoire où aucun personnage ne se trouve
     * @param element l'element à placer
     * @return True si le placement est reussi, False si il n'y a pas de place
     */
    public boolean placer(ElementPhysique element)
    {
        ArrayList<Point2D> placesLibres = placesLibres();
        if(placesLibres.isEmpty() )
            return false;
        
        Random rnd = new Random();
        
        // On crée le point ou on va inserer l'element
        Point2D pointElement = placesLibres.get(rnd.nextInt(placesLibres.size())); // On choisit un point au hasard dans placesLibres
        return placer(element, pointElement);
    }
    
    /**
     * Place un élément dans le monde à une position aléatoire où aucun personnage ne se trouve
     * @param element l'element à placer
     * @return True si le placement est reussi, False si il n'y a pas de place
     */
    public boolean placer(ElementPhysique element, Contrainte contrainte)
    {
        ArrayList<Point2D> placesLibres = placesLibres();
        if(placesLibres.isEmpty() )
            return false;
        
        Random rnd = new Random();
        
        // On crée le point ou on va inserer l'element
        Point2D pointElement = placesLibres.get(rnd.nextInt(placesLibres.size())); // On choisit un point au hasard dans placesLibres
        return placer(element, pointElement);
    }
    
    /**
     * Parcours la matrice du monde pour retourner les vecteurs de places libres dans le monde (pour l'instant c'est juste pour les créatures!!
     * @return un vecteur des places libres dans le monde 
     */
    public ArrayList<Point2D> placesLibres()
    {
        boolean[][] matrice = PlaceLibre.creationMatriceBoolean(matriceCreatures) ;
        
        int ligne;
        int colonne;
        ArrayList<Point2D> pointsLibres = new ArrayList<>();
        
        for( ligne = 0; ligne < tailleMonde; ligne++)
        {
            for( colonne = 0; colonne < tailleMonde; colonne++)
            {
                if(!matrice[ligne][colonne]) // S'il n'y a personne en x ) ligne et y = colonne....
                    pointsLibres.add(new Point2D(ligne, colonne));
            }
        }
        return pointsLibres;
    }
    /**
     * crée un monde aléatoirement
     * le nombre de protagoniste de chaque type est aléatoire
     * la position des protagonistes est aléatoire
     * les protagonistes doivent etre suffisamment proche
     * et non superposés
     * 
     * 
     * @param nbPersos nb de protagonistes à créer
     * @param maxDist distance maximale entre les protagonistes
     */
    
    public boolean creeMondeAlea(int nbPersos){
        
        Random rand = new Random();
        ArrayList<Point2D> placesLibres;
        for (int i = 0; i<nbPersos;i++){
            
            placesLibres = placesLibres();
            if(placesLibres.isEmpty() )
                return false;
           
            
            placer(new Archer());
        }
        return true;
    }    
   
    /**
     * Affiche les protagonistes contenus dans le monde (position et nom s'ils en ont)
     */
    public void afficheWorld(){
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
    public void afficheMatrice(){
        int ligne = 0;
        int colonne = 0;
        for( ligne = 0; ligne < tailleMonde; ligne++)
        {
            for( colonne = 0; colonne < tailleMonde; colonne++)
                if(matriceCreatures[ligne][colonne] != null)
                    System.out.print(matriceCreatures[ligne][colonne].getClass().getSimpleName() + " ");
                else System.out.print(" - ");
                   
            System.out.print("\n");
        }

    }
}
