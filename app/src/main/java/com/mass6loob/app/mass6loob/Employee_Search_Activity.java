package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sriven on 6/21/2016.
 */
public class Employee_Search_Activity extends  RootActivity {
    EditText jobtitle,experience,nationality,education;
    LinearLayout search;
    String title_str,expe_str,nation_str,edu_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.employee_search);
        jobtitle = (EditText)findViewById(R.id.emp_search_title);
        experience = (EditText)findViewById(R.id.emp_search_exp);
        nationality = (EditText)findViewById(R.id.emp_search_nationality);
        education = (EditText)findViewById(R.id.emp_search_edu);
        search = (LinearLayout)findViewById(R.id.emp_search_ll);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_str = jobtitle.getText().toString();
                expe_str = experience.getText().toString();
                nation_str = nationality.getText().toString();
                edu_str = nationality.getText().toString();
                 if(title_str.equals("")){
                    Toast.makeText(Employee_Search_Activity.this, "please enter jobtitle", Toast.LENGTH_SHORT).show();
                }
                else if(expe_str.equals("")){
                    Toast.makeText(Employee_Search_Activity.this, "please enter experience", Toast.LENGTH_SHORT).show();
                }
                else if(nation_str.equals("")){
                    Toast.makeText(Employee_Search_Activity.this, "please select ur nationality", Toast.LENGTH_SHORT).show();
                }
                 else if(edu_str.equals("")){
                     Toast.makeText(Employee_Search_Activity.this, "please select ur education", Toast.LENGTH_SHORT).show();
                 }
                Intent intent = new Intent(Employee_Search_Activity.this,Users_List_Activity.class);
                startActivity(intent);


            }
        });
    }
   //public  void Freelancer_client_register(){
    // final ProgressDialog progressDialog = new ProgressDialog(this);
     //  progressDialog.setMessage("please wait.. we are processing");
    // progressDialog.show();
    // progressDialog.setCancelable(false);
    // String url = Settings.SERVER_URL+"employee_search.php?";

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
    //  params.put("jobtitle", title_str);
    //  params.put("experience", expe_str);
    //  params.put("nationality",nation_str);
    // params.put("education",edu_str);
//
    //   return params;
    //  }
    // };
    // AppController.getInstance().addToRequestQueue(stringRequest);
    //  }
}
