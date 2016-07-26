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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/21/2016.
 */
public class Employee_Register_Activity extends  RootActivity{
    EditText username,jobtitle,location;
    TextView reviewcv,nationality,experience,gender,educationmasters,educationbachelors,uploadimage,uploadcv;
    LinearLayout submit;
    String user_str,job_str,exp_str,nationality_str,edumasters_str,edubachelors_str,location_str,gender_str,uploadcv_str,uploadimage_str;
    String exp_id = "0";
    String nation_id = "0";
    String edub_id = "0";
    String edum_id = "0";
    String emp_id;
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
        gen_title = new ArrayList<String>();
        exps_id = new ArrayList<String>();
        exps_title = new ArrayList<String>();
        nations_id = new ArrayList<String>();
        nations_title = new ArrayList<String>();
        edubs_id = new ArrayList<String>();
        edubs_title = new ArrayList<String>();
        edums_id = new ArrayList<String>();
        edums_title = new ArrayList<String>();
        gen_title.add("Male");
        gen_title.add("Female");
        username = (EditText) findViewById(R.id.user_name);
        jobtitle = (EditText) findViewById(R.id.job_title);
        experience = (TextView) findViewById(R.id.emp_experience);
        nationality = (TextView) findViewById(R.id.emp_nationality);
        educationbachelors = (TextView) findViewById(R.id.emp_edu_bachelors);
        educationmasters = (TextView) findViewById(R.id.emp_edu_masters);
        location = (EditText) findViewById(R.id.emp_location);
        gender = (TextView) findViewById(R.id.emp_gender);
        uploadcv = (TextView) findViewById(R.id.emp_cv_l);
        LinearLayout emp_nationality = (LinearLayout) findViewById(R.id.emp_nationality_ll);
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
                        //nation_id = nations_id.get(which);
                        nationality.setText(nations_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_nationality();

        LinearLayout emp_experience = (LinearLayout) findViewById(R.id.emp_experience_ll);
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

        LinearLayout emp_education_bach = (LinearLayout) findViewById(R.id.emp_bachelors_ll);
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

        LinearLayout emp_education_masters = (LinearLayout) findViewById(R.id.emp_masters_ll);
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


        LinearLayout emp_gender = (LinearLayout) findViewById(R.id.emp_gender_ll);
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
        LinearLayout  uploadcv = (LinearLayout)findViewById(R.id.emp_uploadcv_ll);
        uploadcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        reviewcv = (TextView) findViewById(R.id.emp_review_cv);
     //   uploadimage = (TextView) findViewById(R.id.emp_img_l);
        LinearLayout uploadimage = (LinearLayout)findViewById(R.id.emp_uploadimg_ll);
        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        submit = (LinearLayout) findViewById(R.id.emp_submit_ll);
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
            //    uploadcv_str = uploadcv.getText().toString();
          //      uploadimage_str = uploadimage.getText().toString();
                if (user_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                } else if (job_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please enter jobtitle", Toast.LENGTH_SHORT).show();
                } else if (exp_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please enter experience", Toast.LENGTH_SHORT).show();
                } else if (nationality_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please select ur nationality", Toast.LENGTH_SHORT).show();
                } else if (edubachelors_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please select ur bachelors degree", Toast.LENGTH_SHORT).show();
                } else if (edumasters_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please select ur masters degree", Toast.LENGTH_SHORT).show();
                } else if (location_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please select ur location", Toast.LENGTH_SHORT).show();
                } else if (gender_str.equals("")) {
                    Toast.makeText(Employee_Register_Activity.this, "please select ur gender", Toast.LENGTH_SHORT).show();
             //   } else if (uploadcv_str.equals("")) {
               //     Toast.makeText(Employee_Register_Activity.this, "please upload your cv", Toast.LENGTH_SHORT).show();
              //  } else if (uploadimage_str.equals("")) {
                //    Toast.makeText(Employee_Register_Activity.this, "please upload ur image", Toast.LENGTH_SHORT).show();
                }
                else {
                    employee_register();
                    // Toast.makeText(Company_Register_Activity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
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
        String url = Settings.SERVERURL + "experiences.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        String exp_name = sub.getString("title"+Settings.get_lan(Employee_Register_Activity.this));
                        String exp_id = sub.getString("id");
                        exps_id.add(exp_id);
                        exps_title.add(exp_name);
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
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    private void get_nationality() {
        String url = "https://restcountries.eu/rest/v1/all";
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                for (int i=0;i<jsonObject.length();i++){
                    try {
                        nations_title.add(jsonObject.getJSONObject(i).getString("demonym"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Employee_Register_Activity.this, "cant_rech_server",Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }

    private void get_edu_bachelors() {
        String url = Settings.SERVERURL + "degrees.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        String exp_title = sub.getString("title" + Settings.get_lan(Employee_Register_Activity.this));
                        String exp_id = sub.getString("id");
                        edubs_id.add(exp_id);
                        edubs_title.add(exp_title);
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
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    private void get_edu_masters() {
        String url = Settings.SERVERURL + "degrees.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        String exp_title = sub.getString("title"+Settings.get_lan(Employee_Register_Activity.this));
                        String exp_id = sub.getString("id");
                        edums_id.add(exp_id);
                        edums_title.add(exp_title);
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
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    public  void employee_register(){
        final ProgressDialog progressDialog = new ProgressDialog(Employee_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-employee.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                try {
                    JSONArray jsonObject=new JSONArray(response);
                    Log.e("response",jsonObject.toString());
                    JSONObject jsonObject1=jsonObject.getJSONObject(0);
                    String reply=jsonObject1.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject1.getString("message");
                        emp_id=jsonObject1.getString("employee_id");
                        Toast.makeText(Employee_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Employee_Register_Activity.this,Employee_Search_Activity.class);
                        startActivity(intent);
                      emp_image();
                        emp_cv();
//                        finish();

                    }
                    else {
                        String msg=jsonObject1.getString("message");
                        Toast.makeText(Employee_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        Toast.makeText(Employee_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Employee_Register_Activity.this));
                params.put("title",job_str);
                params.put("experience",exp_id);
                params.put("nationality",nationality_str);
                params.put("bachelors",edub_id);
                params.put("masters",edum_id);
                params.put("gender",gender_str);
                params.put("location",location_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public  void emp_image(){
        final ProgressDialog progressDialog = new ProgressDialog(Employee_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-employee-image.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Log.e("response",jsonObject.toString());
                    String reply=jsonObject.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(Employee_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
//                        finish();

                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Employee_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        Toast.makeText(Employee_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Employee_Register_Activity.this));
                params.put("file",imgDecodableString);
                params.put("employee_id",emp_id);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public  void emp_cv(){
        final ProgressDialog progressDialog = new ProgressDialog(Employee_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-employee-cv.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Log.e("response",jsonObject.toString());
                    String reply=jsonObject.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(Employee_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
//                        finish();

                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Employee_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        Toast.makeText(Employee_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Employee_Register_Activity.this));
                params.put("resume",uploadcv_str);
                params.put("employee_id",emp_id);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
