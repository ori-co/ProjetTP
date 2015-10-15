/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import org.centrale.projet.objet.enregistrements.ChargementPartie;
import org.centrale.projet.objet.enregistrements.SauvegardePartie;

/**
 *
 * @author Oriane
 */
public class MainClass {

    public static void main(String[] args) {
        Joueur j = new Joueur();
        ArrayList<Object> listeChoix = new ArrayList<>();
        String choix1 = "Nouvelle Partie";listeChoix.add(choix1);
        String choix2 = "Charger une partie sauvegardée";listeChoix.add(choix2);
        Object choix = j.choisi(listeChoix);

        World monde;

        if (choix.equals(choix1)) {
            monde = new World();
            monde.creeMondeAlea(30);
            monde.creationJoueur();
        } else {
            String source = "Sauvegarde-WoE.txt";
            ChargementPartie cp = new ChargementPartie(source);
            monde = cp.chargerPartie();
        }

        while (!monde.lesJoueurs.isEmpty()) {
            monde.afficheMatrice();
            monde.tourDeJeu();
            
            listeChoix = new ArrayList<>();
            choix1 = "Continuer";listeChoix.add(choix1);
            choix2 = "Sauvegarder et quitter";listeChoix.add(choix2);
            choix = j.choisi(listeChoix);
            
            if (choix.equals(choix2)){
                SauvegardePartie sp = new SauvegardePartie("Sauvegarde_"+System.currentTimeMillis()+".txt");
                sp.sauvegarderPartie(monde);
                break;
            }
        }
        monde.afficheWorld();
    }
}
