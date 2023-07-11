package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AuthorSelectionActivity extends AppCompatActivity {

    private DatabaseReference booksRef;

    private ListView listViewAuthors;
    private Button buttonSubmit;

    private List<String> authors;
    private List<String> selectedAuthors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_selection);

        booksRef = FirebaseDatabase.getInstance().getReference("Books");

        listViewAuthors = findViewById(R.id.listViewAuthors);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        authors = new ArrayList<>();
        selectedAuthors = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, authors);
        listViewAuthors.setAdapter(adapter);
        listViewAuthors.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listViewAuthors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String author = authors.get(position);
                if (selectedAuthors.contains(author)) {
                    selectedAuthors.remove(author);
                } else {
                    selectedAuthors.add(author);
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("selectedAuthors", (ArrayList<String>) selectedAuthors);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        loadAuthors();
    }

    private void loadAuthors() {
        Query query = booksRef.orderByChild("author");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                authors.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    if (book != null && !authors.contains(book.getAuthor())) {
                        authors.add(book.getAuthor());
                    }
                }
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) listViewAuthors.getAdapter();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
