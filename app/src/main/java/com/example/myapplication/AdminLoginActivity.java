package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editTextPassword.getText().toString().trim();
                if (password.equals("11223344")) {
                    // Admin login successful
                    Intent intent = new Intent(AdminLoginActivity.this, AddBookActivity.class);
                    startActivity(intent);
                    // Add your logic here for handling admin access
                    Toast.makeText(AdminLoginActivity.this, "Admin login successful", Toast.LENGTH_SHORT).show();

                } else {
                    // Incorrect password
                    Toast.makeText(AdminLoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
