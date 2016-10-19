package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/22/2016.
 */
public class Company_Register_Activity extends  RootActivity {
    EditText uname,password,companyname,area,companylogo,aboutcompany;
    TextView country,submit_tv;
    LinearLayout submit,signup,loginhere;
    LinearLayout country_ll;
    String uname_str,pwd_str,cname_str,country_str,area_str,companylogo_str,about_str;
    String country_id="0";
    ArrayList<String> coun_id;
    ArrayList<String> coun_title;
    String com_id,tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);

        setContentView(R.layout.company_register);
        tt=getIntent().getStringExtra("user");
     //   uname = (EditText)findViewById(R.id.company_uname);
       // password = (EditText)findViewById(R.id.company_pwd);
        submit_tv = (TextView)findViewById(R.id.submit_tv);
        submit_tv.setText(Settings.getword(this,"submit"));
        companyname= (EditText)findViewById(R.id.company_name);
        country = (TextView)findViewById(R.id.company_country);
        country_ll =(LinearLayout)findViewById(R.id.company_country_ll);
        area= (EditText)findViewById(R.id.company_area);
        aboutcompany= (EditText)findViewById(R.id.about_company);
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

        if(tt.equals("1")){
            try {
                JSONObject jsonObject= new JSONObject(Settings.getSettings_json(Company_Register_Activity.this));
                Log.e("lenth",String.valueOf(jsonObject.getJSONArray("companies").length()));
                if(jsonObject.getJSONArray("companies").length()!=0){
                    submit_tv.setText(Settings.getword(this,"update"));
                    com_id=jsonObject.getJSONArray("companies").getJSONObject(0).getString("id");
                    companyname.setText(jsonObject.getJSONArray("companies").getJSONObject(0).getString("name"));
                    area.setText(jsonObject.getJSONArray("companies").getJSONObject(0).getString("area"));
                    aboutcompany.setText(jsonObject.getJSONArray("companies").getJSONObject(0).getString("description"));
                    Log.e("country", jsonObject.getJSONArray("companies").getJSONObject(0).getString("country"));
                    country.setText(jsonObject.getJSONArray("companies").getJSONObject(0).getString("country"));
//                    Picasso.with(this).load(jsonObject.getJSONArray("companies").getJSONObject(0).getString("image")).into(imgView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        coun_title=new ArrayList<String>();
       // companylogo= (EditText)findViewById(R.id.upload_logo);
        LinearLayout companylogo = (LinearLayout)findViewById(R.id.com_uploadlogo_ll);
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
                        // country_id = coun_id.get(which);
                        country.setText(coun_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_contries();

        submit= (LinearLayout)findViewById(R.id.company_submit_ll);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                uname_str = uname.getText().toString();
  //              pwd_str = password.getText().toString();
                cname_str = companyname.getText().toString();
                country_str = country.getText().toString();
                area_str = area.getText().toString();
           //     companylogo_str = companylogo.getText().toString();
                about_str = aboutcompany.getText().toString();

          //      if(uname_str.equals("")){
            //        Toast.makeText(Company_Register_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
             //   }
              //  else if(pwd_str.equals("")){
                //    Toast.makeText(Company_Register_Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                //}
                 if(cname_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please enter companyname", Toast.LENGTH_SHORT).show();
                }
                else if(country_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please select your country", Toast.LENGTH_SHORT).show();
                }
                else if(area_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please select your area", Toast.LENGTH_SHORT).show();
                }
//                else if(imgDecodableString.equals("")){
//                     Toast.makeText(Company_Register_Activity.this, "please upload your company logo", Toast.LENGTH_SHORT).show();
//                }
                else if(about_str.equals("")){
                    Toast.makeText(Company_Register_Activity.this, "please describe about your company", Toast.LENGTH_SHORT).show();
                }
                else {
                     company_register();
                   // Toast.makeText(Company_Register_Activity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    boolean isimgchoosen = false;
    final int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;
    String encodedString;
    Bitmap bitmap;
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
                isimgchoosen=true;
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
    public void get_contries(){
        String url = "https://restcountries.eu/rest/v1/all";
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                for (int i=0;i<jsonObject.length();i++){
                    try {
                        coun_title.add(jsonObject.getJSONObject(i).getString("name"));
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
                Toast.makeText(Company_Register_Activity.this, "cant_rech_server",Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }
    public  void company_register(){
        final ProgressDialog progressDialog = new ProgressDialog(Company_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-company.php?";

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
                        com_id=jsonObject.getString("company_id");

                        Toast.makeText(Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        //Intent intent= new Intent(Company_Register_Activity.this,Employee_Search_Activity.class);
                        //startActivity(intent);
                        //company_logo();
//                        finish();
                        if(isimgchoosen)
                            encodeImagetoString();
                        else {
                            Intent intent = new Intent(Company_Register_Activity.this, Employee_Search_Activity.class);
                            startActivity(intent);
                         finish();
                        }

                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Company_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Company_Register_Activity.this));
                params.put("name",cname_str);
                params.put("country",country_str);
                params.put("area",area_str);
                params.put("description",about_str);
//                if(tt.equals("1")){
//                    params.put("company_id",com_id);
//                }

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public  void company_logo(){
        final ProgressDialog progressDialog = new ProgressDialog(Company_Register_Activity.this);
        progressDialog.setMessage("please wait.. uploading image");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-company-image.php";

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
                        Toast.makeText(Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Company_Register_Activity.this,Employee_Search_Activity.class);
                        startActivity(intent);

                        finish();

                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Company_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Company_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Company_Register_Activity.this));
                params.put("file",encodedString);
                params.put("company_id",com_id);
                params.put("ext_str", "jpg");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {
            final ProgressDialog progressDialog = new ProgressDialog(Company_Register_Activity.this);

            protected void onPreExecute() {
                progressDialog.setMessage("please wait.. encoding image");
                progressDialog.show();
                progressDialog.setCancelable(false);

            };

            @Override
            protected String doInBackground(Void... params) {
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                encodedString = "";
                bitmap = BitmapFactory.decodeFile(imgDecodableString, options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String
                encodedString = Base64.encodeToString(byte_arr, Base64.NO_WRAP);


                return "";
            }

            @Override
            protected void onPostExecute(String msg) {

                // Put converted Image string into Async Http Post param
                // Trigger Image upload
                if(progressDialog!=null)
                    progressDialog.dismiss();
                company_logo();

            }
        }.execute(null, null, null);
    }
}
