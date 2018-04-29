package com.company.Entites;

public class Restos 
{
    int Id;
    String Nom;
    String Details;
    String Position;
    String Photo;
    Villes RestoVille;

    public Restos(String Nom, String Details, String Position, String Photo, Villes RestoVille) {
        this.Nom = Nom;
        this.Details = Details;
        this.Position = Position;
        this.Photo = Photo;
        this.RestoVille = RestoVille;
    }

    public Restos() 
    {
        
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getDetails() {
        return Details;
    }

    public String getPosition() {
        return Position;
    }

    public String getPhoto() {
        return Photo;
    }

    public Villes getRestoVille() {
        return RestoVille;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public void setRestoVille(Villes RestoVille) {
        this.RestoVille = RestoVille;
    }

    @Override
    public String toString() {
        return "EntiteResto{" + "Id=" + Id + ", Nom=" + Nom + ", Details=" + Details + ", Position=" + Position + ", Photo=" + Photo + ", RestoVille=" + RestoVille + '}';
    }

}
