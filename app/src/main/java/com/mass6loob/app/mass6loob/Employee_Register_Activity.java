package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by sriven on 6/21/2016.
 */
public class Employee_Register_Activity extends  RootActivity{
    EditText username,jobtitle,location,uploadcv,uploadimage;
    TextView reviewcv,nationality,experience,gender,educationmasters,educationbachelors;
    LinearLayout submit;
    String user_str,job_str,exp_str,nationality_str,edumasters_str,edubachelors_str,location_str,gender_str,uploadcv_str,uploadimage_str;
    String exp_id = "0";
    String nation_id = "0";
    String edub_id = "0";
    String edum_id = "0";
    ArrayList<String>gen_title;
    ArrayList<String>exps_id;
    ArrayList<String>exps_title;
    ArrayList<String>nations_id;
    ArrayList<String>nations_title;
    ArrayList<String>edubs_id;
    ArrayList<String>edubs_title;
    ArrayList<String>edums_id;
    ArrayList<String>edums_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);

        setContentView(R.layout.employee_register);
        gen_title = new   ArrayList<String>();
        exps_title = new ArrayList<String>();
        nations_title = new ArrayList<String>();
        edubs_title = new ArrayList<String>();
        edums_title = new ArrayList<String>();
        gen_title.add("Male");
        gen_title.add("Female");
        username = (EditText)findViewById(R.id.user_name);
        jobtitle = (EditText)findViewById(R.id.job_title);
        experience = (TextView)findViewById(R.id.emp_experience);
        nationality = (TextView)findViewById(R.id.emp_nationality);
        educationbachelors = (TextView)findViewById(R.id.emp_edu_bachelors);
        educationmasters = (TextView)findViewById(R.id.emp_edu_masters);
        location = (EditText)findViewById(R.id.emp_location);
        gender = (TextView)findViewById(R.id.emp_gender);
        uploadcv = (EditText)findViewById(R.id.emp_cv_l);
        LinearLayout emp_nationality = (LinearLayout)findViewById(R.id.emp_nationality_ll);
        emp_nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Register_Activity.this);
                builder.setTitle("CHOOSE NATIONALITY");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Register_Activity.this, android.R.layout.select_dialog_item, nations_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        nation_id = nations_id.get(which);
                        nationality.setText(nations_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_nationality();

        LinearLayout emp_experience = (LinearLayout)findViewById(R.id.emp_experience_ll);
        emp_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Register_Activity.this);
                builder.setTitle("CHOOSE EXPERIENCE");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Register_Activity.this, android.R.layout.select_dialog_item, exps_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        exp_id = exps_id.get(which);
                        experience.setText(exps_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_experience();

        LinearLayout emp_education_bach = (LinearLayout)findViewById(R.id.emp_bachelors_ll);
        emp_education_bach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Register_Activity.this);
                builder.setTitle("CHOOSE EDUCATION");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Register_Activity.this, android.R.layout.select_dialog_item, edubs_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        edub_id = edubs_id.get(which);
                        educationbachelors.setText(edubs_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_edu_bachelors();

        LinearLayout emp_education_masters = (LinearLayout)findViewById(R.id.emp_masters_ll);
        emp_education_masters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Register_Activity.this);
                builder.setTitle("CHOOSE EDUCATION");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Register_Activity.this, android.R.layout.select_dialog_item, edums_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        edum_id = edums_id.get(which);
                        educationmasters.setText(edums_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_edu_masters();


        LinearLayout emp_gender = (LinearLayout)findViewById(R.id.emp_gender_ll);
        emp_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Register_Activity.this);
                builder.setTitle("CHOOSE GENDER");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Register_Activity.this, android.R.layout.select_dialog_item, gen_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        gender.setText(gen_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });

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
                edumasters_str = educationmasters.getText().toString();
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
    private void get_experience() {
        String url = Settings.SERVERURL + "levels.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("levels");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String exp_num = sub.getString("title");
                        String exp_id = sub.getString("id");
                        exps_id.add(exp_id);
                        exps_title.add(exp_num);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Employee_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
    private void get_nationality() {
        String url = Settings.SERVERURL + "levels.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("levels");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String nation_name = sub.getString("title");
                        String nation_id = sub.getString("id");
                        nations_id.add(nation_id);
                        nations_title.add(nation_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Employee_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
    private void get_edu_bachelors() {
        String url = Settings.SERVERURL + "levels.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("levels");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String edub_title = sub.getString("title");
                        String edub_id = sub.getString("id");
                        edubs_id.add(edub_id);
                        edubs_title.add(edub_title);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Employee_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
    private void get_edu_masters() {
        String url = Settings.SERVERURL + "levels.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("levels");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String edum_title = sub.getString("title");
                        String edum_id = sub.getString("id");
                        edums_id.add(edum_id);
                        edums_title.add(edum_title);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Employee_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
}
