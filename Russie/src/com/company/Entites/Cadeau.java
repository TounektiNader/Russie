package com.company.Entites;

public class Cadeau {

    private int idCadeau;

    private String categorie;
    private String type;
    private int jeton;
    private String img;

    public Cadeau() {
    }

    public Cadeau(int idCadeau, String type, int jeton, String img) {
        this.idCadeau = idCadeau;
        this.type = type;
        this.jeton = jeton;
        this.img = img;
    }

    public Cadeau(String type, int jeton, String img) {
        this.type = type;
        this.jeton = jeton;
        this.img = img;
    }

    public Cadeau(String categorie, String type, int jeton, String img) {
        this.categorie = categorie;
        this.type = type;
        this.jeton = jeton;
        this.img = img;
    }

    public Cadeau(int idCadeau, String categorie, String type, int jeton, String img) {
        this.idCadeau = idCadeau;
        this.categorie = categorie;
        this.type = type;
        this.jeton = jeton;
        this.img = img;
    }

    
    
    public int getIdCadeau() {
        return idCadeau;
    }

    public void setIdCadeau(int idCadeau) {
        this.idCadeau = idCadeau;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getJeton() {
        return jeton;
    }

    public void setJeton(int jeton) {
        this.jeton = jeton;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    

}
