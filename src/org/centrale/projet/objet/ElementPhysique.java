/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author Sacha
 * Il s'agit d'un élément qu'on peut placer dans le monde
 */
public class ElementPhysique {
     /**
     * Position du monstre
     */
    protected Point2D pos;
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
}

