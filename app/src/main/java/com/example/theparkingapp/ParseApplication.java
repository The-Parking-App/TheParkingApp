package com.example.theparkingapp;

import android.app.Application;

import com.parse.Parse;


// DO NOT CHANGE THE NAME OF THE KEYS
// SHOULD BE: clientKey and applicationID
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.applicationID))  // stored in values/secrets.xml file
                .clientKey(getString(R.string.clientKey))          // stored in values/secrets.xml file
                .server("https://parseapi.back4app.com").build()
        );
    }
}
