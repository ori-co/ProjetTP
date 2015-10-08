package org.centrale.projet.objet.Contraintes;
import org.centrale.projet.objet.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Permet de creer des classes de type contraintes qu'on pourra transmettre à des fonctions telles que placer... 
 * C'est cool parceque ca permet de delocaliser le traitement des contrainte qui est assez specifique
 * et du coups d'alleger notre World qui est déjà assez lourd.
 * @author Sacha
 */
public interface Contrainte {
    /**
     * Vérifie si le personnage dont la position doit être specifiée peut être inseré 
     * dans le monde passé en parametre en respectant la contrainte définie par l'objet
     * qui implémente l'interface Contrainte
     * @param monde le monde sur lequel on voudrait inserer l'objet
     * @param element l'element qu'on voudrait inserer (position renseignée !
     * @return True si on peut inserer l'element, False sinon
     */
    public boolean respecteContrainte(World monde, ElementPhysique element);
}