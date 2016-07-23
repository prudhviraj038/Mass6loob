package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sriven on 7/22/2016.
 */
public class Login_Activity extends Activity {
    EditText uname, password;
    TextView signup_tv, fpassword;
    LinearLayout login;
    String uname_str, pwds_str;
    String write;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        uname = (EditText) findViewById(R.id.usr_name);
        password = (EditText) findViewById(R.id.pwd);
        signup_tv = (TextView) findViewById(R.id.signup_tv);

        fpassword = (TextView) findViewById(R.id.forget_pwd);
        signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this,Signup_Screen_Activity.class);
                startActivity(intent);
            }
        });

        login = (LinearLayout) findViewById(R.id.login_ll);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logindetails();
            }
        });

        fpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(Login_Activity.this);
                alert1.setTitle("Enter Email id");
                final EditText input = new EditText(Login_Activity.this);
                input.setMinLines(5);
                input.setVerticalScrollBarEnabled(true);
               // input.setBackgroundResource(R.drawable.comments_bg);
                input.setPadding(10, 10, 10, 10);
                alert1.setView(input);
                alert1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        write = input.getText().toString();
                        if (write.equals(""))
//                            alert.showAlertDialog(getActivity(), "Info", Settings.getword(getActivity(), "please_enter_email"), false);
                            Toast.makeText(getApplicationContext(), "Please enter email id", Toast.LENGTH_SHORT).show();
                        else if (!write.matches(emailPattern))
//                            alert.showAlertDialog(getActivity(), "Info", Settings.getword(getActivity(),"enter_valid_email"), false);
                            Toast.makeText(getApplicationContext(), "Please enter valid email id", Toast.LENGTH_SHORT).show();
                        else
                            forgot_pass();
                    }
                });
                alert1.show();
            }
        });
    }

    private void logindetails() {
        String uname_str = uname.getText().toString();
        String pwds_str = password.getText().toString();
        if (uname_str.equals(""))
            Toast.makeText(Login_Activity.this, "Pls_ent_username", Toast.LENGTH_SHORT).show();
        else if (pwds_str.equals(""))
            Toast.makeText(Login_Activity.this, "Pls_ent_Password", Toast.LENGTH_SHORT).show();
         {
            String url = Settings.SERVERURL + "login.php?";
            try {
                url = url + "email=" + URLEncoder.encode(uname_str, "utf-8") +
                        "&password=" + URLEncoder.encode(pwds_str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.e("url--->", url);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray jsonArray) {
                    progressDialog.dismiss();
                    Log.e("response is: ", jsonArray.toString());
                    try {
                        String reply = jsonArray.getJSONObject(0).getString("status");
                        if (reply.equals("Failed")) {
                            String msg = jsonArray.getJSONObject(0).getString("message");
                            Toast.makeText(Login_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            String mem_id = jsonArray.getJSONObject(0).getString("member_id");
                            String name = jsonArray.getJSONObject(0).getString("name");
                            Settings.setUserid(Login_Activity.this, mem_id, name);
                            Intent mainIntent = new Intent(getApplicationContext(), Employee_Search_Activity.class);
                            mainIntent.putExtra("uid", mem_id);
                            startActivity(mainIntent);
                            finish();
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
                    Toast.makeText(Login_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            });

// Access the RequestQueue through your singleton class.
            AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        }
    }
    public void forgot_pass(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url="";

            url = Settings.SERVERURL + "forget-password.php?email="+write;
        Log.e("url--->", url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    String reply=jsonObject.getString("status");
                    if(reply.equals("Failed")) {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(), Settings.getword(getApplicationContext(),"server_not_connected"), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}


