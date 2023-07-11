package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NameInputActivity extends AppCompatActivity {

    private EditText editTextBookName;
    private Button buttonSubmit;

    private DatabaseReference booksRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_input);

        editTextBookName = findViewById(R.id.editTextBookName);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        booksRef = FirebaseDatabase.getInstance().getReference("Books");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = editTextBookName.getText().toString().trim();
                searchBookByName(enteredName);
            }
        });
    }

    private void searchBookByName(final String name) {
        Query query = booksRef.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Book book = snapshot.getValue(Book.class);
                        if (book != null) {
                            openBookDetails(book);
                            return;
                        }
                    }
                }
                Toast.makeText(NameInputActivity.this, "Book not found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(NameInputActivity.this, "Failed to fetch book", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openBookDetails(Book book) {
        Intent intent = new Intent(NameInputActivity.this, BookDetailsActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }
}

