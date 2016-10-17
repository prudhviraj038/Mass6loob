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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/22/2016.
 */
public class Freelancer_Client_Register_Activity extends  RootActivity {
    EditText name, email, phone;
    LinearLayout signup;
    TextView loginn,submit_tv;
    String name_str, email_str, phn_str;
    String cli_id,tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.freelancer_client_register);
        tt=getIntent().getStringExtra("user");
        submit_tv = (TextView)findViewById(R.id.sign_up_tv);
        submit_tv.setText(Settings.getword(this,"submit"));
        name = (EditText) findViewById(R.id.client_name);
        email = (EditText) findViewById(R.id.client_email);
        phone = (EditText) findViewById(R.id.client_phn);
        loginn = (TextView) findViewById(R.id.log_here);
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Freelancer_Client_Register_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
        if(tt.equals("1")){
            try {
                JSONObject jsonObject= new JSONObject(Settings.getSettings_json(Freelancer_Client_Register_Activity.this));
                if(jsonObject.getJSONArray("freelancers_company").length()!=0){
                    submit_tv.setText(Settings.getword(this,"update"));
                    cli_id=jsonObject.getJSONArray("freelancers_company").getJSONObject(0).getString("id");
                    name.setText(jsonObject.getJSONArray("freelancers_company").getJSONObject(0).getString("name"));
                    email.setText(jsonObject.getJSONArray("freelancers_company").getJSONObject(0).getString("email"));
                    phone.setText(jsonObject.getJSONArray("freelancers_company").getJSONObject(0).getString("phone"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        signup = (LinearLayout) findViewById(R.id.client_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                phn_str = phone.getText().toString();
                if (name_str.equals("")) {
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter your name", Toast.LENGTH_SHORT).show();
                } else if (!email_str.matches(emailPattern)) {
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                } else if (phn_str.equals("")) {
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter your phone number", Toast.LENGTH_SHORT).show();
                }else {
                    client_register();
//                    Intent intent = new Intent(Freelancer_Client_Register_Activity.this, Freelancer_List_Activity.class);
//                    startActivity(intent);
                }
            }
        });

    }
    public  void client_register(){
        final ProgressDialog progressDialog = new ProgressDialog(Freelancer_Client_Register_Activity.this);
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVERURL+"add-freelancer-company.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    JSONObject jsonObject1=jsonObject;
                    String reply=jsonObject1.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject1.getString("message");
                        cli_id=jsonObject1.getString("freelancer_company_id");
                        Toast.makeText(Freelancer_Client_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Freelancer_Client_Register_Activity.this,Freelancer_Search_Activity.class);
                        startActivity(intent);

//                        finish();

                    }
                    else {
                        String msg=jsonObject1.getString("message");
                        Toast.makeText(Freelancer_Client_Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Freelancer_Client_Register_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("member_id",Settings. getUserid(Freelancer_Client_Register_Activity.this));

                params.put("name",name_str);
                params.put("email",email_str);
                params.put("phone",phn_str);
                if(tt.equals("1")){
                    params.put("company_id",cli_id);
                }
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
