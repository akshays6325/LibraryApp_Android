package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GenreSelectionActivity extends AppCompatActivity {

    private ListView listViewGenres;
    private ArrayAdapter<String> adapter;
    private List<String> genres;
    private DatabaseReference booksRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_selection);

        listViewGenres = findViewById(R.id.listViewGenres);
        genres = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genres);
        listViewGenres.setAdapter(adapter);

        booksRef = FirebaseDatabase.getInstance().getReference("Books");
        fetchGenres();

        listViewGenres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = genres.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedGenre", selectedGenre);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void fetchGenres() {
        booksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                genres.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    if (book != null && !genres.contains(book.getGenre())) {
                        genres.add(book.getGenre());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GenreSelectionActivity.this, "Failed to fetch genres", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
