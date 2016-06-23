package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/21/2016.
 */
public class Home_Activity extends Activity {
    LinearLayout recruit,postmycv;
    LinearLayout register,postmycv1;
    LinearLayout volunterregister,volunteercompanyregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.home_page);
        recruit = (LinearLayout)findViewById(R.id.recruit_ll);
        recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Company_Register_Activity.class);
                startActivity(intent);

            }
        });
        postmycv = (LinearLayout)findViewById(R.id.post_my_cv_ll);
        postmycv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Employee_Register_Activity.class);
                startActivity(intent);

            }
        });
        register = (LinearLayout)findViewById(R.id.lancer_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Freelancer_Register_Activity.class);
                startActivity(intent);
            }
        });
        postmycv1=(LinearLayout)findViewById(R.id.post_my_cv);
        postmycv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Freelancer_Client_Register_Activity.class);
                startActivity(intent);
            }
        });
        volunterregister = (LinearLayout)findViewById(R.id.volunteer_register);
        volunterregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Volunteer_Register_Activity.class);
                startActivity(intent);
            }
        });
        volunteercompanyregister=(LinearLayout)findViewById(R.id.volunteer_postcv);
        volunteercompanyregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Volunteer_Company_Register_Activity.class);
                startActivity(intent);
            }
        });


    }
}
