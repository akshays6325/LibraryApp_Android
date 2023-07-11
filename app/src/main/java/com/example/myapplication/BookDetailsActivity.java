package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewCategory;
    private TextView textViewAvailability;
    private TextView textViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewAvailability = findViewById(R.id.textViewAvailability);
        textViewId = findViewById(R.id.textViewId);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("book")) {
            Book book = intent.getParcelableExtra("book");
            if (book != null) {
                displayBookDetails(book);
            }
        }
    }

    private void displayBookDetails(Book book) {
        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());
        textViewCategory.setText(book.getCategory());
        textViewAvailability.setText("Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
        textViewId.setText("ID: " + book.getId());
    }
}
