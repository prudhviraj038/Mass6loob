package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 7/22/2016.
 */
public class Signup_Screen_Activity extends Activity {
    EditText name, password,email,phone;
    TextView login_tv;
    LinearLayout register;
    String name_str,pwdss_str,email_str,phn_str;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        name = (EditText) findViewById(R.id.sign_name);
        password = (EditText) findViewById(R.id.sign_pwd);
        email = (EditText) findViewById(R.id.sign_email);
        login_tv = (TextView) findViewById(R.id.login_tv);
       phone = (EditText) findViewById(R.id.sign_phn);

       register = (LinearLayout) findViewById(R.id.register_ll);
        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_Screen_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register() {
        String name_str = name.getText().toString();
        String pwdss_str = password.getText().toString();
        String email_str = email.getText().toString();
        String phn_str = phone.getText().toString();

        if (name_str.equals(""))
            Toast.makeText(Signup_Screen_Activity.this, "Pls_ent_username", Toast.LENGTH_SHORT).show();
        else if (pwdss_str.equals(""))
            Toast.makeText(Signup_Screen_Activity.this, "Pls_ent_Password", Toast.LENGTH_SHORT).show();
        else if (password.length() < 6)
            Toast.makeText(Signup_Screen_Activity.this, "Password Lenth should be grether than 6 charcters", Toast.LENGTH_SHORT).show();
        else if (email_str.equals(""))
            Toast.makeText(Signup_Screen_Activity.this, "Pls_ent_email", Toast.LENGTH_SHORT).show();
        else if (phn_str.equals(""))
            Toast.makeText(Signup_Screen_Activity.this, "Pls_ent_Phone", Toast.LENGTH_SHORT).show();
        else {
            String url = Settings.SERVERURL + "add-member.php?";
            try {
                url = url + "name=" + URLEncoder.encode(name_str, "utf-8") +
                        "&password=" + URLEncoder.encode(pwdss_str, "utf-8")+ "&phone=" + URLEncoder.encode(phn_str, "utf-8")+ "&email=" + URLEncoder.encode(email_str, "utf-8");
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
                            Toast.makeText(Signup_Screen_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            String mem_id = jsonArray.getJSONObject(0).getString("member_id");
                            String name = jsonArray.getJSONObject(0).getString("member_id");
                            Settings.setUserid(Signup_Screen_Activity.this, mem_id, name);
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
                    Toast.makeText(Signup_Screen_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            });

// Access the RequestQueue through your singleton class.
            AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        }
     }

}

