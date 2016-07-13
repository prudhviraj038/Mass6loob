package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sriven on 6/22/2016.
 */
public class Users_List_Activity extends  RootActivity {
    ArrayList<User> users;
    UserAdapter employees;
    ListView userslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userslist);
        userslist = (ListView)findViewById(R.id.list_view);
        userslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        employees = new UserAdapter(this,users);
        userslist.setAdapter(employees);

    }

}
