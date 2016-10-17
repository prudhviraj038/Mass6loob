package com.mass6loob.app.mass6loob;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sriven on 5/26/2016.
 */
public class Volunteer implements Serializable {
    String id,company,established,area,catagory,volunteerimage,intrested;
    ArrayList<Category> categories;
    Volunteer(JSONObject object){
        this.categories = new ArrayList<>();
        try {
            id=object.getString("id");
           company=object.getString("name");
            established=object.getString("established");
            area=object.getString("area");
            intrested=object.getString("intrested");
//            volunteerimage = object.getString("image");
            for (int i = 0; i < object.getJSONArray("category").length(); i++) {
                try {
                    categories.add(new Category(object.getJSONArray("category").getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public class Category implements java.io.Serializable {
        public  String id,title, title_ar;

        Category(JSONObject jsonObject) {
            try {
                id=jsonObject.getString("id");
                title=jsonObject.getString("title");
                title_ar=jsonObject.getString("title_ar");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public String get_title(Context context) {
            if(Settings.get_user_language(context).equals("ar"))
                return title_ar;
            else
                return title;
        }
    }
}
