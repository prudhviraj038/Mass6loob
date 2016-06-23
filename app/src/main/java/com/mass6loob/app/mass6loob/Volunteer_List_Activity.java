package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sriven on 6/22/2016.
 */
public class Volunteer_List_Activity extends Activity {
    ArrayList<User> users;
    VolunteerAdapter employees;
    ListView volunteerlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_list);
        volunteerlist = (ListView)findViewById(R.id.volunteer_list);
        volunteerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        employees = new VolunteerAdapter(this,users);
        volunteerlist.setAdapter(employees);

    }

}
