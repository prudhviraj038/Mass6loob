package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sriven on 7/22/2016.
 */
public class Login_Activity extends Activity {
    EditText uname, password;
    LinearLayout login;
    String uname_str, pwds_str;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        uname = (EditText) findViewById(R.id.usr_name);
        password = (EditText) findViewById(R.id.pwd);
        login = (LinearLayout) findViewById(R.id.login_ll);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logindetails();
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
        else if (password.length() < 6)
            Toast.makeText(Login_Activity.this, "Password Lenth should be grether than 6 charcters", Toast.LENGTH_SHORT).show();
        else {
            String url = Settings.SERVERURL + "login.php?";
            try {
                url = url + "username=" + URLEncoder.encode(uname_str, "utf-8") +
                        "&password=" + URLEncoder.encode(pwds_str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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
                        String reply = jsonObject.getJSONArray("response").getJSONObject(0).getString("status");
                        if (reply.equals("Failure")) {
                            String msg = jsonObject.getJSONArray("response").getJSONObject(0).getString("message");
                            Toast.makeText(Login_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            String mem_id = jsonObject.getJSONArray("response").getJSONObject(0).getString("member_id");
                            String name = jsonObject.getJSONArray("response").getJSONObject(0).getString("name");
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
            AppController.getInstance().addToRequestQueue(jsObjRequest);

        }
     }
    }
