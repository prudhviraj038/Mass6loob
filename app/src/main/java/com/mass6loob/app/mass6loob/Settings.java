package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by HP on 6/23/2016.
 */
public class Settings {
    static String lan_key = "mass6loob_lan";
    public static final String SERVERURL = "http://clients.yellowsoft.in/ma6loob/api/";
    static String words_key = "danden_words";
    public static final String USERID = "massloob_id";
    public static final String NAME = "employee_name";
    static String Deli_charges="delivery_charge";

    public static void set_user_language(Context context,String user_id){

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(lan_key,user_id);
        editor.commit();
    }
    public static String get_lan(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(sharedPreferences.getString(lan_key,"en").equals("ar"))
        {
            return "_ar";
        }
        return "";
    }
    public static String get_user_language(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPreferences.getString(lan_key,"en");
    }
    public static void set_user_language_words(Context context,String user_id){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(words_key,user_id);
        editor.commit();
    }
    public static JSONObject get_user_language_words(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(sharedPreferences.getString(words_key,"-1"));
            jsonObject = jsonObject.getJSONObject(get_user_language(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static String getword(Context context,String word)
    {

        JSONObject words = get_user_language_words(context);

        try {
            return words.getString(word);
        } catch (JSONException e) {
            e.printStackTrace();
            return word;
        }
    }
    public static   void forceRTLIfSupported(Activity activity) {
        SharedPreferences sharedPref;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        Log.e("lan", sharedPref.getString(lan_key, "-1"));

        if (sharedPref.getString(lan_key, "-1").equals("en")) {
            Resources res = activity.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("en".toLowerCase());
            res.updateConfiguration(conf, dm);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }

        else if(sharedPref.getString(lan_key, "-1").equals("ar")){
            Resources res = activity.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("ar".toLowerCase());
            res.updateConfiguration(conf, dm);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }

        else {
            Resources res = activity.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("en".toLowerCase());
            res.updateConfiguration(conf, dm);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }

    }

    static SharedPreferences sharedPreferences;

    public static void set_Is_first_time(Context context, String deli_charges) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Deli_charges, deli_charges);
        editor.commit();
    }
    public static String get_Is_first_time(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(Deli_charges, "-1");
    }
    public static void setUserid(Context context, String member_id, String name) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERID, member_id);
        editor.putString(NAME, name);
        editor.commit();
    }

    public static String getUserid(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USERID, "-1");

    }
    public static void setSettings(Context context, String about_us) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("settings",about_us);
        editor.commit();
    }

    public static String getSettings(Context context,String word) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        JSONObject jsonObject;
        String temp;
        try {
            jsonObject=new JSONObject( sharedPreferences.getString("settings","-1"));
            temp=jsonObject.getString(word);
        } catch (JSONException e) {
            temp=word;
            e.printStackTrace();
        }

        return temp;
    }
    public static String getSettings_json(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        JSONObject jsonObject = null;
        String temp;
        try {
            jsonObject=new JSONObject( sharedPreferences.getString("settings","-1"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static void member_details(final Context context){
        String url = Settings.SERVERURL + "member-details.php?member_id="+Settings.getUserid(context);
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    if(jsonArray.length()!=0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        Settings.setSettings(context, jsonObject.toString());
                    }else{}
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(context, "Server not connected", Toast.LENGTH_SHORT).show();
                if(progressDialog!=null)
                    progressDialog.dismiss();

            }

        });

// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }
}
