package com.LizaBenkadoum_MonessaPierre.bibliotheque.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.LizaBenkadoum_MonessaPierre.bibliotheque.R;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.Book;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.JsonUtil;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BookListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.book_list);
        books = new ArrayList<>();

        loadBooks();

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Book selectedBook = books.get(position);
        Toast.makeText(BookListActivity.this, selectedBook.getTitle(), Toast.LENGTH_SHORT).show();

        // You could add code here to navigate to a book details activity
        Intent intent = new Intent(BookListActivity.this, BookDetailsActivity.class);
        intent.putExtra("book_id", selectedBook.getId());
        startActivity(intent);
    }

    private void loadBooks() {
        new Thread(() -> {
            try {
                String response = NetworkUtil.get("livres");
                books = JsonUtil.parseBooks(response);

                runOnUiThread(() -> {
                    List<String> bookTitles = new ArrayList<>();
                    for (Book book : books) {
                        bookTitles.add(book.getTitle());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(BookListActivity.this,
                            android.R.layout.simple_list_item_1, bookTitles);
                    listView.setAdapter(adapter);
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(BookListActivity.this, "Failed to load books", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }


}