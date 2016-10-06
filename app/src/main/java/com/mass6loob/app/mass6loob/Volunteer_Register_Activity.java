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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/21/2016.
 */
public class Volunteer_Register_Activity extends  RootActivity {
    EditText name,age,email;
    TextView gender;
    LinearLayout signup;
    String name_str,age_str,email_str,gender_str;
    String gender_id="0";
    String vol_id;
    ArrayList<String> gen_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.volunteer_register);
        gen_title = new   ArrayList<String>();
        gen_title.add("Male");
        gen_title.add("Female");
        name = (EditText)findViewById(R.id.volunteer_name);
        email = (EditText)findViewById(R.id.volunteer_email);
        gender  = (TextView)findViewById(R.id.volunteer_gender);
        age  = (EditText)findViewById(R.id.volunteer_age);
        LinearLayout volunteer_gender = (LinearLayout)findViewById(R.id.volunteer_gender_ll);
        volunteer_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Volunteer_Register_Activity.this);
                builder.setTitle("CHOOSE GENDER");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Volunteer_Register_Activity.this, android.R.layout.select_dialog_item, gen_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        gender.setText(gen_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        signup = (LinearLayout)findViewById(R.id.volunteer_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                gender_str = gender.getText().toString();
                age_str= age.getText().toString();
                if(name_str.equals("")){
                    Toast.makeText(Volunteer_Register_Activity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(Volunteer_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(gender_str.equals("")){
                    Toast.makeText(Volunteer_Register_Activity.this, "please enter gender", Toast.LENGTH_SHORT).show();
                }
                else if(age_str.equals("")){
                    Toast.makeText(Volunteer_Register_Activity.this, "please select ur age", Toast.LENGTH_SHORT).show();
                }
                else {
                    volunteer_register();
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
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

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
    public  void volunteer_register(){
        final ProgressDialog progressDialog = new ProgressDialog(Volunteer_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-volunteer.php?";

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
                        vol_id=jsonObject1.getString("member_id");
                        Toast.makeText(Volunteer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Volunteer_Register_Activity.this,Volunteer_List_Activity.class);
                        startActivity(intent);
                        //emp_image();

//                        finish();

                    }
                    else {
                        String msg=jsonObject1.getString("message");
                        Toast.makeText(Volunteer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Volunteer_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Volunteer_Register_Activity.this));
                params.put("name",name_str);
                params.put("age",age_str);
                params.put("email",email_str);
                params.put("gender",gender_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
