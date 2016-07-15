package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
public class Employee_Search_Activity extends  RootActivity {
    EditText jobtitle;
    LinearLayout search;
    TextView experience,nationality,education;
    String title_str,expe_str,nation_str,edu_str;
    String exp_id ="0";
    String edu_id ="0";
    ArrayList<String> exps_id;
    ArrayList<String>exps_title;
    ArrayList<String>nations_id;
    ArrayList<String>nations_title;
    ArrayList<String>edus_id;
    ArrayList<String>edus_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.employee_search);
        exps_title = new ArrayList<String>();
        nations_title = new ArrayList<String>();
        edus_title = new ArrayList<String>();

        jobtitle = (EditText)findViewById(R.id.emp_search_title);
        experience = (TextView)findViewById(R.id.emp_search_exp);
        nationality = (TextView)findViewById(R.id.emp_search_nationality);
        education = (TextView)findViewById(R.id.emp_search_edu);
        LinearLayout emp_search_exp = (LinearLayout)findViewById(R.id.emp_search_exp_ll);
        emp_search_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Search_Activity.this);
                builder.setTitle("CHOOSE EXPERIENCE");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Search_Activity.this, android.R.layout.select_dialog_item, exps_title);
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

        LinearLayout emp_search_nationality = (LinearLayout)findViewById(R.id.emp_search_nation_ll);
        emp_search_nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Search_Activity.this);
                builder.setTitle("CHOOSE NATIONALITY");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Search_Activity.this, android.R.layout.select_dialog_item, exps_title);
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
        get_nationality();
        LinearLayout emp_education = (LinearLayout)findViewById(R.id.emp_search_edu_ll);
        emp_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Employee_Search_Activity.this);
                builder.setTitle("CHOOSE EDUCATION");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee_Search_Activity.this, android.R.layout.select_dialog_item, edus_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, sub_title.get(which), Toast.LENGTH_SHORT).show();
                        edu_id = edus_id.get(which);
                        education.setText(edus_title.get(which));

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        get_education();

        search = (LinearLayout)findViewById(R.id.emp_search_ll);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_str = jobtitle.getText().toString();
                expe_str = experience.getText().toString();
                nation_str = nationality.getText().toString();
                edu_str = education.getText().toString();
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

    private void get_experience() {
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
                        String exp_num = sub.getString("title");
                        String exp_id = sub.getString("id");
                        exps_id.add(exp_id);
                        exps_title.add(exp_num);
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
                Toast.makeText(Employee_Search_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
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
                Toast.makeText(Employee_Search_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
    private void get_education() {
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
                        String edu_title = sub.getString("title");
                        String edu_id = sub.getString("id");
                        edus_id.add(edu_id);
                        edus_title.add(edu_title);
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
                Toast.makeText(Employee_Search_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
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
