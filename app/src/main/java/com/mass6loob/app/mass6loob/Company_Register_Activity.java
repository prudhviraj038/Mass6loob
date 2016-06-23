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
public class Company_Register_Activity extends Activity {
    EditText uname,password,companyname,country,area,companylogo,aboutcompany;
    LinearLayout submit,signup;
    String uname_str,pwd_str,cname_str,country_str,area_str,companylogo_str,about_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_register);
        uname = (EditText)findViewById(R.id.company_uname);
        password = (EditText)findViewById(R.id.company_pwd);
        companyname= (EditText)findViewById(R.id.company_name);
        country = (EditText)findViewById(R.id.company_country);
        area= (EditText)findViewById(R.id.company_area);
        companylogo= (EditText)findViewById(R.id.upload_logo);
        aboutcompany= (EditText)findViewById(R.id.about_company);
        submit= (LinearLayout)findViewById(R.id.company_submit_ll);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname_str = uname.getText().toString();
                pwd_str = password.getText().toString();
                cname_str = companyname.getText().toString();
                country_str = country.getText().toString();
                area_str = area.getText().toString();
                companylogo_str = companylogo.getText().toString();
                about_str = aboutcompany.getText().toString();
                if(uname_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(pwd_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(cname_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please enter companyname", Toast.LENGTH_SHORT).show();
                }
                else if(country_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please select your country", Toast.LENGTH_SHORT).show();
                }
                else if(area_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please select your area", Toast.LENGTH_SHORT).show();
                }
                else if(companylogo_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please upload your company logo", Toast.LENGTH_SHORT).show();
                }
                else if(about_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please describe your company", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Company_Register_Activity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                }

                signup= (LinearLayout)findViewById(R.id.c_singup_ll);
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Company_Register_Activity.this,Employee_Search_Activity.class);
                        startActivity(intent);

                    }
                });

            }
        });

    }
}
