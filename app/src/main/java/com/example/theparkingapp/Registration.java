package com.example.theparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    }
}