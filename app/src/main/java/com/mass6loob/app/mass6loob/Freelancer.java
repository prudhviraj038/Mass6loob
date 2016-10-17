package com.mass6loob.app.mass6loob;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Freelancer implements Serializable {
    String id,name,field,email,phone,age,gender,image,nationality,exp;

    Freelancer(JSONObject object){
        try {
            id = object.getString("id");
            field = object.getString("field");
            name = object.getString("name");
            email = object.getString("email");
            phone = object.getString("phone");
            age=object.getString("age");
            gender=object.getString("gender");
            nationality=object.getString("nationality");
            exp = object.getJSONObject("experience").getString("title");
            image = object.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
