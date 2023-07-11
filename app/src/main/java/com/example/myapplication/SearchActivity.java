package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private Button buttonByASIN;
    private Button buttonByName;

    private static final int REQUEST_ASIN_INPUT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonByASIN = findViewById(R.id.buttonByASIN);
        buttonByName = findViewById(R.id.buttonByName);

        buttonByASIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openASINInput();
            }
        });

        buttonByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle By Name button click
                // Add your logic here
                openNameInput();
            }
        });
    }

    private void openASINInput() {
        Intent intent = new Intent(SearchActivity.this, ASINInputActivity.class);
        startActivityForResult(intent, REQUEST_ASIN_INPUT);
    }
    private void openNameInput() {
        Intent intent = new Intent(SearchActivity.this, NameInputActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_ASIN_INPUT:
                    String asin = data.getStringExtra("asin");
                    // Handle the ASIN value, e.g., perform a search using the ASIN
                    break;
            }
        }
    }
}
