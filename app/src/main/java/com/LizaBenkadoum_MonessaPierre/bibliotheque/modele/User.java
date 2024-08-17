package com.LizaBenkadoum_MonessaPierre.bibliotheque.modele;

public class User {
    private String compte;
    private String nom;
    private String prenom;
    private String id;
    private String password;

    public User() {}

    public User(String compte, String nom, String prenom, String id, String password) {
        this.compte = compte;
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.password = password;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
