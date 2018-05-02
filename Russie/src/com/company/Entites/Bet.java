package com.company.Entites;


public class Bet {

    private int idBet;
    private int valeurr;
    private String etat;
    private User user;
    private Partie partie;
     
    private int nombreBetPersonne ; 
    
    public Bet() {
    }
     public Bet(int valeurr, String etat) {
             this.valeurr = valeurr;
        this.etat = etat;
    }


    public Bet(int idBet, int valeurr, String etat, User user, Partie partie) {
        this.idBet = idBet;
        this.valeurr = valeurr;
        this.etat = etat;
        this.user = user;
        this.partie = partie;
    }

    public Bet(int valeurr, String etat, User user, Partie partie) {
        this.valeurr = valeurr;
        this.etat = etat;
        this.user = user;
        this.partie = partie;
    }

    public int getIdBet() {
        return idBet;
    }

    public void setIdBet(int idBet) {
        this.idBet = idBet;
    }

    public int getValeurr() {
        return valeurr;
    }

    public void setValeurr(int valeurr) {
        this.valeurr = valeurr;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Bet(User user, int nombreBetPersonne) {
    
        this.user = user;
        
        this.nombreBetPersonne = nombreBetPersonne;
    }

    public int getNombreBetPersonne() {
        return nombreBetPersonne;
    }

    public void setNombreBetPersonne(int nombreBetPersonne) {
        this.nombreBetPersonne = nombreBetPersonne;
    }
    
    
   
}
