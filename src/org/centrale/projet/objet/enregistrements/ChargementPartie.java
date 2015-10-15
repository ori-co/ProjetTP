/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet.enregistrements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.projet.objet.Archer;
import org.centrale.projet.objet.Creature;
import org.centrale.projet.objet.ElementPhysique;
import org.centrale.projet.objet.Joueur;
import org.centrale.projet.objet.Nourriture;
import org.centrale.projet.objet.NuageToxique;
import org.centrale.projet.objet.Objet;
import org.centrale.projet.objet.Personnage;
import org.centrale.projet.objet.Point2D;
import org.centrale.projet.objet.Potion;
import org.centrale.projet.objet.World;

/**
 *
 * @author oriane école
 */
public class ChargementPartie {
    /**
     * nom du fichier de sauvegarde à charger
     */
    protected String source;
    /**
     * 
     */
    protected BufferedReader fichier;

    //Constructeur
    public ChargementPartie(String source) {
        this.source = source;
        try {
            this.fichier = new BufferedReader(new FileReader(source));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChargementPartie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Méthodes
    public World chargerPartie() {

        World monde = new World();
        try {
            // Lire la sauvegarde
            String delimiteur = " ";
            String ligne;
            // On parcours le fichier ligne à ligne
            ligne = fichier.readLine();
            while (ligne != null) {
                // On sépare le contenu de la ligne selon un délimiteur espace
                StringTokenizer tokenizer = new StringTokenizer(ligne, delimiteur);
                // On stocke les mots ainsi découpés
                ArrayList<String> mots = new ArrayList<>();
                while (tokenizer.hasMoreTokens()) {
                    mots.add(tokenizer.nextToken());
                }
                // on ajoute au monde l'élément de jeu défini par la ligne courante
                if (mots.get(0).equals("Taille")) {
                    World.tailleMonde = Integer.parseInt(mots.get(1));
                } else {
                    if(mots.get(0).equals("Joueur")){
                        mots.remove(mots.get(0));
                        ElementPhysique nv = this.creerElement(mots);
                        Joueur nvj = new Joueur((Personnage)nv);
                        monde.lesJoueurs.add(nvj);
                    } else {
                        ElementPhysique nv= this.creerElement(mots);
                        monde.lesBots.add(nv);
                    }
                }
                ligne = fichier.readLine();
            }

            // modifier World
            // retourner le monde créé
            return monde;
        } catch (IOException ex) {
            Logger.getLogger(ChargementPartie.class.getName()).log(Level.SEVERE, null, ex);
            // ne retourne rien
            return null;
        }

        
    }
    
    /** 
     * Creation d'un elément de jeu définis dans le monde
     * @param mots définition de l'élément de jeu à ajouter
     * @return l'élement du jeu créé
    *
     */
    public ElementPhysique creerElement(ArrayList<String> mots) {
            // le mot d'indice 0 désigne la classe de l'élément
        // les mots suivants sont les paramètres du constructeur asocié
        try {

            if (Class.forName("org.centrale.projet.objet." + mots.get(0)) instanceof Class) {
                Object item = Class.forName("org.centrale.projet.objet." + mots.get(0)).newInstance();
                if (item instanceof ElementPhysique) {
                    // on définie la position
                    ((ElementPhysique) item).setPos(new Point2D(Integer.parseInt(mots.get(1)), Integer.parseInt(mots.get(2))));

                    if (item instanceof Creature) {
                        //on définit ptVie, ptPar, %Att, %Par et degAtt
                        ((Creature) item).setPtVie(Integer.parseInt(mots.get(3)));
                        ((Creature) item).setPtPar(Integer.parseInt(mots.get(4)));
                        ((Creature) item).setPourcentageAtt(Integer.parseInt(mots.get(5)));
                        ((Creature) item).setPourcentagePar(Integer.parseInt(mots.get(6)));
                        ((Creature) item).setDegAtt(Integer.parseInt(mots.get(7)));

                        if (item instanceof Personnage) {
                            // on définit nom, ptMana, %Mag, %resistMagie, degMag et distanceMax
                            ((Personnage) item).setNom(mots.get(8));
                            ((Personnage) item).setPtMana(Integer.parseInt(mots.get(9)));
                            ((Personnage) item).setPourcentageMag(Integer.parseInt(mots.get(10)));
                            ((Personnage) item).setPourcentageResistMag(Integer.parseInt(mots.get(11)));
                            ((Personnage) item).setDegMag(Integer.parseInt(mots.get(12)));
                            ((Personnage) item).setDistAttMax(Integer.parseInt(mots.get(13)));

                            if (item instanceof Archer) {
                                //on définit nbFleches
                                ((Archer) item).setNbFleches(Integer.parseInt(mots.get(14)));
                            }
                        }
                    } else {
                        if (item instanceof Objet) {
                            // on définit etiquette
                            ((Objet) item).setEtiquette(mots.get(3));

                            if (item instanceof Potion) {
                                // on définit nbPt
                                ((Potion) item).setNbPt(Integer.parseInt(mots.get(4)));
                            } else {
                                if (item instanceof Nourriture) {
                                    // on défnit nbPt et nbTours
                                    ((Nourriture) item).setNbPt(Integer.parseInt(mots.get(4)));
                                    ((Nourriture) item).setNbTours(Integer.parseInt(mots.get(5)));
                                } else {
                                    if (item instanceof NuageToxique) {
                                        // on définit charge
                                        ((NuageToxique) item).setCharge(Integer.parseInt(mots.get(4)));
                                    }
                                }
                            }
                        }
                    }
                }
                return (ElementPhysique) item;
            } else {
                return null;
            }
            

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
