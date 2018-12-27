package com.example.nanu.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    EditText signUpEmail,signUpUsername,signUpPassword;
    Button signUpBtn,logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");
        signUpEmail = findViewById(R.id.signupEmailTextView);
        signUpUsername = findViewById(R.id.signupUsernameTextView);
        signUpPassword = findViewById(R.id.signupPassword);

        signUpBtn = findViewById(R.id.signUpBtn);
        logInBtn = findViewById(R.id.logInBtn);

        signUpBtn.setOnClickListener(SignUp.this);
        logInBtn.setOnClickListener(SignUp.this);

        //to avoid token issues we logout the logged in user
        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaApp();
        }

        signUpPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(signUpBtn );
                }

                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {



        switch (v.getId()){

            case R.id.signUpBtn:

                if (signUpEmail.getText().toString().equals("")
                        || signUpUsername.getText().toString().equals("")
                        || signUpPassword.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this,
                            "Email, Username, Password is required",
                            FancyToast.LENGTH_SHORT,
                            FancyToast.INFO,
                            true).show();
                }else {

                    final ParseUser signUpUser = new ParseUser();

                    signUpUser.setEmail(signUpEmail.getText().toString());
                    signUpUser.setUsername(signUpUsername.getText().toString());
                    signUpUser.setPassword(signUpPassword.getText().toString());

                    final ProgressDialog signUpProgressDialog = new ProgressDialog(this);
                    signUpProgressDialog.setMessage("Signing up " + signUpUsername.getText().toString());
                    signUpProgressDialog.show();

                    signUpUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                FancyToast.makeText(SignUp.this,
                                        signUpUser.getUsername() + " successfully signed up!",
                                        FancyToast.LENGTH_SHORT,
                                        FancyToast.SUCCESS,
                                        false).show();

                                transitionToSocialMediaApp();

                            } else {

                                FancyToast.makeText(SignUp.this,
                                        e.getMessage() + " !",
                                        FancyToast.LENGTH_SHORT,
                                        FancyToast.ERROR,
                                        false).show();

                            }

                            signUpProgressDialog.dismiss();
                        }
                    });
                }
                break;

            case R.id.logInBtn:
                Intent toLogInActivity = new Intent(SignUp.this,LogIn.class);
                startActivity(toLogInActivity);

                break;



        }

    }

    public void rootLayoutTapped(View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void transitionToSocialMediaApp(){

        Intent intent = new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
