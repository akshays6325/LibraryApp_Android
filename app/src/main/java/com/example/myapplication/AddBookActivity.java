package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextAuthor;
    private Spinner spinnerCategory, spinnerGenre;
    private RadioGroup radioGroupAvailable;
    private Button buttonSubmit;
    private Button buttonLogout;

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
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerGenre = findViewById(R.id.spinnerGenre);
        radioGroupAvailable = findViewById(R.id.radioGroupAvailable);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonLogout = findViewById(R.id.buttonLogout);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genres_array, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(genreAdapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered values
                String id = editTextId.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String author = editTextAuthor.getText().toString().trim();
                String category = spinnerCategory.getSelectedItem().toString().trim();
                String genre = spinnerGenre.getSelectedItem().toString().trim();
                boolean available = ((RadioButton) findViewById(radioGroupAvailable.getCheckedRadioButtonId())).getText().toString().equals("Yes");

                // Check if any field is empty
                if (id.isEmpty() || name.isEmpty() || author.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a Book object with the entered details
                    Book book = new Book(id, name, author, category, genre, available);

                    // Save the book details to the Firebase Realtime Database
                    booksRef.child(id).setValue(book);

                    Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();

                    // Reset the input fields
                    editTextId.setText("");
                    editTextName.setText("");
                    editTextAuthor.setText("");
                    spinnerCategory.setSelection(0);
                    spinnerGenre.setSelection(0);
                    radioGroupAvailable.check(R.id.radioButtonYes); // Assuming "Yes" is the default selection

                    // Alternatively, you can recreate the activity to reset all fields
                    // recreate();
                }
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Log Out button click
                // Perform log out logic here (e.g., clear admin session)

                // Redirect the user back to the admin login screen (AdminLoginActivity)
                Intent intent = new Intent(AddBookActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                finish(); // Close the current activity to prevent going back to it with the back button
            }
        });


    }
}
