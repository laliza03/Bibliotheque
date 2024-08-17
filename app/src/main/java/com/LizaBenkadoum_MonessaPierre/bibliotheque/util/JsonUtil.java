package com.LizaBenkadoum_MonessaPierre.bibliotheque.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.Book;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.User;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();


    public static List<Book> loadBooks(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Book loadBookById(String filePath, String bookId) {
        List<Book> books = loadBooks(filePath);
        if (books != null) {
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    return book;
                }
            }
        }
        return null;
    }

    public static void rateBook(String filePath, String bookId, float rating) {
        List<Book> books = loadBooks(filePath);
        if (books != null) {
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    // Mise à jour de l'évaluation moyenne
                    float totalRating = book.getAverageRating() * book.getNumberOfRatings();
                    totalRating += rating;
                    book.setNumberOfRatings(book.getNumberOfRatings() + 1);
                    book.setAverageRating(totalRating / book.getNumberOfRatings());
                    break;
                }
            }
            saveBooks(filePath, books);
        }
    }

    public static void addBook(String filePath, Book book) {
        List<Book> books = loadBooks(filePath);
        if (books != null) {
            books.add(book);
            saveBooks(filePath, books);
        }
    }

    public static void removeBook(String filePath, String bookId) {
        List<Book> books = loadBooks(filePath);
        if (books != null) {
            books = books.stream()
                    .filter(book -> !book.getId().equals(bookId))
                    .collect(Collectors.toList());
            saveBooks(filePath, books);
        }
    }

    public static boolean validateUser(String filePath, String email, String password) {
        List<User> users = loadUsers(filePath);
        if (users != null) {
            for (User user : users) {
                if (user.getCompte().equals(email) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<User> loadUsers(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void saveBooks(String filePath, List<Book> books) {
        try {
            objectMapper.writeValue(new File(filePath), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
