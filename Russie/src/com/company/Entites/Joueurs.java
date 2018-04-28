/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;





/**
 *
 * @author elbrh
 */
public class Joueurs extends Equipe{
    
    int Idjoueur ;
    String NomJoueur ;
    String PrenomJoueur;
    String ImageJoueur;
    String Position;
    Equipe equipe;

    public Joueurs(int Idjoueur, String NomJoueur, String PrenomJoueur, String ImageJoueur , String Position, Equipe equipe) {
        this.Idjoueur = Idjoueur;
        this.NomJoueur = NomJoueur;
        this.PrenomJoueur = PrenomJoueur;
        this.ImageJoueur=ImageJoueur;
        this.Position = Position;
        this.equipe=equipe;
    }

    public Joueurs() {
    }

    public int getIdjoueur() {
        return Idjoueur;
    }

    public void setIdjoueur(int Idjoueur) {
        this.Idjoueur = Idjoueur;
    }

    public String getNomJoueur() {
        return NomJoueur;
    }

    public void setNomJoueur(String NomJoueur) {
        this.NomJoueur = NomJoueur;
    }

    public String getPrenomJoueur() {
        return PrenomJoueur;
    }

    public void setPrenomJoueur(String PrenomJoueur) {
        this.PrenomJoueur = PrenomJoueur;
    }

    public String getImageJoueur() {
        return ImageJoueur;
    }

    public void setImageJoueur(String ImageJoueur) {
        this.ImageJoueur = ImageJoueur;
    }
    

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Joueurs{" + "Idjoueur=" + Idjoueur + ", NomJoueur=" + NomJoueur + ", PrenomJoueur=" + PrenomJoueur + ", ImageJoueur=" + ImageJoueur + ", Position=" + Position + ", equipe=" + equipe.toString() + '}';
    }
 
    
    
}



    
    
    
    
