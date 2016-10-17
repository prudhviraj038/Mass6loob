package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/22/2016.
 */
public class Volunteer_List_Activity extends  RootActivity {
    ArrayList<Volunteer> volunteers;
    VolunteerAdapter employees;
    ListView volunteerlist;
    LinearLayout sett;
    String cli_id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.volunteer_list);
        volunteers=new ArrayList<>();
        get_list();
        sett=(LinearLayout)findViewById(R.id.sett_v_ll);
        volunteerlist = (ListView)findViewById(R.id.volunteer_list);
        volunteerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Volunteer_List_Activity.this, Volunteer_Register_Activity.class);
                intent.putExtra("user", "1");
                startActivity(intent);
            }
        });
        try {
            JSONObject jsonObject= new JSONObject(Settings.getSettings_json(Volunteer_List_Activity.this));
            if(jsonObject.getJSONArray("volunteers").length()!=0){
                cli_id=jsonObject.getJSONArray("volunteers").getJSONObject(0).getString("id");
                Log.e("idddd",cli_id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(cli_id.equals("0"))
            sett.setVisibility(View.GONE);
        else
            sett.setVisibility(View.VISIBLE);

    }
    public  void get_list(){

        String url = Settings.SERVERURL + "volunteer-companies.php?member_id="+Settings.getUserid(Volunteer_List_Activity.this);
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                volunteers.clear();
                Log.e("response is: ", jsonObject.toString());
                try {

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        Volunteer pack=new Volunteer(sub);
                        volunteers.add(pack);
                    }
                    employees = new VolunteerAdapter(Volunteer_List_Activity.this,volunteers);
                    volunteerlist.setAdapter(employees);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Volunteer_List_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}
