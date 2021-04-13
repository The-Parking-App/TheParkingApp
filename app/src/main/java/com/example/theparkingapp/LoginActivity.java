package com.example.theparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";

    private Button btLogin;
    private Button btSignup;

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Persistence with login user
        if (ParseUser.getCurrentUser() != null)
            goMainActivity();

        etEmail =findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btSignup = findViewById(R.id.btRegister);

        // Retrieve the username and password from the edit text fields and pass it to loginUser
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                loginUser(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        // User wishes to register with us --> take them to the Registration Activity
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Registration.class);
                startActivity(i);
                finish();
            }
        });
    }


    /*
     * Name:        loginUser
     * Parameters:  The username of the user (tye string) and the password for the account (type string)
     * Description: Checks the credentials of the user by making a network request to parse server in the background thread. If e is null then success
     * Returns:     void
     */
    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user " + username + " with password as "+ password);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with login\n", e);
                    Toast.makeText(LoginActivity.this, "Unsuccessful Login", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                goMainActivity();
            }
        });
    }


    /*
     * Name:        goMainActivity
     * Parameters:  void
     * Description: Launches the MainActivity
     * Returns:     void
     */
    private void goMainActivity() {
        Intent main = new Intent(this, SelectionActivity.class);
        startActivity(main);
        finish();
    }
}