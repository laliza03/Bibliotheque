package com.LizaBenkadoum_MonessaPierre.bibliotheque.dao;

import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.Book;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.JsonUtil;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.NetworkUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

public class BookDao {

    private static final String BOOKS_ENDPOINT = "/livres";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // Récupère tous les livres
    public List<Book> getAllBooks() {
        try {
            String response = NetworkUtil.get(BOOKS_ENDPOINT);
            return JsonUtil.parseBooks(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupère un livre par son ID
    public Book getBookById(String bookId) {
        List<Book> books = getAllBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    return book;
                }
            }
        }
        return null;
    }

    // Ajoute un livre
    public boolean addBook(Book book) {
        try {
            String json = JsonUtil.toJson(book);
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(NetworkUtil.BASE_URL + BOOKS_ENDPOINT)
                    .post(body)
                    .build();

            try (Response response = NetworkUtil.getClient().newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprime un livre par son ID
    public boolean deleteBook(String bookId) {
        try {
            Request request = new Request.Builder()
                    .url(NetworkUtil.BASE_URL + BOOKS_ENDPOINT + "/" + bookId)
                    .delete()
                    .build();

            try (Response response = NetworkUtil.getClient().newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Met à jour la note d'un livre
    public boolean rateBook(String bookId, float rating) {
        List<Book> books = getAllBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    // Calcul de la nouvelle note moyenne
                    float totalRating = book.getAverageRating() * book.getNumberOfRatings();
                    totalRating += rating;
                    book.setNumberOfRatings(book.getNumberOfRatings() + 1);
                    book.setAverageRating(totalRating / book.getNumberOfRatings());

                    return updateBook(book);
                }
            }
        }
        return false;
    }

    // Met à jour un livre existant
    private boolean updateBook(Book book) {
        try {
            String json = JsonUtil.toJson(book);
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(NetworkUtil.BASE_URL + BOOKS_ENDPOINT + "/" + book.getId())
                    .put(body)
                    .build();

            try (Response response = NetworkUtil.getClient().newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
