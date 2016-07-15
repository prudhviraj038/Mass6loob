package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sriven on 6/21/2016.
 */
public class Volunteer_Register_Activity extends  RootActivity {
    EditText name,age,email;
    TextView gender;
    LinearLayout signup;
    String name_str,age_str,email_str,gender_str;
    String gender_id="0";
    ArrayList<String> gen_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.volunteer_register);
        gen_title = new   ArrayList<String>();
        gen_title.add("Male");
        gen_title.add("Female");
        name = (EditText)findViewById(R.id.volunteer_name);
        email = (EditText)findViewById(R.id.volunteer_email);
        gender  = (TextView)findViewById(R.id.volunteer_gender);
        age  = (EditText)findViewById(R.id.volunteer_age);
        LinearLayout volunteer_gender = (LinearLayout)findViewById(R.id.volunteer_gender_ll);
        volunteer_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Volunteer_Register_Activity.this);
                builder.setTitle("CHOOSE GENDER");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Volunteer_Register_Activity.this, android.R.layout.select_dialog_item, gen_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        gender.setText(gen_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

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
