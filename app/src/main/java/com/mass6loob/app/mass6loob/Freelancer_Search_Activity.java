package com.mass6loob.app.mass6loob;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by sriven on 6/21/2016.
 */
public class Freelancer_Search_Activity extends  RootActivity {
    EditText jobtitle;
    LinearLayout search;
    ImageView sett;
    TextView experience,education;
    EditText nationality;
    String title_str,expe_str,age_str,edu_str;
    String exp_id ="0";
    String edu_id ="0";
    String cli_id = "0";
    ArrayList<String> exps_id;
    ArrayList<String>exps_title;
    ArrayList<Freelancer> freelancers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.freelancer_search);
        exps_title = new ArrayList<String>();
        exps_id = new ArrayList<String>();
        freelancers=new ArrayList<>();
        sett = (ImageView)findViewById(R.id.sett_free_lancer);
        jobtitle = (EditText)findViewById(R.id.free_search_title);
        experience = (TextView)findViewById(R.id.free_search_exp);
        nationality = (EditText)findViewById(R.id.free_search_age);
        try {
            JSONObject jsonObject= new JSONObject(Settings.getSettings_json(Freelancer_Search_Activity.this));
            if(jsonObject.getJSONArray("freelancers_company").length()!=0){
                cli_id=jsonObject.getJSONArray("freelancers_company").getJSONObject(0).getString("id");
                Log.e("idddd",cli_id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(cli_id.equals("0"))
            sett.setVisibility(View.GONE);
        else
            sett.setVisibility(View.VISIBLE);
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Freelancer_Search_Activity.this,Freelancer_Client_Register_Activity.class);
                intent.putExtra("user","1");
                startActivity(intent);
            }
        });
        LinearLayout emp_search_exp = (LinearLayout)findViewById(R.id.free_search_exp_ll);
        emp_search_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Freelancer_Search_Activity.this);
                builder.setTitle("CHOOSE EXPERIENCE");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Freelancer_Search_Activity.this, android.R.layout.select_dialog_item, exps_title);
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
        
        search = (LinearLayout)findViewById(R.id.free_search_ll);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_str = jobtitle.getText().toString();
                expe_str = experience.getText().toString();
                age_str = nationality.getText().toString();
//                 if(title_str.equals("")){
//                    Toast.makeText(Freelancer_Search_Activity.this, "please enter jobtitle", Toast.LENGTH_SHORT).show();
//                }
//                else if(expe_str.equals("")){
//                    Toast.makeText(Freelancer_Search_Activity.this, "please enter experience", Toast.LENGTH_SHORT).show();
//                }
//                else if(age_str.equals("")){
//                    Toast.makeText(Freelancer_Search_Activity.this, "please enter your age", Toast.LENGTH_SHORT).show();
//                }else{
//                     get_list();
                     Intent intent = new Intent(Freelancer_Search_Activity.this,Freelancer_List_Activity.class);
                     intent.putExtra("title_str",title_str);
                     intent.putExtra("age_str",age_str);
                     intent.putExtra("exp_id",exp_id);
                     startActivity(intent);
//                 }

            }
        });
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
                        String exp_name = sub.getString("title"+Settings.get_lan(Freelancer_Search_Activity.this));
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
                Toast.makeText(Freelancer_Search_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
   
}

