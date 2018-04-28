/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;



/**
 *
 * @author Nader
 */
public class Equipe implements Comparable<Equipe> {
//    private int idEquipe ;
//    private String nomEquipe;
//
//    public Equipe(){}
//    public int getIdEquipe() {
//        return idEquipe;
//    }
//
//    public void setIdEquipe(int idEquipe) {
//        this.idEquipe = idEquipe;
//    }
//
//    public String getNomEquipe() {
//        return nomEquipe;
//    }
//
//    public void setNomEquipe(String nomEquipe) {
//        this.nomEquipe = nomEquipe;
//    }
//
//    public Equipe(int idEquipe, String nomEquipe) {
//        this.idEquipe = idEquipe;
//        this.nomEquipe = nomEquipe;
//    }
    
    
   private int IDEquipe;
  private   String NomEquipe;
     private String Entraineur;
     private String Continent;
    private String Drapeau;
     private String Groupe;
    private  int ButMarque;
    private  int butEncaisse;
     private int MatchJouee;
    private int MatchNull;
    private int MatchGagne;
    private int Matchperdu;
    private int NombrePoints;

    
public Equipe(){}     

    public Equipe(int IDEquipe, String NomEquipe, String Entraineur, String Continent, String Drapeau, String Groupe, int ButMarque, int butEncaisse, int MatchJouee, int MatchNull, int MatchGagne, int Matchperdu, int NombrePoints) {
        this.IDEquipe = IDEquipe;
        this.NomEquipe = NomEquipe;
        this.Entraineur = Entraineur;
        this.Continent = Continent;
        this.Drapeau = Drapeau;
        this.Groupe = Groupe;
        this.ButMarque = ButMarque;
        this.butEncaisse = butEncaisse;
        this.MatchJouee = MatchJouee;
        this.MatchNull = MatchNull;
        this.MatchGagne = MatchGagne;
        this.Matchperdu = Matchperdu;
        this.NombrePoints = NombrePoints;
    }

    public Equipe(int IDEquipe, String NomEquipe) {
        this.IDEquipe = IDEquipe;
        this.NomEquipe = NomEquipe;
    }

    public Equipe(int IDEquipe, String NomEquipe, String Groupe, int ButMarque, int butEncaisse, int MatchJouee, int MatchNull, int MatchGagne, int Matchperdu, int NombrePoints) {
        this.IDEquipe = IDEquipe;
        this.NomEquipe = NomEquipe;
        this.Groupe = Groupe;
        this.ButMarque = ButMarque;
        this.butEncaisse = butEncaisse;
        this.MatchJouee = MatchJouee;
        this.MatchNull = MatchNull;
        this.MatchGagne = MatchGagne;
        this.Matchperdu = Matchperdu;
        this.NombrePoints = NombrePoints;
    }
    
    
     


    public int getIDEquipe() {
        return IDEquipe;
    }

    public void setIDEquipe(int IDEquipe) {
        this.IDEquipe = IDEquipe;
    }

    public String getNomEquipe() {
        return NomEquipe;
    }

    public void setNomEquipe(String NomEquipe) {
        this.NomEquipe = NomEquipe;
    }

    public String getEntraineur() {
        return Entraineur;
    }

    public void setEntraineur(String Entraineur) {
        this.Entraineur = Entraineur;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String Continent) {
        this.Continent = Continent;
    }

    public String getDrapeau() {
        return Drapeau;
    }

    public void setDrapeau(String Drapeau) {
        this.Drapeau = Drapeau;
    }

    public String getGroupe() {
        return Groupe;
    }

    public void setGroupe(String Groupe) {
        this.Groupe = Groupe;
    }
     public int getButMarque() {
        return ButMarque;
    }

    public void setButMarque(int ButMarque) {
        this.ButMarque = ButMarque;
    }

    public int getButEncaisse() {
        return butEncaisse;
    }

    public void setButEncaisse(int butEncaisse) {
        this.butEncaisse = butEncaisse;
    }

    public int getMatchJouee() {
        return MatchJouee;
    }

    public void setMatchJouee(int MatchJouee) {
        this.MatchJouee = MatchJouee;
    }

    public int getMatchNull() {
        return MatchNull;
    }

    public void setMatchNull(int MatchNull) {
        this.MatchNull = MatchNull;
    }

    public int getMatchGagne() {
        return MatchGagne;
    }

    public void setMatchGagne(int MatchGagne) {
        this.MatchGagne = MatchGagne;
    }

    public int getMatchperdu() {
        return Matchperdu;
    }

    public void setMatchperdu(int Matchperdu) {
        this.Matchperdu = Matchperdu;
    }

    public int getNombrePoints() {
        return NombrePoints;
    }
    
    public void setNombrePoints(int NombrePoints) {
        this.NombrePoints = NombrePoints;
    }

    @Override
    public String toString() {
        return "Equipe{" + "IDEquipe=" + IDEquipe + ", NomEquipe=" + NomEquipe + ", Entraineur=" + Entraineur + ", Continent=" + Continent + ", Drapeau=" + Drapeau + ", Groupe=" + Groupe + ", ButMarque=" + ButMarque + ", butEncaisse=" + butEncaisse + ", MatchJouee=" + MatchJouee + ", MatchNull=" + MatchNull + ", MatchGagne=" + MatchGagne + ", Matchperdu=" + Matchperdu + ", NombrePoints=" + NombrePoints + '}';
    }

    
  @Override
    public int compareTo(Equipe e) {
       
       
         if (this.getNombrePoints() > e.getNombrePoints()) 
    { return -1             ; }
    else if (this.getNombrePoints() <e.getNombrePoints()){
            return 1; }
    return 0;  }
    
    
   
}
