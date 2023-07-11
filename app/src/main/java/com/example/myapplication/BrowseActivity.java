package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {

    private Button buttonByCategory;
    private Button buttonByAuthor;
    private Button buttonByGenre;

    private static final int REQUEST_CATEGORY_SELECTION = 1;
    private static final int REQUEST_AUTHOR_SELECTION = 2;
    private static final int REQUEST_GENRE_SELECTION = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        buttonByCategory = findViewById(R.id.buttonByCategory);
        buttonByAuthor = findViewById(R.id.buttonByAuthor);
        buttonByGenre = findViewById(R.id.buttonByGenre);

        buttonByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategorySelection();
            }
        });

        buttonByAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAuthorSelection();
            }
        });

        buttonByGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenreSelection();
            }
        });
    }

    private void openCategorySelection() {
        Intent intent = new Intent(BrowseActivity.this, CategorySelectionActivity.class);
        startActivityForResult(intent, REQUEST_CATEGORY_SELECTION);
    }

    private void openAuthorSelection() {
        Intent intent = new Intent(BrowseActivity.this, AuthorSelectionActivity.class);
        startActivityForResult(intent, REQUEST_AUTHOR_SELECTION);
    }

    private void openGenreSelection() {
        Intent intent = new Intent(BrowseActivity.this, GenreSelectionActivity.class);
        startActivityForResult(intent, REQUEST_GENRE_SELECTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_CATEGORY_SELECTION:
                    String selectedCategory = data.getStringExtra("selectedCategory");
                    Intent intentCategory = new Intent(BrowseActivity.this, BooksByCategoryActivity.class);
                    intentCategory.putExtra("category", selectedCategory);
                    startActivity(intentCategory);
                    break;
                case REQUEST_AUTHOR_SELECTION:
                    ArrayList<String> selectedAuthors = data.getStringArrayListExtra("selectedAuthors");
                    Intent intentAuthor = new Intent(BrowseActivity.this, BooksByAuthorActivity.class);
                    intentAuthor.putStringArrayListExtra("selectedAuthors", selectedAuthors);
                    startActivity(intentAuthor);
                    break;
                case REQUEST_GENRE_SELECTION:
                    String selectedGenre = data.getStringExtra("selectedGenre");
                    Intent intentGenre = new Intent(BrowseActivity.this, BooksByGenreActivity.class);
                    intentGenre.putExtra("selectedGenre", selectedGenre);
                    startActivity(intentGenre);
                    break;
            }
        }
    }
}
