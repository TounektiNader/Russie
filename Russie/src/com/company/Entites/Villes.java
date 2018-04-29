package com.company.Entites;

public class Villes
{
    int Idville;
    String Nom;
    String Fondation;
    String Population;
    String Photoville;
    String Equipelocale;
    String Logoville;
    String Logoequipe;
    String Coordonnees;
    String Timezone;

    public Villes() 
    {
        
    }

    
    public Villes(String Nom, String Fondation, String Population, String Photoville, String Equipelocale, String Logoville, String Logoequipe, String Coordonnees, String Timezone) {
        this.Nom = Nom;
        this.Fondation = Fondation;
        this.Population = Population;
        this.Photoville = Photoville;
        this.Equipelocale = Equipelocale;
        this.Logoville = Logoville;
        this.Logoequipe = Logoequipe;
        this.Coordonnees = Coordonnees;
        this.Timezone = Timezone;
    }

    public int getId() 
    {
        return Idville;
    }

    public String getNom() 
    {
        return Nom;
    }

    public String getFondation() 
    {
        return Fondation;
    }

    public String getPopulation() 
    {
        return Population;
    }

    public String getPhotoville() 
    {
        return Photoville;
    }

    public String getEquipelocale() 
    {
        return Equipelocale;
    }

    public String getLogoville() 
    {
        return Logoville;
    }

    public String getLogoequipe() 
    {
        return Logoequipe;
    }

    public String getCoordonnees() 
    {
        return Coordonnees;
    }

    public String getTimezone() 
    {
        return Timezone;
    }

    public void setIdville(int Idville) {
        this.Idville = Idville;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setFondation(String Fondation) {
        this.Fondation = Fondation;
    }

    public void setPopulation(String Population) {
        this.Population = Population;
    }

    public void setPhotoville(String Photoville) {
        this.Photoville = Photoville;
    }

    public void setEquipelocale(String Equipelocale) {
        this.Equipelocale = Equipelocale;
    }

    public void setLogoville(String Logoville) {
        this.Logoville = Logoville;
    }

    public void setLogoequipe(String Logoequipe) {
        this.Logoequipe = Logoequipe;
    }

    public void setCoordonnees(String Coordonnees) {
        this.Coordonnees = Coordonnees;
    }

    public void setTimezone(String Timezone) {
        this.Timezone = Timezone;
    }

    @Override
    public String toString() {
        return "Ville{" + "Idville=" + Idville + ", Nom=" + Nom + ", Fondation=" + Fondation + ", Population=" + Population + ", Photoville=" + Photoville + ", Equipelocale=" + Equipelocale + ", Logoville=" + Logoville + ", Logoequipe=" + Logoequipe + ", Coordonnees=" + Coordonnees + ", Timezone=" + Timezone + '}';
    }

}