package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class AccountSummaryActivity extends AppCompatActivity {

    private Button buttonDetails;
    private Button buttonChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);

        buttonDetails = findViewById(R.id.buttonDetails);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);

        buttonDetails.setOnClickListener(new View.OnClickListener() {
            final MainActivity mainActivity = new MainActivity();@Override
            public void onClick(View v) {
                String userEmail = mainActivity.getUserEmail();
                openDetailsPage(userEmail);
            }
        });

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangePasswordPage();
            }
        });
    }

    private void openDetailsPage(String email) {
        // Pass the user email to the DetailsActivity
        Intent intent = new Intent(AccountSummaryActivity.this, DetailsActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }


    private void openChangePasswordPage() {
        // Handle Change Password button click
        // Add your logic here
        Toast.makeText(this, "Change Password button clicked", Toast.LENGTH_SHORT).show();
    }
}

