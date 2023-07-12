package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ASINInputActivity extends AppCompatActivity {

    private EditText editTextASIN;
    private Button buttonSubmit;
    private TextView textViewMessage;

    private DatabaseReference booksRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asin_input);

        editTextASIN = findViewById(R.id.editTextASIN);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewMessage = findViewById(R.id.textViewMessage);

        booksRef = FirebaseDatabase.getInstance().getReference("Books");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredASIN = editTextASIN.getText().toString().trim();
                searchBookByASIN(enteredASIN);
            }
        });
    }

    private void searchBookByASIN(final String asin) {
        Query query = booksRef.orderByChild("id").equalTo(asin);
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
                // No book found
                textViewMessage.setVisibility(View.VISIBLE);
                textViewMessage.setText("Oops, no book found with that ASIN at our library");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ASINInputActivity.this, "Failed to fetch book", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openBookDetails(Book book) {
        Intent intent = new Intent(ASINInputActivity.this, BookDetailsActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }
}
