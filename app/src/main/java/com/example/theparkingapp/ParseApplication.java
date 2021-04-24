package com.example.theparkingapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


// DO NOT CHANGE THE NAME OF THE KEYS
// SHOULD BE: clientKey and applicationID
public class ParseApplication extends Application {
    public static final String PARSE_APP_ID = BuildConfig.PARSE_APP_ID;       // Change this inside secure.properties
    public static final String PARSE_CLIENT_KEY = BuildConfig.PARSE_CLIENT_KEY; // Change this inside secure.properties
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(ParkingLot.class);
        ParseObject.registerSubclass(ParkingSpace.class);
        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(PARSE_APP_ID)  // stored in values/secrets.xml file
                .clientKey(PARSE_CLIENT_KEY)          // stored in values/secrets.xml file
                .server("https://parseapi.back4app.com").build()
        );
    }
}
