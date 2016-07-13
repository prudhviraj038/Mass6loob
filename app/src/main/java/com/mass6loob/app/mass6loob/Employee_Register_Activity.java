package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

/**
 * Created by sriven on 6/21/2016.
 */
public class Employee_Register_Activity extends  RootActivity{
    EditText username,jobtitle,experience,nationality,educatuionmasters,educationbachelors,location,gender,uploadcv,uploadimage;
    TextView reviewcv;
    LinearLayout submit;
    String user_str,job_str,exp_str,nationality_str,edumasters_str,edubachelors_str,location_str,gender_str,uploadcv_str,uploadimage_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);

        setContentView(R.layout.employee_register);

        username = (EditText)findViewById(R.id.user_name);
        jobtitle = (EditText)findViewById(R.id.job_title);
        experience = (EditText)findViewById(R.id.emp_experience);
        nationality = (EditText)findViewById(R.id.emp_nationality);
        educationbachelors = (EditText)findViewById(R.id.emp_edu_bachelors);
        educatuionmasters = (EditText)findViewById(R.id.emp_edu_masters);
        location = (EditText)findViewById(R.id.emp_location);
        gender = (EditText)findViewById(R.id.emp_gender);
        uploadcv = (EditText)findViewById(R.id.emp_cv_l);
        uploadcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        reviewcv = (TextView)findViewById(R.id.emp_review_cv);
        uploadimage = (EditText)findViewById(R.id.emp_img_l);
        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        submit = (LinearLayout)findViewById(R.id.emp_submit_ll);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_str = username.getText().toString();
                job_str = jobtitle.getText().toString();
                exp_str = experience.getText().toString();
                nationality_str = nationality.getText().toString();
                edumasters_str = educatuionmasters.getText().toString();
                edubachelors_str = educationbachelors.getText().toString();
                location_str = location.getText().toString();
                gender_str = gender.getText().toString();
                uploadcv_str = uploadcv.getText().toString();
                uploadimage_str = uploadimage.getText().toString();
                if(user_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(job_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please enter jobtitle", Toast.LENGTH_SHORT).show();
                }
                else if(exp_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please enter experience", Toast.LENGTH_SHORT).show();
                }
                else if(nationality_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please select ur nationality", Toast.LENGTH_SHORT).show();
                }
                else if(edubachelors_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please select ur bachelors degree", Toast.LENGTH_SHORT).show();
                }
                else if(edumasters_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please select ur masters degree", Toast.LENGTH_SHORT).show();
                }
                else if(location_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please select ur location", Toast.LENGTH_SHORT).show();
                }
                else if(gender_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please select ur gender", Toast.LENGTH_SHORT).show();
                }
                else if(uploadcv_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please upload your cv", Toast.LENGTH_SHORT).show();
                }
                else if(uploadimage_str.equals("")){
                    Toast.makeText(Employee_Register_Activity.this, "please upload ur image", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Employee_Register_Activity.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
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
                ImageView imgView = (ImageView) findViewById(R.id.emp_img);
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
