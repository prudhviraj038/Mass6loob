package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sriven on 6/21/2016.
 */
public class Volunteer_Company_Register_Activity extends RootActivity {
    EditText companyname,password,intrestedin,establisheddate,area,companylogo,catagory;
    LinearLayout companysingup;
    String company_str,password_str,intrested_str,establish_str,area_str,logo_str;
    ImageView agriculture;
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
        agriculture = (ImageView)findViewById(R.id.ic_tick1);
        agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
               // else if(logo_str.equals("")){
                  //  Toast.makeText(Volunteer_Company_Register_Activity.this, "please upload company logo", Toast.LENGTH_SHORT).show();
                //}
                else if(company_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter company name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    final int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.ic_logo);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

}
