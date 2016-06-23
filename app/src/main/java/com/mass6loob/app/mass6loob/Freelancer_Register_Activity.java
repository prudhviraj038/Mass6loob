package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sriven on 6/21/2016.
 */
public class Freelancer_Register_Activity extends Activity {
    EditText name,email,gender,age,nationality,fields,uploadlogo;
    LinearLayout signup;
    String name_str,email_str,gender_str,age_str,nationalitty_str,fields_str,uploadlogo_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_register);
        name = (EditText)findViewById(R.id.free_name);
        email = (EditText)findViewById(R.id.free_email);
        gender  = (EditText)findViewById(R.id.free_gender);
        age  = (EditText)findViewById(R.id.free_age);
        nationality  = (EditText)findViewById(R.id.free_nationality);
        fields  = (EditText)findViewById(R.id.free_fields);
        uploadlogo = (EditText)findViewById(R.id.free_upload);
        signup = (LinearLayout)findViewById(R.id.free_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                gender_str = gender.getText().toString();
                age_str= age.getText().toString();
                nationalitty_str= nationality.getText().toString();
                fields_str= fields.getText().toString();
                uploadlogo_str= uploadlogo.getText().toString();
                if(name_str.equals("")){
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(gender_str.equals("")){
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter gender", Toast.LENGTH_SHORT).show();
                }
                else if(age_str.equals("")){
                    Toast.makeText(Freelancer_Register_Activity.this, "please select ur age", Toast.LENGTH_SHORT).show();
                }
                if(nationalitty_str.equals("")){
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter your nationality", Toast.LENGTH_SHORT).show();
                }
                else if(fields_str.equals("")){
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter the field you worked for", Toast.LENGTH_SHORT).show();
                }
                else if(uploadlogo_str.equals("")){
                    Toast.makeText(Freelancer_Register_Activity.this, "please upload your logo or profilepic", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Freelancer_Register_Activity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
