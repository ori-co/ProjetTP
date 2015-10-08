/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author oriane 
 */
public class Objet extends ElementPhysique {
    
    /**
     * nom de l'objet
     */
    protected String etiquette;
    
    public Objet(){
        pos = new Point2D();
        etiquette = "objet inconnu";
    }
    
    public Objet(Point2D p, String et){
        pos = new Point2D(p);
        etiquette = et;
    }



    public String getEtiquette() {
        return etiquette;
    }


    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
    
    public void affiche() {
        System.out.println("La potion "+ etiquette+" est situé en "+ pos);
    }
    
}
