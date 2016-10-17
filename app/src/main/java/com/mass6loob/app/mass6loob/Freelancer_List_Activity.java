package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
 * Created by sriven on 6/22/2016.
 */
public class Freelancer_List_Activity extends  RootActivity {
    ArrayList<Freelancer> freelancers;
    FreelancerAdapter employees;
    ListView users_list;
    String title_str="";
    String age_str="";
    String exp_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.freelancer_list);
        freelancers=new ArrayList<>();
        title_str=getIntent().getStringExtra("title_str");
        age_str=getIntent().getStringExtra("age_str");
        exp_id=getIntent().getStringExtra("exp_id");
//        freelancers=(ArrayList<Freelancer>)getIntent().getSerializableExtra("user");
        users_list = (ListView)findViewById(R.id.lancer_list_view);
        users_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        get_list();

    }
    private void get_list() {
        String url = Settings.SERVERURL + "freelancers.php?";
        try {
            url = url +"field=" + URLEncoder.encode(title_str, "utf-8") +
                    "&age=" + URLEncoder.encode(age_str, "utf-8")+
                    "&experience=" + URLEncoder.encode(exp_id, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                freelancers.clear();
                Log.e("response is: ", jsonObject.toString());
                try {
                    if(jsonObject.length()==0){
                        Toast.makeText(Freelancer_List_Activity.this, "there is no freelancers on your search", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        for (int i = 0; i < jsonObject.length(); i++) {
                            JSONObject sub = jsonObject.getJSONObject(i);
                            Freelancer pack = new Freelancer(sub);
                            freelancers.add(pack);
                        }
                        employees = new FreelancerAdapter(Freelancer_List_Activity.this,freelancers);
                        users_list.setAdapter(employees);
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
                Toast.makeText(Freelancer_List_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}
