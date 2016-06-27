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
public class Volunteer_Company_Register_Activity extends Activity {
    EditText companyname,password,intrestedin,establisheddate,area,companylogo;
    LinearLayout companysingup;
    String company_str,password_str,intrested_str,establish_str,area_str,logo_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);

        setContentView(R.layout.volunteer_company_register);
        final int RESULT_LOAD_IMAGE = 1;
        companyname = (EditText)findViewById(R.id.company_name);
        password = (EditText)findViewById(R.id.company_pwd);
        intrestedin = (EditText)findViewById(R.id.intrested_in);
        establisheddate= (EditText)findViewById(R.id.established_date);
        area= (EditText)findViewById(R.id.company_area);
        companylogo= (EditText)findViewById(R.id.company_logo);
        companylogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        companysingup= (LinearLayout)findViewById(R.id.company_signup_ll);
        companysingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                company_str = companyname.getText().toString();
                password_str = password.getText().toString();
                intrested_str = intrestedin.getText().toString();
                establish_str = establisheddate.getText().toString();
                area_str = area.getText().toString();
                logo_str = companylogo.getText().toString();
                if(company_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter company name", Toast.LENGTH_SHORT).show();
                }
               else if(password_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(intrested_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter your intrest", Toast.LENGTH_SHORT).show();
                }
                else if(establish_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter established date", Toast.LENGTH_SHORT).show();
                }
                else if(area_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter company area", Toast.LENGTH_SHORT).show();
                }
                else if(logo_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please upload company logo", Toast.LENGTH_SHORT).show();
                }
                else if(company_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter company name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
