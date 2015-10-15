/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet.enregistrements;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.projet.objet.World;

/**
 *
 * @author oriane école
 */
public class SauvegardePartie {
    /**
     * nom du fichier de sauvegarde à charger
     */
    protected String source;
    /**
     * 
     */
    protected BufferedWriter fichier;

    //Constructeur
    public SauvegardePartie(String source) {
        this.source = source;
        try {
            this.fichier = new BufferedWriter(new FileWriter(source));
        } catch (IOException ex) {
            Logger.getLogger(SauvegardePartie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sauvegarderPartie(World monde) {
        try {
            //test
            String toto = "coucou ca va";
            fichier.write(toto);
            fichier.newLine();
            fichier.write("baaaajdbckjz");

            // appel des textes de sauvegarde de tous les éléments du jeu getTexteSauvegarde()
            // concaténation dans un fichier
            // choix du nom du fichier
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fichier.flush();
                fichier.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
