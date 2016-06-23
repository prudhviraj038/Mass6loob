package com.mass6loob.app.mass6loob;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class User implements Serializable {
    String userid,jobtitle,experience,education,nationality,gender,location,description,userimage;

    User(JSONObject object){
        try {
            userid = object.getString("id no");
            jobtitle = object.getString("jobtitle");
            experience=object.getString("experience");
            education=object.getString("education");
            nationality=object.getString("nationality");
            gender=object.getString("gender");
            location=object.getString("location");
            description=object.getString("description");
            userimage = object.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
