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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/21/2016.
 */
public class Freelancer_Register_Activity extends RootActivity {
    EditText name,email,age,fields;
    TextView nationality,gender,uploadlogo,experience;
    ImageView wimage1,wimage2,wimage3,wimage4,wimage5,wimage6,wimage7,wimage8,wimage9,wimage10;
    ImageView temp;
    LinearLayout signup;
    String name_str,email_str,exp_str,gender_str,age_str,nationalitty_str,fields_str,uploadlogo_str;
    String nation_id="0";
    String exp_id = "0";
    String free_id;
    ArrayList<String> gen_title;
    ArrayList<String>nations_id;
    ArrayList<String>nations_title;
    ArrayList<String>exps_id;
    ArrayList<String>exps_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.freelancer_register);
        gen_title = new   ArrayList<String>();
        nations_title = new ArrayList<String>();
        nations_id = new ArrayList<String>();
        exps_id = new ArrayList<String>();
        exps_title = new ArrayList<String>();
        gen_title.add("Male");
        gen_title.add("Female");
        name = (EditText)findViewById(R.id.free_name);
        email = (EditText)findViewById(R.id.free_email);
        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        gender  = (TextView)findViewById(R.id.free_gender);
        age  = (EditText)findViewById(R.id.free_age);
        nationality  = (TextView)findViewById(R.id.free_nationality);
        fields  = (EditText)findViewById(R.id.free_fields);
        experience = (TextView) findViewById(R.id.free_exp_tv);
        uploadlogo = (TextView)findViewById(R.id.free_upload);
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
        try {
            JSONObject jsonObject= new JSONObject(Settings.getSettings_json(Freelancer_Register_Activity.this));
            if(jsonObject.getJSONArray("freelancers").length()!=0){
                name.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("name"));
                email.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("email"));
                gender.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("gender"));
                age.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("age"));
                nationality.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("nationality"));
                fields.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("field"));
                fields.setText(jsonObject.getJSONArray("freelancers").getJSONObject(0).getJSONObject("experience").getString("title"+Settings.get_lan(Freelancer_Register_Activity.this)));
//                if(!jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("image").equals("")) {
//                    Picasso.with(this).load(jsonObject.getJSONArray("freelancers").getJSONObject(0).getString("image")).into(temp);
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

        LinearLayout emp_experience = (LinearLayout) findViewById(R.id.free_exp_ll);
        emp_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Freelancer_Register_Activity.this);
                builder.setTitle("CHOOSE EXPERIENCE");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Freelancer_Register_Activity.this, android.R.layout.select_dialog_item, exps_title);
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
//                        nation_id = nations_id.get(which);
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
                startActivityForResult(i, RESULT_LOAD_LOGO);
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
                exp_str = experience.getText().toString();
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
                } else if (exp_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter experience", Toast.LENGTH_SHORT).show();
                }else if (nationalitty_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter your nationality", Toast.LENGTH_SHORT).show();
                }
                else if (fields_str.equals("")) {
                    Toast.makeText(Freelancer_Register_Activity.this, "please enter the field you worked for", Toast.LENGTH_SHORT).show();
                }
                // else if(uploadlogo_str.equals("")){
                //  Toast.makeText(Freelancer_Register_Activity.this, "please upload your logo or profilepic", Toast.LENGTH_SHORT).show();
                // }

                else {
                    free_register();
                  //  Toast.makeText(Freelancer_Register_Activity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
    boolean isimgchoosen = false;
    boolean islogochoosen = false;
    final int RESULT_LOAD_IMAGE = 1;
    final int RESULT_LOAD_LOGO =2;
    ArrayList<String> imgDecodableString=new ArrayList<>();
    String imgDecodableLogo;

    String encodedString;
    String encodedLogo;
    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_LOGO && resultCode == RESULT_OK
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
                imgDecodableLogo = cursor.getString(columnIndex);
                cursor.close();


                // Set the Image in ImageView after decoding the String
                temp.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableLogo));
                islogochoosen=true;
            }else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
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
                imgDecodableString .add(cursor.getString(columnIndex));
                cursor.close();


                // Set the Image in ImageView after decoding the String
                temp.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString.get(imgDecodableString.size()-1)));
                isimgchoosen=true;
            }else {
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
                        String exp_name = sub.getString("title"+Settings.get_lan(Freelancer_Register_Activity.this));
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
                Toast.makeText(Freelancer_Register_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Freelancer_Register_Activity.this, "cant_rech_server",Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    public  void free_register(){
        final ProgressDialog progressDialog = new ProgressDialog(Freelancer_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-freelancer.php?";

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
                        free_id=jsonObject.getString("freelancer_id");
                       //    emp_image();
                        if(islogochoosen){
                            encodeImagetoLogo();
                        }else if(isimgchoosen){
                            encodeImagetoString();
                        }else {
                            Toast.makeText(Freelancer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Freelancer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Freelancer_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Freelancer_Register_Activity.this));
                params.put("name",name_str);
                params.put("email",email_str);
                params.put("experience",exp_id);
                params.put("age",age_str);
                params.put("gender",gender_str);
                params.put("fields",fields_str);
                params.put("nationality",nationalitty_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public  void emp_image(){
        final ProgressDialog progressDialog = new ProgressDialog(Freelancer_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-freelancer-multi.php?";

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
                        Toast.makeText(Freelancer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        if(isimgchoosen) {
                            imgDecodableString.remove(imgDecodableString.size() - 1);
                            if (imgDecodableString.size() > 0) {
                                encodeImagetoString();
                            } else {
                                finish();
                            }
                        }else{
                            finish();
                        }
                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Freelancer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Freelancer_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Freelancer_Register_Activity.this));
                params.put("file",encodedString);
                params.put("ext_str", "jpg");
                params.put("freelancer_id",free_id);

                //  params.put("image_id",free_id);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public  void emp_logo(){
        final ProgressDialog progressDialog = new ProgressDialog(Freelancer_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-freelancer-image.php?";

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
                        Toast.makeText(Freelancer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        if(islogochoosen) {
                            encodeImagetoString();
                        }else{
                            finish();
                        }
                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(Freelancer_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Freelancer_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Freelancer_Register_Activity.this));
                params.put("file",encodedLogo);
                params.put("ext_str", "jpg");
                params.put("freelancer_id",free_id);

                //  params.put("image_id",free_id);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {
            final ProgressDialog progressDialog = new ProgressDialog(Freelancer_Register_Activity.this);

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
                bitmap = BitmapFactory.decodeFile(imgDecodableString.get(imgDecodableString.size()-1), options);
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
                emp_image();

            }
        }.execute(null, null, null);
    }
    public void encodeImagetoLogo() {
        new AsyncTask<Void, Void, String>() {
            final ProgressDialog progressDialog = new ProgressDialog(Freelancer_Register_Activity.this);

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
                encodedLogo = "";
                bitmap = BitmapFactory.decodeFile(imgDecodableLogo, options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String
                encodedLogo = Base64.encodeToString(byte_arr, Base64.NO_WRAP);


                return "";
            }

            @Override
            protected void onPostExecute(String msg) {

                // Put converted Image string into Async Http Post param
                // Trigger Image upload
                if(progressDialog!=null)
                    progressDialog.dismiss();
                emp_logo();

            }
        }.execute(null, null, null);
    }



}
