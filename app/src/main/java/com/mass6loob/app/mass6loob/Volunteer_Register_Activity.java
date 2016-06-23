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
public class Volunteer_Register_Activity extends Activity {
    EditText name,age,email,gender;
    LinearLayout signup;
    String name_str,age_str,email_str,gender_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_register);
        name = (EditText)findViewById(R.id.volunteer_name);
        email = (EditText)findViewById(R.id.volunteer_email);
        gender  = (EditText)findViewById(R.id.volunteer_gender);
        age  = (EditText)findViewById(R.id.volunteer_age);
        signup = (LinearLayout)findViewById(R.id.volunteer_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                gender_str = gender.getText().toString();
                age_str= age.getText().toString();
                if(name_str.equals("")){
                    Toast.makeText(Volunteer_Register_Activity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(Volunteer_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(gender_str.equals("")){
                    Toast.makeText(Volunteer_Register_Activity.this, "please enter gender", Toast.LENGTH_SHORT).show();
                }
                else if(age_str.equals("")){
                    Toast.makeText(Volunteer_Register_Activity.this, "please select ur age", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Volunteer_Register_Activity.this,Volunteer_List_Activity.class);
                startActivity(intent);

            }
        });
    }
}
