package com.example.theparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Registration extends AppCompatActivity {
    private Button btLogin;
    private Button btSignup;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etEmail =findViewById(R.id.etEmail);
        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btSignup = findViewById(R.id.btRegister);

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name     = etName.getText().toString();
                String pass     = etPassword.getText().toString();
                String email    = etEmail.getText().toString();

                registerUser(name, pass, email);
            }
        });

    }


    /*
     * Name:        registerUser
     * Parameters:  The name of the user, password, and email all of type string
     * Description: Registers the user to the database
     * Returns:     void
     */
    private void registerUser(String name, String password, String email){
        // Create the ParseUser
        ParseUser newUser = new ParseUser();

        // Set the core properties
        newUser.setUsername(name);
        newUser.setPassword(password);
        newUser.setEmail(email);

        // Invoke sign-up in the Background
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(Registration.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                    Intent selection = new Intent(Registration.this, SelectionActivity.class);
                    startActivity(selection);
                    finish();
                }else{
                    Toast.makeText(Registration.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}