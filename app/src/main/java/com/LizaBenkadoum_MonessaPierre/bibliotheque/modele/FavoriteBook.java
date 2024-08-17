package com.LizaBenkadoum_MonessaPierre.bibliotheque.modele;

public class FavoriteBook {
    private String id;
    private String title;
    private String author;
    private String userEmail;

    // Constructeur par défaut
    public FavoriteBook() {}

    public FavoriteBook(String id, String title, String author, String userEmail) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.userEmail = userEmail;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
