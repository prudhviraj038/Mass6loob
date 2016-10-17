package com.mass6loob.app.mass6loob;

import android.content.Context;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class User implements Serializable {
    String userid,jobtitle,status,experience,bachelors,masters,nationality,gender,location,description,userimage,update;

    User(JSONObject object,Context context){
        try {
            userid = object.getString("id");
            jobtitle = object.getString("title");
            userimage = object.getString("image");
            status = object.getString("status");
            experience=object.getJSONObject("experience").getString("title");
            nationality=object.getString("nationality");
            bachelors=object.getJSONObject("bachelors").getString("title");
            masters=object.getJSONObject("masters").getString("title");
            gender=object.getString("gender");
            location=object.getString("location");
            description=object.getString("description");
            userimage = object.getString("image");
            update = object.getString("updated");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
