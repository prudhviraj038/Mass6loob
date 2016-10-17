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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sriven on 7/22/2016.
 */
public class EditProfile_Activity extends Activity {
    EditText name, password,email,phone;
    TextView login_tv;
    LinearLayout register;
    String name_str,pwdss_str,email_str,phn_str;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_screen);
        name = (EditText) findViewById(R.id.ef_name);
        email = (EditText) findViewById(R.id.ef_email);
        phone = (EditText) findViewById(R.id.ef_phn);
        try {
            JSONObject jsonObject= new JSONObject(Settings.getSettings_json(EditProfile_Activity.this));
                name.setText(jsonObject.getString("name"));
                email.setText(jsonObject.getString("email"));
                phone.setText(jsonObject.getString("phone"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        register = (LinearLayout) findViewById(R.id.ef_update_ll);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register() {
        String name_str = name.getText().toString();
        String email_str = email.getText().toString();
        String phn_str = phone.getText().toString();

        if (name_str.equals(""))
            Toast.makeText(EditProfile_Activity.this, "Pls_ent_username", Toast.LENGTH_SHORT).show();
        else if (email_str.equals(""))
            Toast.makeText(EditProfile_Activity.this, "Pls_ent_email", Toast.LENGTH_SHORT).show();
        else if (phn_str.equals(""))
            Toast.makeText(EditProfile_Activity.this, "Pls_ent_Phone", Toast.LENGTH_SHORT).show();
        else {
            String url = Settings.SERVERURL + "edit-profile.php?";
            try {
                url = url +"&member_id=" + URLEncoder.encode(Settings.getUserid(this), "utf-8") + "&name=" + URLEncoder.encode(name_str, "utf-8") +
                        "&phone=" + URLEncoder.encode(phn_str, "utf-8")+ "&email=" + URLEncoder.encode(email_str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.e("url--->", url);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject jsonObject) {
                    progressDialog.dismiss();
                    Log.e("response is: ", jsonObject.toString());
                    try {
                        String reply = jsonObject.getString("status");
                        if (reply.equals("Failed")) {
                            String msg = jsonObject.getString("message");
                            Toast.makeText(EditProfile_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            String msg = jsonObject.getString("message");
                            Toast.makeText(EditProfile_Activity.this, msg, Toast.LENGTH_SHORT).show();
                            //Intent mainIntent = new Intent(getApplicationContext(), Employee_Search_Activity.class);
                           // mainIntent.putExtra("uid", mem_id);
                          //  startActivity(mainIntent);
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
                    Toast.makeText(EditProfile_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            });
// Access the RequestQueue through your singleton class.
                AppController.getInstance().addToRequestQueue(jsonObjectRequest);

            }
     }

}

