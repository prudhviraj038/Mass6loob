package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sriven on 6/21/2016.
 */
public class Employee_Register_Activity extends Activity {
    EditText username,jobtitle,experience,nationality,educatuionmasters,educationbachelors,location,gender,uploadcv,uploadimage;
    TextView reviewcv;
    LinearLayout submit;
    String user_str,job_str,exp_str,nationality_str,edumasters_str,edubachelors_str,location_str,gender_str,uploadcv_str,uploadimage_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_register);
        username = (EditText)findViewById(R.id.user_name);
        jobtitle = (EditText)findViewById(R.id.job_title);
        experience = (EditText)findViewById(R.id.emp_experience);
        nationality = (EditText)findViewById(R.id.emp_nationality);
        educationbachelors = (EditText)findViewById(R.id.emp_edu_bachelors);
        educatuionmasters = (EditText)findViewById(R.id.emp_edu_masters);
        location = (EditText)findViewById(R.id.emp_location);
        gender = (EditText)findViewById(R.id.emp_gender);
        uploadcv = (EditText)findViewById(R.id.emp_cv);
        reviewcv = (TextView)findViewById(R.id.emp_review_cv);
        uploadimage = (EditText)findViewById(R.id.emp_img);
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
}
