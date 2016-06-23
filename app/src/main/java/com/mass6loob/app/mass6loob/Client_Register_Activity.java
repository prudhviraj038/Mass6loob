package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sriven on 6/21/2016.
 */
public class Client_Register_Activity extends Activity {
    EditText client_name,client_email,client_phone;
    LinearLayout client_signup;
    String client_str,clientemail_str,phone_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_client_register);
        client_name = (EditText)findViewById(R.id.client_name);
        client_email = (EditText)findViewById(R.id.client_email);
        client_phone = (EditText)findViewById(R.id.client_phn);
        client_signup = (LinearLayout)findViewById(R.id.client_signup_ll);
        client_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client_str = client_name.getText().toString();
                clientemail_str = client_email.getText().toString();
               phone_str = client_phone.getText().toString();
                if(client_str.equals("")){
                    Toast.makeText(Client_Register_Activity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                else if(clientemail_str.equals("")){
                    Toast.makeText(Client_Register_Activity.this, "please enter valid emailid", Toast.LENGTH_SHORT).show();
                }
                else if(phone_str.equals("")){
                    Toast.makeText(Client_Register_Activity.this, "please enter phonenumber", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Client_Register_Activity.this, "client registered successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
