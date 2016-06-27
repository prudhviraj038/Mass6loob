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
public class Freelancer_List_Activity extends Activity {
    ArrayList<Freelancer> freelancers;
    FreelancerAdapter employees;
    ListView users_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);

        setContentView(R.layout.freelancer_list);

        users_list = (ListView)findViewById(R.id.lancer_list_view);
        users_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        employees = new FreelancerAdapter(this,freelancers);
        users_list.setAdapter(employees);

    }

}
