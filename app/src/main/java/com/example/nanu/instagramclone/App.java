package com.example.nanu.instagramclone;

import android.app.Application;

import com.parse.Parse;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("eRQ25c7Iu0T8EBcgZ7y2dkL6Oxd4RBMDujg1FiyX")
                // if defined
                .clientKey("hzsCB6oaCy1KZRQhEA30hTxox7skZWfXF5UvBIL2")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
