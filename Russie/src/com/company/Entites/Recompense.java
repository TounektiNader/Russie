/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;


public class Recompense {
    
    private int idRecompense ; 
    private Cadeau cadeau ; 
    private User user ;
    
    public Recompense(){}

    public Recompense(int idRecompense, Cadeau cadeau, User user) {
        this.idRecompense = idRecompense;
        this.cadeau = cadeau;
        this.user = user;
    }

    public Recompense(Cadeau cadeau, User user) {
        this.cadeau = cadeau;
        this.user = user;
    }

    public int getIdRecompense() {
        return idRecompense;
    }

    public void setIdRecompense(int idRecompense) {
        this.idRecompense = idRecompense;
    }

    public Cadeau getCadeau() {
        return cadeau;
    }

    public void setCadeau(Cadeau cadeau) {
        this.cadeau = cadeau;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
