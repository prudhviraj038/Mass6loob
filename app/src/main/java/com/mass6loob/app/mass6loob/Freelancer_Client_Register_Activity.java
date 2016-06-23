package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sriven on 6/22/2016.
 */
public class Freelancer_Client_Register_Activity extends Activity {
    EditText name,email,phone;
    LinearLayout signup;
    String name_str,email_str,phn_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_client_register);
        name = (EditText)findViewById(R.id.client_name);
        email = (EditText)findViewById(R.id.client_email);
        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        phone =(EditText)findViewById(R.id.client_phn);
        signup = (LinearLayout) findViewById(R.id.client_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                phn_str = phone.getText().toString();
                if(name_str.equals("")){
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter your name", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(phn_str.equals("")){
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter your phone number", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Freelancer_Client_Register_Activity.this,Freelancer_List_Activity.class);
                startActivity(intent);
            }
        });

    }

}
