package com.mass6loob.app.mass6loob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
public class SettingsActivity extends  RootActivity {
    LinearLayout ef,logout,cp_ll,submit;
    TextView ef_tv,logout_tv,submit_tv;
    EditText op,np,cp;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.settings_activity);
        viewFlipper=(ViewFlipper)findViewById(R.id.viewFlipper);
        ef=(LinearLayout)findViewById(R.id.edit_profile);
        logout=(LinearLayout)findViewById(R.id.logout);
        cp_ll=(LinearLayout)findViewById(R.id.c_pass_ll);
        submit=(LinearLayout)findViewById(R.id.submit__c_ll);
        op=(EditText)findViewById(R.id.et_o_pass);
        np=(EditText)findViewById(R.id.et_n_pass);
        cp=(EditText)findViewById(R.id.et_c_pass);
        ef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SettingsActivity.this,EditProfile_Activity.class);
                startActivity(intent);
//                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Settings.setUserid(SettingsActivity.this,"-1","");
                Intent intent= new Intent(SettingsActivity.this,Home_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        cp_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            viewFlipper.setDisplayedChild(1);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String op_str = op.getText().toString();
                String np_str = np.getText().toString();
                String cp_str = cp.getText().toString();

                if (op_str.equals(""))
                    Toast.makeText(SettingsActivity.this, "Please enter old password", Toast.LENGTH_SHORT).show();
                else if (np_str.equals(""))
                    Toast.makeText(SettingsActivity.this, "Please enter new password", Toast.LENGTH_SHORT).show();
                else if (!np_str.equals(cp_str))
                    Toast.makeText(SettingsActivity.this, "Please enter same password", Toast.LENGTH_SHORT).show();
                else {
                    String url = Settings.SERVERURL + "change-password.php?";
                    try {
                        url = url + "member_id=" + URLEncoder.encode(Settings. getUserid(SettingsActivity.this), "utf-8") +"&opassword=" + URLEncoder.encode(op_str, "utf-8") +
                                "&password=" + URLEncoder.encode(np_str, "utf-8")+"&cpassword=" + URLEncoder.encode(cp_str, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.e("url--->", url);
                    final ProgressDialog progressDialog = new ProgressDialog(SettingsActivity.this);
                    progressDialog.setMessage("Please wait....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET ,url,null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            progressDialog.dismiss();
                            Log.e("response is: ", jsonObject.toString());
                            try {
                                String reply = jsonObject.getString("status");
                                if (reply.equals("Failed")) {
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(SettingsActivity.this, msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(SettingsActivity.this, msg, Toast.LENGTH_SHORT).show();
                                    viewFlipper.setDisplayedChild(0);
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
                            Toast.makeText(SettingsActivity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    });

// Access the RequestQueue through your singleton class.
                    AppController.getInstance().addToRequestQueue(jsonObjectRequest);

                }
            }

        });
    }
    @Override
    public void onBackPressed() {
       if(viewFlipper.getDisplayedChild()==1){
           viewFlipper.setDisplayedChild(0);
       }else{
           finish();
       }
    }

}
