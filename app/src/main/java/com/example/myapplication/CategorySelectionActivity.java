package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class CategorySelectionActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickListener {

    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;

    private List<String> categories = Arrays.asList("Documentary", "Novel", "Sci-Fi", "Fantasy", "Mystery");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(categories, this);
        recyclerViewCategories.setAdapter(categoryAdapter);
    }

    @Override
    public void onCategoryClick(String category) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedCategory", category);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
