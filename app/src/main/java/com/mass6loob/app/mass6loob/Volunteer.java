package com.mass6loob.app.mass6loob;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Volunteer implements Serializable {
    String company,established,gender,catagory,volunteerimage;

    Volunteer(JSONObject object){
        try {

           company=object.getString("company");
            established=object.getString("established");
            gender=object.getString("gender");
           catagory=object.getString("catagory");
            volunteerimage = object.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
