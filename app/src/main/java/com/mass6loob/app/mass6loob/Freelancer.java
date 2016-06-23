package com.mass6loob.app.mass6loob;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Freelancer implements Serializable {
    String name,field,experience,gender,image;

    Freelancer(JSONObject object){
        try {
           field = object.getString("field");
            name = object.getString("name");
            experience=object.getString("experience");
            gender=object.getString("gender");
            image = object.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
