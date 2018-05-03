/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;

import com.company.Entites.User;

/**
 *
 * @author gaelfameni
 */
public class Actualite {

    private int idactualite;

    private String titre;
    private String texte;
    private String image;

    private User user;

    public int getIdactualite() {
        return idactualite;
    }

    public void setIdactualite(int idactualite) {
        this.idactualite = idactualite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Actualite(int idactualite, String titre, String texte, String image) {
        this.idactualite = idactualite;
        this.titre = titre;
        this.texte = texte;
        this.image = image;
    }

    public Actualite() {
    }

    public Actualite(String titre, String texte) {
        this.titre = titre;
        this.texte = texte;
    }

    public Actualite(int idactualite, String titre, String texte, String image, User user) {
        this.idactualite = idactualite;
        this.titre = titre;
        this.texte = texte;
        this.image = image;
        this.user = user;
    }

    public Actualite(String titre, String texte, String image, User user) {
        this.titre = titre;
        this.texte = texte;
        this.image = image;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  
     
    
    @Override
    public String toString() {
        return "Actualite{" + "idactualite=" + idactualite + ", titre=" + titre + ", texte=" + texte + ", image=" + image + '}';
    }

}
