package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextAuthor, editTextCategory, editTextGenre;
    private RadioGroup radioGroupAvailable;
    private Button buttonSubmit;

    private DatabaseReference booksRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Initialize the Firebase Realtime Database reference
        booksRef = FirebaseDatabase.getInstance().getReference("Books");

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextGenre = findViewById(R.id.editTextGenre);
        radioGroupAvailable = findViewById(R.id.radioGroupAvailable);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered values
                String id = editTextId.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String author = editTextAuthor.getText().toString().trim();
                String category = editTextCategory.getText().toString().trim();
                String genre = editTextGenre.getText().toString().trim();
                boolean available = ((RadioButton) findViewById(radioGroupAvailable.getCheckedRadioButtonId())).getText().toString().equals("Yes");

                // Check if any field is empty
                if (id.isEmpty() || name.isEmpty() || author.isEmpty() || category.isEmpty() || genre.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a Book object with the entered details
                    Book book = new Book(id, name, author, category, genre, available);

                    // Save the book details to the Firebase Realtime Database
                    booksRef.child(id).setValue(book);

                    Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the current activity and return to the previous screen
                }
            }
        });
    }
}
