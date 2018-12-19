package com.example.nanu.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcomeTxtView;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeTxtView = findViewById(R.id.welcomeText);
        logoutBtn = findViewById(R.id.logoutBtn);


        welcomeTxtView.setText("Welcome to Home page " + ParseUser.getCurrentUser().getUsername());
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(WelcomeActivity.this," Logged out successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            finish();

                        }
                    }
                });
            }
        });
    }
}
