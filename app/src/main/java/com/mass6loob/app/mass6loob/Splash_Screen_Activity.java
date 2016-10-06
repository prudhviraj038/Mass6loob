package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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


/**
 * Created by sriven on 6/23/2016.
 */
public class Splash_Screen_Activity extends  RootActivity {
    ImageView english,arabic;
    TextView skip;
    Handler handler;
    Runnable runnable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        skip = (TextView)findViewById(R.id.skip);
        //english = (ImageView)findViewById(R.id.image_english);

        handler = new Handler();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
       // ImageView imageView=(ImageView)findViewById(R.id.splash);
        //imageView.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation));
         handler.postDelayed(runnable,3000);
        runnable=new Runnable() {
            @Override
            public void run() {newone();
            }
        };
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                newone();
            }
        });
    }
    public  void newone(){
        member_details();

    }
    public  void member_details(){
        String url = Settings.SERVERURL + "member-details.php?member_id="+Settings.getUserid(Splash_Screen_Activity.this);
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
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    Settings.setSettings(Splash_Screen_Activity.this,jsonObject.toString());
                    String abc= Settings.get_Is_first_time(Splash_Screen_Activity.this);
                    Log.e("lng", abc);
                    if(abc.equals("-1")) {
                        Intent mainIntent = new Intent(getApplicationContext(), Language_Activity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    else {
                        Intent mainIntent = new Intent(getApplicationContext(), Home_Activity.class);
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
                Toast.makeText(Splash_Screen_Activity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

}
