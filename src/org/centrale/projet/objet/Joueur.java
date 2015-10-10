/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
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

    public void joue(World monde) {
        perso.affiche();
        Scanner sc;
        sc = new Scanner(System.in);
        int action = 0;
        if (perso instanceof Combattant) {
            // {choix entre déplacer et combattre (entrée texte)}
            while (action != 1 && action != 2) {
                System.out.println("\nSouhaitez-vous vous déplacer (1) ou combattre (2) ?");
                action = sc.nextInt();
            }
        } else {
            action = 1;
        }

        switch (action) {
//méthode deplace(char dir, int nbCases) avec dir = h:haut b:bas d:droite ou g:gauche
//méthode combattre(Cretaure) : choix de l’adversaire (entrée text)
            case 1:
                // déplacement
                this.deplacement(monde);
                break;
            case 2:
                // combat
                this.combat(monde);
                break;
            default:
        }
//          digere
        perso.digere();
    }

    public void deplacement(World monde) {
        boolean deplacement = false;
        while (!deplacement) {
            // Entrée au clavier de la direction du déplacement
            char dir = 'n';
            while (dir != 'h' && dir != 'b' && dir != 'd' && dir != 'g') {
                System.out.println("Déplacement : Dans quelle direction souhaitez-vous vous déplacer ?\n(h:haut, b:bas, d:droite, g:gauche) ");
                Scanner sc;
                sc = new Scanner(System.in);
                dir = sc.next().charAt(0);
            }
            // Point de destination et éventuel objet sur place stocké
            Point2D nouvP = perso.deplace(dir, 1);
            ElementPhysique element = monde.getElementByPos(nouvP);

            // déplacement effectué avec la contrainte Place accessible (cad que la destination peut contenir un objet utilisable)
            deplacement = monde.placer(perso, nouvP, new PlaceAccessible());
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
            }
        }
    }

    private void combat(World monde) {
        // Attention si le perso n'est pas Combattant
        // choix de l'adversaire (liste des aversaires possibles ?)
                Random rand = new Random();
                ArrayList<Creature> liste = ((Combattant)perso).listeAdversaire(monde);
                if (!liste.isEmpty()){
                    System.out.println("Combat : adversaire aléatoire");
                ((Combattant)perso).combattre(liste.get(rand.nextInt(liste.size())));
                }else {
                    System.out.println("Aucun combat possible");
                    this.deplacement(monde);
                }
    }
}
