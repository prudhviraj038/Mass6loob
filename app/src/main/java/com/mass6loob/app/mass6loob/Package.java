package com.mass6loob.app.mass6loob;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HP on 12-Oct-16.
 */
public class Package {
    String id,title,title_ar,credits,price;
    Package(JSONObject jsonObject){
        try {
            id=jsonObject.getString("id");
            title=jsonObject.getString("title");
            title_ar=jsonObject.getString("title_ar");
            credits=jsonObject.getString("credits");
            price=jsonObject.getString("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String get_title(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
            return title_ar;
        else
            return  title;
    }
}
