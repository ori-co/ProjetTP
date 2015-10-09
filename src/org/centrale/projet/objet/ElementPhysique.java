/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *Il s'agit d'un élément qu'on peut placer dans le monde
 * @author Sacha 
 */
public abstract class ElementPhysique {

    /**
     * Position du monstre
     */
    protected Point2D pos;

    public ElementPhysique(Point2D pos) {
        this.pos = pos;
    }
    public ElementPhysique(ElementPhysique e){
        this.pos=new Point2D(e.getPos());
    }
    public ElementPhysique(){
        this.pos = new Point2D();
    }

    /**
     * Get the value of pos
     *
     * @return the value of pos
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     * Set the value of pos
     *
     * @param pos new value of pos
     */
    public final void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    /**
     * Affiche l'élément physique dans la console 
     * sa position, son nom s'il en a un
     */
    public abstract void affiche();
}
