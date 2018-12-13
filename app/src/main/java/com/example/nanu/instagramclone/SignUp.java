package com.example.nanu.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button saveBtn;
    private EditText nameEdtTxt,emailEdtTxt,genderEdtTxt,professionEdtTxt;
    private TextView txtGetData;
    private Button getAllDataOnce;
    private String allPersonalDetails;

    private Button getToNextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initialize
        saveBtn = findViewById(R.id.saveBtn);
        nameEdtTxt = findViewById(R.id.nameEdtTxt);
        emailEdtTxt = findViewById(R.id.emailEdtTxt);
        genderEdtTxt = findViewById(R.id.genderEdtTxt);
        professionEdtTxt = findViewById(R.id.professionEdtTxt);
        txtGetData = findViewById(R.id.txtGetData);
        getAllDataOnce = findViewById(R.id.getAllDataOnce);
        getToNextActivity = findViewById(R.id.getToNextActivity);

        saveBtn.setOnClickListener(SignUp.this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> objectParseQuery = ParseQuery.getQuery("PersonalDetails");
                objectParseQuery.getInBackground("y7X99sbQp3", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null){
                            txtGetData.setText(object.get("name").toString());
                        }
                    }
                });
            }
        });

        getAllDataOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allPersonalDetails = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("PersonalDetails");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null){
                            if (objects.size() > 0){

                                for (ParseObject details : objects){
                                    allPersonalDetails = allPersonalDetails + details.get("name") + "\n";
                                }

                                FancyToast.makeText(SignUp.this,allPersonalDetails,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }


                        }
                    }
                });

            }
        });

        getToNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,SignUpLogIn.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {

        final ParseObject personalDetails = new ParseObject("PersonalDetails");

        personalDetails.put("name",nameEdtTxt.getText().toString());
        personalDetails.put("email",emailEdtTxt.getText().toString());
        personalDetails.put("gender",genderEdtTxt.getText().toString());
        personalDetails.put("profession",professionEdtTxt.getText().toString());

        personalDetails.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null){
                    //Toast.makeText(SignUp.this,personalDetails.get("name")+"'s"+" "+"details entered into the server successfully",Toast.LENGTH_SHORT).show();
                    FancyToast.makeText(SignUp.this,personalDetails.get("name")+"'s"+"personal details are saved successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                }
            }
        });
    }
}
