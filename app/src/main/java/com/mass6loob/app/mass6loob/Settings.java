package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.Locale;

/**
 * Created by HP on 6/23/2016.
 */
public class Settings {
    static String lan_key = "mass6loob_lan";

    public static void set_user_language(Context context,String user_id){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(lan_key,user_id);
        editor.commit();
    }

    public static String get_user_language(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPreferences.getString(lan_key,"en");
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


}
