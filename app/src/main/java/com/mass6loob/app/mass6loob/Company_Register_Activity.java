package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;

/**
 * Created by sriven on 6/22/2016.
 */
public class Company_Register_Activity extends  RootActivity {
    EditText uname,password,companyname,area,companylogo,aboutcompany;
    TextView country;
    LinearLayout submit,signup,loginhere;
    LinearLayout country_ll;
    String uname_str,pwd_str,cname_str,country_str,area_str,companylogo_str,about_str;
    String country_id="0";
    ArrayList<String> coun_id;
    ArrayList<String> coun_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);

        setContentView(R.layout.company_register);

        uname = (EditText)findViewById(R.id.company_uname);
        password = (EditText)findViewById(R.id.company_pwd);
        companyname= (EditText)findViewById(R.id.company_name);
        country = (TextView)findViewById(R.id.company_country);
        country_ll =(LinearLayout)findViewById(R.id.company_country_ll);
        area= (EditText)findViewById(R.id.company_area);
        loginhere = (LinearLayout)findViewById(R.id.com_login_ll);
        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Company_Register_Activity.this,Login_Activity.class);
                startActivity(intent);

            }
        });
        signup= (LinearLayout)findViewById(R.id.c_singup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Company_Register_Activity.this,Signup_Screen_Activity.class);
                startActivity(intent);

            }
        });



        coun_title=new ArrayList<String>();
        companylogo= (EditText)findViewById(R.id.upload_logo);
        companylogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        country_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Company_Register_Activity.this);
                builder.setTitle("CHOOSE COUNTRY");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Company_Register_Activity.this, android.R.layout.select_dialog_item, coun_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        country_id = coun_id.get(which);
                        country.setText(coun_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_country();
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
                    Toast.makeText(Company_Register_Activity.this, "please describe about your company", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Company_Register_Activity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    final int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "image selected", Toast.LENGTH_LONG)
                .show();
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
                ImageView imgView = (ImageView) findViewById(R.id.ic_upload_logo);
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
    private void get_country() {
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
                        String country_name = sub.getString("title");
                        String couns_id = sub.getString("id");
                        coun_id.add(couns_id);
                        coun_title.add(country_name);
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
                Toast.makeText(Company_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
}
