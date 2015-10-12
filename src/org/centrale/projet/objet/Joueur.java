/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.lang.reflect.InvocationTargetException;
import org.centrale.projet.objet.World;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.projet.objet.Contraintes.PlaceAccessible;

/**
 *
 * @author
 */
public class Joueur {

    protected Personnage perso;

    public Joueur(Personnage perso) {
        this.perso = perso;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    public Personnage getPerso() {
        return perso;
    }

    public Object choisi(ArrayList<Object> listeChoix) {
        Scanner sc;
        // Affiche la liste de choix
        for (Object o : listeChoix) {
            int index = listeChoix.indexOf(o) + 1;
            System.out.print(" " + index + " : ");
            if (o instanceof ElementPhysique) {
                ((ElementPhysique) o).affiche();
            } else {
                if (o instanceof Class) {
                    System.out.print(((Class) o).getSimpleName());
                } else {
                    if (o instanceof Method) {
                        System.out.print(((Method) o).getName());
                    } else {
                        System.out.print(o.toString());
                    }
                }
            }
        }
        System.out.println();
        // entrée au clavier d'un choix
        sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix <= 0 || choix > listeChoix.size()) {
            System.out.println("Choix erroné. Recommencez");
            sc = new Scanner(System.in);
            choix = sc.nextInt();
        }
        // retourne l'élément choisi
        return listeChoix.get(choix - 1);
    }

    public void joue(World monde) {
        perso.affiche();

        if (perso instanceof Combattant) {
            ArrayList<Method> listeChoix = new ArrayList<>();

            try {
                // Deplacement ou bien Combat
                listeChoix.add(this.getClass().getMethod("deplacement", World.class));
                listeChoix.add(this.getClass().getMethod("combat", World.class));
            } catch (NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Que voulez-vous faire ?");
            Method choix = (Method) this.choisi((ArrayList<Object>) (Object) listeChoix);
            try {
                choix.invoke(this, monde);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.deplacement(monde);
        }
//          digere
        perso.digere();
    }

    public void deplacement(World monde) {
        System.out.println("Déplacement : ");

        ArrayList<String> listeChoix = new ArrayList<>();

        //Directions possibles
        listeChoix.add("Haut");
        listeChoix.add("Bas");
        listeChoix.add("Droite");
        listeChoix.add("Gauche");

        System.out.println("Dans quelle direction souhaitez-vous vous déplacer ?");
        String dir = (String) this.choisi((ArrayList<Object>) (Object) listeChoix);
        char d =dir.toLowerCase().charAt(0);

        // Point de destination et éventuel objet sur place stocké
        Point2D nouvP = perso.deplace(d, 1);
        ElementPhysique element = null;
        if (nouvP.getX() >= 0 && nouvP.getX() < World.tailleMonde && nouvP.getY() >= 0 && nouvP.getY() < World.tailleMonde) {
            element = monde.getElementByPos(nouvP);
        }

        // déplacement effectué avec la contrainte Place accessible (cad que la destination peut contenir un objet utilisable)
        boolean deplacement = monde.placer(perso, nouvP, new PlaceAccessible());
        if (deplacement) {
            // si le deplacement à bien eu lieu
            System.out.println("Déplacement effectué");
            if (element != null) {
                // s'il y avait un objet utilisable sur la case, on l'utlise et le retire de la carte
                perso.utilise((Utilisable) element);
                monde.lesBots.remove(element);
            }
        } else {
            // si le déplacement n'est pas possible, on recommence
            System.out.println("Déplacement impossible dans cette direction");
            this.deplacement(monde);
        }
    }

    public void combat(World monde) {
        System.out.print("Combat : ");
        // Attention si le perso n'est pas Combattant
        // choix de l'adversaire (liste des aversaires possibles ?)
        Random rand = new Random();
        ArrayList<Creature> liste = ((Combattant) perso).listeAdversaire(monde);
        if (!liste.isEmpty()) {
            System.out.println("Choisissez votre adversaire");
            Creature adver = (Creature) this.choisi((ArrayList<Object>) (Object) liste);
            ((Combattant) perso).combattre(adver);
        } else {
            System.out.println("Aucun combat possible");
            this.deplacement(monde);
        }
    }
}
