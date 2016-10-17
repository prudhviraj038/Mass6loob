package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;

/**
 * Created by sriven on 6/22/2016.
 */
public class Users_List_Activity extends  RootActivity {
    ArrayList<User> users;
    ArrayList<Package> packages;
    UserAdapter employees;
    ListView userslist,pack_list;
    PackageAdapter packageAdapter;
    LinearLayout pack_pop;
    ImageView close;
    String title_str="";
    String nation_str="";
    String exp_id="";
    String edu_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userslist);
        users=new ArrayList<>();
        packages=new ArrayList<>();
        title_str=getIntent().getStringExtra("title_str");
        nation_str=getIntent().getStringExtra("nation_str");
        exp_id=getIntent().getStringExtra("exp_id");
        edu_id=getIntent().getStringExtra("edu_id");
        Log.e(title_str,nation_str+","+exp_id+","+edu_id);
        userslist = (ListView)findViewById(R.id.list_view);
        pack_list = (ListView)findViewById(R.id.pack_list);
        pack_pop=(LinearLayout)findViewById(R.id.pack_pop);
        close=(ImageView)findViewById(R.id.close_pack);
        userslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        pack_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pack_pop.setVisibility(View.GONE);
                pack_details(packages.get(position).id);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pack_pop.setVisibility(View.GONE);
            }
        });
        get_list();
    }
    public void get_list() {
        Log.e(title_str,nation_str+","+exp_id+","+edu_id);
        String url = Settings.SERVERURL + "cvs.php?";
        try {
            url = url + "title=" + URLEncoder.encode(title_str, "utf-8") +
                    "&nationality=" + URLEncoder.encode(nation_str, "utf-8")+
                    "&experience=" + URLEncoder.encode(exp_id, "utf-8")+
                    "&education=" + URLEncoder.encode(edu_id, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("urllll--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                users.clear();
                Log.e("response is: ", jsonObject.toString());
                try {

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        User user= new User(sub,Users_List_Activity.this);
                        users.add(user);
                    }
                    if(users.size()==0){
                        Toast.makeText(Users_List_Activity.this, "there is no cvs on your search", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        employees = new UserAdapter(Users_List_Activity.this,users,Users_List_Activity.this);
                        userslist.setAdapter(employees);
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
                Toast.makeText(Users_List_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    public  void get_package(){

        String url = Settings.SERVERURL + "packages.php?member_id="+Settings.getUserid(Users_List_Activity.this);
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                packages.clear();
                Log.e("response is: ", jsonObject.toString());
                try {

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        Package pack=new Package(sub);
                        packages.add(pack);
                    }
                    pack_pop.setVisibility(View.VISIBLE);
                    packageAdapter=new PackageAdapter(Users_List_Activity.this,packages);
                    pack_list.setAdapter(packageAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(Users_List_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    public  void  pack_details(String id){
        String url = Settings.SERVERURL + "add-package.php?member_id="+Settings.getUserid(this)+"&package_id="+id;
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("message");
                    if(status.equals("Failed")){
                        Toast.makeText(Users_List_Activity.this,msg, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Users_List_Activity.this,msg, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(Users_List_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}
