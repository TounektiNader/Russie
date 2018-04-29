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
public class Stade {
    private int idStade ; 
    private String nomStade ; 
  
    public Stade (){}
    public Stade (int idStade ){this.idStade=idStade;}
    public Stade (String nomStade ){this.nomStade=nomStade;}

    public Stade(int idStade, String nomStade) {
        this.idStade = idStade;
        this.nomStade = nomStade;
    }
    

    public int getIdStade() {
        return idStade;
    }

    public void setIdStade(int idStade) {
        this.idStade = idStade;
    }

    public String getNomStade() {
        return nomStade;
    }

    public void setNomStade(String nomStade) {
        this.nomStade = nomStade;
    }
    
}
