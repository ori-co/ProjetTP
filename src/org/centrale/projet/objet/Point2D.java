/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;
import java.util.Random;

/**
 *
 * @author Sacha
 */
public class Point2D {
    /**
     * Abcsisse du point
     */
    private int x;
    /**
     * Ordonnée du point
     */
    private int y;

    public Point2D() {
        
        x = 0;
        y = 0;
    }
    /**
     * Permet de creer un point aléatoire à partir d'un objet random
     * Pourquoi demander un objet random ? Pour eviter de re-générer la suite de nombre pseudo-aléatoire à chaque appel
     * @param rnd objet de type Random déjà initialisé
     * @param limiteSup Limite superieure de génération du point
     */
    public Point2D(Random rnd, int limiteSup) {
        x = rnd.nextInt(limiteSup);
        y = rnd.nextInt(limiteSup);
    }
    public Point2D(Point2D AutrePoint) {
        x = AutrePoint.getX();
        y = AutrePoint.getY();
    }
    
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void affiche(){
        System.out.println("[" + x + ", " + y + "]");
    }
    @Override
    public String toString(){
        return "[" + x + ", " + y + "]";
    }
    
    public void translate(int dx, int dy){
        x += dx;
        y += dy;
    }
    
    public void setPosition(int Nx, int Ny){
        x = Nx;
        y = Ny;
    }
    
    /** 
     * calcule le distance entre le point courant et le point mis en paramètre
     * @param p2 point par rapport auquel on calcule la distance
     * @return distance entre p2 et le point courant
     */
    public float distance(Point2D p2){
       float res;
       
       res = (float) Math.sqrt(Math.pow((this.x-p2.x),2)+Math.pow((this.y-p2.y),2));
       
       return res;
    }

}
