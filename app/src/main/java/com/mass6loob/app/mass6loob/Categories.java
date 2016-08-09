package com.mass6loob.app.mass6loob;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chinni on 09-08-2016.
 */
public class Categories {
    String id,title,title_ar;
    Boolean isselected = false;
    Categories(JSONObject object){
        try {
            id = object.getString("id");
            title = object.getString("title");
            title_ar = object.getString("title_ar");
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
