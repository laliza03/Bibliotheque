package com.LizaBenkadoum_MonessaPierre.bibliotheque.modele;

public class Book {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String description;
    private String publicationDate;
    private float averageRating;
    private int numberOfRatings;

    // Constructeur
    public Book(String title, String author, String isbn, String publisher, String description, String publicationDate, float averageRating, int numberOfRatings) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.description = description;
        this.publicationDate = publicationDate;
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPublicationDate() { return publicationDate; }
    public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }

    public float getAverageRating() { return averageRating; }
    public void setAverageRating(float averageRating) { this.averageRating = averageRating; }

    public int getNumberOfRatings() { return numberOfRatings; }
    public void setNumberOfRatings(int numberOfRatings) { this.numberOfRatings = numberOfRatings; }
}