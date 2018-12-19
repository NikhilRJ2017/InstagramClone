package com.example.nanu.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogIn extends AppCompatActivity {

    EditText userSignUpUsername,userSignUpPassword,userLogInUsername,userLogInPassword;
    Button signUpBtn,logInBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        userSignUpUsername = findViewById(R.id.userSignUpUsername);
        userSignUpPassword = findViewById(R.id.userSignUpPassword);
        userLogInUsername = findViewById(R.id.logInUserName);
        userLogInPassword = findViewById(R.id.logInPassword);

        signUpBtn = findViewById(R.id.signUpBtn);
        logInBtn = findViewById(R.id.logInbtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();

                appUser.setUsername(userSignUpUsername.getText().toString());
                appUser.setPassword(userSignUpPassword.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){
                            FancyToast.makeText(SignUpLogIn.this,appUser.get("username").toString()+" is successfully Signed up",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            Intent welcomePageIntent = new Intent(SignUpLogIn.this,WelcomeActivity.class);
                            startActivity(welcomePageIntent);
                        }else{
                            FancyToast.makeText(SignUpLogIn.this,e.getMessage()+"",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });



            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(userLogInUsername.getText().toString(), userLogInPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){
                            FancyToast.makeText(SignUpLogIn.this,user.get("username").toString()+" is successfully Logged In",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            Intent welcomePageIntent = new Intent(SignUpLogIn.this,WelcomeActivity.class);
                            startActivity(welcomePageIntent);
                        }else {
                            FancyToast.makeText(SignUpLogIn.this,e.getMessage()+"",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });
            }
        });

    }
}
