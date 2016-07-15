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
 * Created by sriven on 6/21/2016.
 */
public class Freelancer_Register_Activity extends RootActivity {
    EditText name,email,age,fields,uploadlogo;
    TextView nationality,gender;
    ImageView wimage1,wimage2,wimage3,wimage4,wimage5,wimage6,wimage7,wimage8,wimage9,wimage10;
    ImageView temp;
    LinearLayout signup;
    String name_str,email_str,gender_str,age_str,nationalitty_str,fields_str,uploadlogo_str;
    String nation_id="0";
    ArrayList<String> gen_title;
    ArrayList<String>nations_id;
    ArrayList<String>nations_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.freelancer_register);
        gen_title = new   ArrayList<String>();
        nations_title = new ArrayList<String>();
        gen_title.add("Male");
        gen_title.add("Female");
        name = (EditText)findViewById(R.id.free_name);
        email = (EditText)findViewById(R.id.free_email);
        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        gender  = (TextView)findViewById(R.id.free_gender);
        age  = (EditText)findViewById(R.id.free_age);
        nationality  = (TextView)findViewById(R.id.free_nationality);
        fields  = (EditText)findViewById(R.id.free_fields);
        uploadlogo = (EditText)findViewById(R.id.free_upload);
        wimage1 = (ImageView)findViewById(R.id.image1_ll);
        wimage2 = (ImageView)findViewById(R.id.image2_ll);
        wimage3= (ImageView)findViewById(R.id.image3_ll);
        wimage4= (ImageView)findViewById(R.id.image4_ll);
        wimage5= (ImageView)findViewById(R.id.image5_ll);
        wimage6= (ImageView)findViewById(R.id.image6_ll);
        wimage7= (ImageView)findViewById(R.id.image7_ll);
        wimage8= (ImageView)findViewById(R.id.image8_ll);
        wimage9= (ImageView)findViewById(R.id.image9_ll);
        wimage10= (ImageView)findViewById(R.id.image10_ll);
        LinearLayout free_gender = (LinearLayout)findViewById(R.id.free_gender_ll);
        free_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Freelancer_Register_Activity.this);
                builder.setTitle("CHOOSE GENDER");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Freelancer_Register_Activity.this, android.R.layout.select_dialog_item, gen_title);
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


        LinearLayout free_nationality = (LinearLayout)findViewById(R.id.free_nationality_ll);
        free_nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Freelancer_Register_Activity.this);
                builder.setTitle("CHOOSE NATIONALITY");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Freelancer_Register_Activity.this, android.R.layout.select_dialog_item, nations_title);
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

        uploadlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 temp = (ImageView) findViewById(R.id.free_logo_ll);

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage1;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage2;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage3;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage4;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage5;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage6;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage7;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage8;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage9;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        wimage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = wimage10;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        signup = (LinearLayout)findViewById(R.id.free_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                gender_str = gender.getText().toString();
                age_str = age.getText().toString();
                nationalitty_str = nationality.getText().toString();
                fields_str = fields.getText().toString();
                uploadlogo_str = uploadlogo.getText().toString();

                if (name_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if (!email_str.matches(emailPattern)) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if (gender_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter gender", Toast.LENGTH_SHORT).show();
                }
                else if (age_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please select ur age", Toast.LENGTH_SHORT).show();
                }
                if (nationalitty_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter your nationality", Toast.LENGTH_SHORT).show();
                }
                else if (fields_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter the field you worked for", Toast.LENGTH_SHORT).show();
                }
                // else if(uploadlogo_str.equals("")){
                //  Toast.makeText(Freelancer_Register_Activity.this, "please upload your logo or profilepic", Toast.LENGTH_SHORT).show();
                // }

                else {
                    Toast.makeText(Freelancer_Register_Activity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
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


                // Set the Image in ImageView after decoding the String
                temp.setImageBitmap(BitmapFactory
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
                Toast.makeText(Freelancer_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
}
