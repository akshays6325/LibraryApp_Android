package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button buttonBrowse;
    private Button buttonSearch;
    private Button buttonAccountSummary;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonBrowse = findViewById(R.id.buttonBrowse);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonAccountSummary = findViewById(R.id.buttonAccountSummary);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BrowseActivity.class);
                startActivity(intent);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        buttonAccountSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AccountSummaryActivity.class);
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Log Out button click
                // Perform log out logic here (e.g., sign out from Firebase)

                // Redirect the user back to the sign-in screen (MainActivity)
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity to prevent going back to it with the back button
            }
        });
    }
}
