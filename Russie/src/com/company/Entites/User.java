/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Odil
 */
public class User {
    
    private int id;
    private String username;
    private String username_canonical;
    private String email_canonical;
    private String email;
    private String nom;
    private String prenom;
    private int jeton;
    private String num;
    private String nationalite;
    private int enabled;
    private String code;
    private String image ; 
    private String password;
    private List<String>roles = new ArrayList<String>();

    public User() {
    }

    public User(int id, String username, String username_canonical, String email_canonical, String email, String nom, String prenom, int jeton, String num, String nationalite, int enabled, String password) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.jeton = jeton;
        this.num = num;
        this.nationalite = nationalite;
        this.enabled = enabled;
        this.password = password;
    }

    public User(int id, String username, String email, String nom, String prenom, int jeton, String nationalite, String password,String num,int enabled,String code) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.jeton = jeton;
        this.nationalite = nationalite;
        this.password = password;
        this.num= num;
        this.enabled=enabled;
        this.code=code;
    }

       
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getJeton() {
        return jeton;
    }

    public void setJeton(int jeton) {
        this.jeton = jeton;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email_canonical=" + email_canonical + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", jeton=" + jeton + ", num=" + num + ", nationalite=" + nationalite + ", enabled=" + enabled + ", password=" + password + ", roles=" + roles + '}';
    }
      
   
  
   

 

    
    
}
