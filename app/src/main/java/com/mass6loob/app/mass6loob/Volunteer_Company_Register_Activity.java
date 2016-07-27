package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/21/2016.
 */
public class Volunteer_Company_Register_Activity extends RootActivity {
    EditText companyname,password,intrestedin,establisheddate,area,catagory;
    TextView company_logo;
    LinearLayout companysingup;
    String company_str,password_str,intrested_str,establish_str,area_str,logo_str;
    String vol_id;
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
        company_logo = (TextView)findViewById(R.id.company_logo);
        agriculture = (ImageView)findViewById(R.id.ic_tick1);
        agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

      //  companylogo= (EditText)findViewById(R.id.company_logo);
       // companylogo.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v) {

      //      }
        //});

        companysingup= (LinearLayout)findViewById(R.id.company_signup_ll);
        companysingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                company_str = companyname.getText().toString();
                password_str = password.getText().toString();
                intrested_str = intrestedin.getText().toString();
                establish_str = establisheddate.getText().toString();
                area_str = area.getText().toString();
            //    logo_str = companylogo.getText().toString();
                LinearLayout companylogo = (LinearLayout)findViewById(R.id.vol_com_logo_ll);
                companylogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }
                });
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
               //     else if(logo_str.equals("")){
               //  Toast.makeText(Volunteer_Company_Register_Activity.this, "please upload company logo", Toast.LENGTH_SHORT).show();
               //    }
                else if(company_str.equals("")){
                    Toast.makeText(Volunteer_Company_Register_Activity.this, "please enter company name", Toast.LENGTH_SHORT).show();
                }
                else {
                    volunteer_register();
                   // Toast.makeText(Volunteer_Company_Register_Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
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
    public  void volunteer_register(){
        final ProgressDialog progressDialog = new ProgressDialog(Volunteer_Company_Register_Activity.this);
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
                    Log.e("response", jsonObject.toString());
                    JSONObject jsonObject1=jsonObject.getJSONObject(0);
                    String reply=jsonObject1.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject1.getString("message");
                        vol_id=jsonObject1.getString("employee_id");
                        Toast.makeText(Volunteer_Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Volunteer_Company_Register_Activity.this,Employee_Search_Activity.class);
                        startActivity(intent);
                        emp_image();

//                        finish();

                    }
                    else {
                        String msg=jsonObject1.getString("message");
                        Toast.makeText(Volunteer_Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Volunteer_Company_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Volunteer_Company_Register_Activity.this));
                params.put("title",company_str);
                params.put("experience",intrested_str);
                params.put("nationality",establish_str);
                params.put("bachelors",area_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public  void emp_image(){
        final ProgressDialog progressDialog = new ProgressDialog(Volunteer_Company_Register_Activity.this);
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
                        Toast.makeText(Volunteer_Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
//                        finish();

                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Volunteer_Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Volunteer_Company_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Volunteer_Company_Register_Activity.this));
                params.put("file",imgDecodableString);
                params.put("employee_id",vol_id);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    }
