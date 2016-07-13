package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/22/2016.
 */
public class Freelancer_Client_Register_Activity extends  RootActivity {
    EditText name,email,phone;
    LinearLayout signup;
    String name_str,email_str,phn_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.freelancer_client_register);
        name = (EditText)findViewById(R.id.client_name);
        email = (EditText)findViewById(R.id.client_email);
        phone =(EditText)findViewById(R.id.client_phn);
        signup = (LinearLayout) findViewById(R.id.client_signup_ll);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                phn_str = phone.getText().toString();
                if(name_str.equals("")){
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter your name", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(phn_str.equals("")){
                    Toast.makeText(Freelancer_Client_Register_Activity.this, "please enter your phone number", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Freelancer_Client_Register_Activity.this,Freelancer_List_Activity.class);
                startActivity(intent);
            }
        });

    }
    //public  void Freelancer_client_register(){
       // final ProgressDialog progressDialog = new ProgressDialog(this);
      //  progressDialog.setMessage("please wait.. we are processing");
       // progressDialog.show();
       // progressDialog.setCancelable(false);
       // String url = Settings.SERVER_URL+"missed-customer.php?";

       // StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
         //   @Override
            //public void onResponse(String response) {
             //   if(progressDialog!=null)
             //       progressDialog.dismiss();
               // try {
                //  JSONObject jsonObject=new JSONObject(response);
               //     String reply=jsonObject.getString("status");
               //     if(reply.equals("Success")) {
                 //       String msg = jsonObject.getString("message");
               //         Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
              //          finish();
               //     }
                //    else {
                  //      String msg=jsonObject.getString("message");
                 //       Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                  //  }

               // } catch (JSONException e) {
               //     e.printStackTrace();
               // }
          // }
       // },
             //   new Response.ErrorListener() {
                //    @Override
                //    public void onErrorResponse(VolleyError error) {

                      //  if(progressDialog!=null)
                      //      progressDialog.dismiss();
                      //  Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                 //   }
               // }){
          //  @Override
          //  protected Map<String,String> getParams(){
            //    Map<String,String> params = new HashMap<String, String>();
              //  params.put("name",name_str);
              //  params.put("phone",phn_str);
               // params.put("email",email_str);
//
             //   return params;
          //  }
       // };
       // AppController.getInstance().addToRequestQueue(stringRequest);
  //  }

}
