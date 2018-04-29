package com.company.Entites;

public class Stades 
{
    int Id;
    String Nom;
    String Fondation;
    String Capacite;
    String Photo;
    String Equipelocale;
    String Position;
    Villes StadeVille;

    public Stades() {
    }

    public Stades(String Nom, String Fondation, String Capacite, String Photo, String Equipelocale, String Position, Villes StadeVille) {
        this.Nom = Nom;
        this.Fondation = Fondation;
        this.Capacite = Capacite;
        this.Photo = Photo;
        this.Equipelocale = Equipelocale;
        this.Position = Position;
        this.StadeVille = StadeVille;
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getFondation() {
        return Fondation;
    }

    public String getCapacite() {
        return Capacite;
    }

    public String getPhoto() {
        return Photo;
    }

    public String getEquipelocale() {
        return Equipelocale;
    }

    public String getPosition() {
        return Position;
    }

    public Villes getStadeVille() {
        return StadeVille;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setFondation(String Fondation) {
        this.Fondation = Fondation;
    }

    public void setCapacite(String Capacite) {
        this.Capacite = Capacite;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public void setEquipelocale(String Equipelocale) {
        this.Equipelocale = Equipelocale;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setStadeVille(Villes StadeVille) {
        this.StadeVille = StadeVille;
    }

    @Override
    public String toString() {
        return "EntiteStade{" + "Id=" + Id + ", Nom=" + Nom + ", Fondation=" + Fondation + ", Capacite=" + Capacite + ", Photo=" + Photo + ", Equipelocale=" + Equipelocale + ", Position=" + Position + ", StadeVille=" + StadeVille + '}';
    }

}
