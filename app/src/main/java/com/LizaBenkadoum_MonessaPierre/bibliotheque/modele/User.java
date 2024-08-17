package com.LizaBenkadoum_MonessaPierre.bibliotheque.modele;

public class User {
    private String compte;
    private String nom;
    private String prenom;
    private String id;
    private String password = "etsMtl"; // Mot de passe par défaut

    // Constructeur par défaut
    public User() {}

    // Constructeur complet
    public User(String compte, String nom, String prenom, String id) {
        this.compte = compte;
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.password = "etsMtl";
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
