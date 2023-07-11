package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksByGenreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;

    private DatabaseReference booksRef;

    private String selectedGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_genre);

        selectedGenre = getIntent().getStringExtra("selectedGenre");

        booksRef = FirebaseDatabase.getInstance().getReference("Books");

        recyclerView = findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(bookList);
        recyclerView.setAdapter(bookAdapter);

        loadBooksByGenre();
    }

    private void loadBooksByGenre() {
        Query query = booksRef.orderByChild("genre").equalTo(selectedGenre);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    if (book != null) {
                        bookList.add(book);
                    }
                }
                bookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BooksByGenreActivity.this, "Failed to fetch books", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
