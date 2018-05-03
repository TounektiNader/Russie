/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;

/**
 *
 * @author Amouri Aziz
 */
public class Favoris {
    User user;
    Equipe equipe;

    public Favoris(User user, Equipe equipe) {
        this.user = user;
        this.equipe = equipe;
    }

     public Favoris() {
     
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }


}
