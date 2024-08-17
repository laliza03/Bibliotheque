package com.LizaBenkadoum_MonessaPierre.bibliotheque.util;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.FavoriteBook;
import java.util.List;
import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bibliotheque.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer les tables SQLite
        String CREATE_FAVORITE_BOOKS_TABLE = "CREATE TABLE favorite_books (id TEXT PRIMARY KEY, title TEXT, author TEXT, user_email TEXT)";
        db.execSQL(CREATE_FAVORITE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Gérer les mises à jour de la base de données
        db.execSQL("DROP TABLE IF EXISTS favorite_books");
        onCreate(db);
    }

    public void addFavoriteBook(FavoriteBook book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", book.getId());
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("user_email", book.getUserEmail());

        db.insert("favorite_books", null, values);
        db.close();
    }

    public List<FavoriteBook> loadFavoriteBooks() {
        List<FavoriteBook> favoriteBooks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM favorite_books", null);

        if (cursor.moveToFirst()) {
            do {
                FavoriteBook book = new FavoriteBook(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                favoriteBooks.add(book);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return favoriteBooks;
    }

    public void removeFavoriteBook(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("favorite_books", "id = ?", new String[]{bookId});
        db.close();
    }
}
