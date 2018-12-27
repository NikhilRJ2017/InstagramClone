package com.example.nanu.instagramclone;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    EditText logInEmail,logInPassword;
    Button signUpFromLogInBtn,logInFromLogInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Log In");

        logInEmail = findViewById(R.id.logInEmailFromLogIn);
        logInPassword = findViewById(R.id.logInPasswordFromLogIn);
        signUpFromLogInBtn = findViewById(R.id.signUpBtnFromLogIn);
        logInFromLogInBtn = findViewById(R.id.logInBtnFromLogIn);

        signUpFromLogInBtn.setOnClickListener(LogIn.this);
        logInFromLogInBtn.setOnClickListener(LogIn.this);

        //to avoid token issues we logout the logged in user
        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaApp();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signUpBtnFromLogIn:
                Intent getBackToSignUpPage = new Intent(LogIn.this,SignUp.class);
                startActivity(getBackToSignUpPage);
                break;

            case  R.id.logInBtnFromLogIn:
                ParseUser.logInInBackground(logInEmail.getText().toString(),
                        logInPassword.getText().toString(),
                        new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){

                            FancyToast.makeText(LogIn.this,
                                    user.getUsername()+" successfully logged In!",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.SUCCESS,
                                    false).show();

                            transitionToSocialMediaApp();

                        }else {

                            FancyToast.makeText(LogIn.this,e.getMessage()+"",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();

                        }
                    }
                });

                break;
        }
    }

    private void transitionToSocialMediaApp(){

        Intent intent = new Intent(LogIn.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
