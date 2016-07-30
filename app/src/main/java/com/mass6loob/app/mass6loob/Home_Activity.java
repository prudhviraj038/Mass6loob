package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by sriven on 6/21/2016.
 */
public class Home_Activity extends RootActivity {
    LinearLayout recruit, postmycv;
    LinearLayout register, postmycv1;
    LinearLayout volunterregister, volunteercompanyregister;
    RelativeLayout slidingpanel;
    LinearLayout jobs_view,free_view,vol_view;
    DisplayMetrics metrics = new DisplayMetrics();

    int pos=0;
    TextView heading,sub_heading;

    private void set_heading(){
        if(pos==0) {
            heading.setText("Searching for Job?");
            sub_heading.setText("We Made it Simple!");
        }else if(pos == -1){
            heading.setText("Searching for Volunters?");
            sub_heading.setText("We Made it Simple!");

        }else if(pos ==1){
            heading.setText("Searching for Freelancers?");
            sub_heading.setText("We Made it Simple!");

        }
    }
    int distance;
    float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.forceRTLIfSupported(this);
        setContentView(R.layout.home_page);

         distance = 300;
         scale = getResources().getDisplayMetrics().density;
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d("ApplicationTagName", "Display width in px is " + metrics.widthPixels);
        slidingpanel = (RelativeLayout) findViewById(R.id.sliding_layer);
        jobs_view = (LinearLayout) findViewById(R.id.jobs_view);
        free_view = (LinearLayout) findViewById(R.id.free_view);
        vol_view = (LinearLayout) findViewById(R.id.volunteers_view);
        heading = (TextView) findViewById(R.id.heading);
        sub_heading = (TextView) findViewById(R.id.sub_heading);
        recruit = (LinearLayout) findViewById(R.id.recruit_ll);
        recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Settings.getUserid(Home_Activity.this).equals("-1"))
                {
                    Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(),Company_Register_Activity.class);
                    mainIntent.putExtra("uid",Settings.getUserid(Home_Activity.this));
                    startActivity(mainIntent);

                }

            }
        });
        postmycv = (LinearLayout) findViewById(R.id.post_my_cv_ll);
        postmycv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Settings.getUserid(Home_Activity.this).equals("-1"))
                {
                    Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(),Employee_Register_Activity.class);
                    mainIntent.putExtra("uid",Settings.getUserid(Home_Activity.this));
                    startActivity(mainIntent);

                }
            }
        });
        register = (LinearLayout) findViewById(R.id.lancer_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Settings.getUserid(Home_Activity.this).equals("-1"))
                {
                    Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(),Freelancer_Client_Register_Activity.class);
                    mainIntent.putExtra("uid",Settings.getUserid(Home_Activity.this));
                    startActivity(mainIntent);

                }
            }
        });
        postmycv1 = (LinearLayout) findViewById(R.id.post_my_cv);
        postmycv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Settings.getUserid(Home_Activity.this).equals("-1"))
                {
                    Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(),Freelancer_Register_Activity.class);
                    mainIntent.putExtra("uid",Settings.getUserid(Home_Activity.this));
                    startActivity(mainIntent);

                }
            }
        });
        volunterregister = (LinearLayout) findViewById(R.id.volunteer_register);
        volunterregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Settings.getUserid(Home_Activity.this).equals("-1"))
                {
                    Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(),Volunteer_Register_Activity.class);
                    mainIntent.putExtra("uid",Settings.getUserid(Home_Activity.this));
                    startActivity(mainIntent);

                }
            }
        });
        volunteercompanyregister = (LinearLayout) findViewById(R.id.volunteer_postcv);
        volunteercompanyregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Settings.getUserid(Home_Activity.this).equals("-1"))
                {
                    Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(),Volunteer_Company_Register_Activity.class);
                    mainIntent.putExtra("uid",Settings.getUserid(Home_Activity.this));
                    startActivity(mainIntent);

                }
            }
        });

        setonTounch(slidingpanel);
            jobX=jobs_view.getX();
        jobY = jobs_view.getY();
        Log.e("jobx",String.valueOf(jobs_view.getX()));
        Log.e("joby", String.valueOf(jobs_view.getY()));

        jobs_view.animate()
                .x(metrics.xdpi / 2)
                .y(jobs_view.getY())
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(500)
                .start();
        jobs_view.bringToFront();
        free_view.animate()
                .x(0 - 100)
                .y(free_view.getY())
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(500)
                .start();

        vol_view.animate()
                .x(metrics.xdpi + 100)
                .y(vol_view.getY())
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(500)
                .start();


        pos = 0;
            set_heading();

    }

    public float baseX, baseY;
    public float jobX, jobY;

    float dX, dY;
    private void setonTounch(final View dragger ) {

        View.OnTouchListener listener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        baseX =  event.getRawX();
                        baseY =  event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                       /* dX = baseX-event.getRawX();
                        Log.e("dis_swiped",String.valueOf(dX));
                        if(jobs_view.getX() > -250 && jobs_view.getX() < metrics.widthPixels-250) {
                            jobs_view.animate()
                                    .x(jobs_view.getX()-dX/2)
                                    .y(jobs_view.getY())
                                    .setDuration(0)
                                    .start();
                        }
                        else{

                        }
                        */
                        break;
                    case MotionEvent.ACTION_UP:
                        if(Math.abs(baseX-event.getRawX())>20) {

                            if (baseX > event.getRawX()) {
                                if (pos == 0) {
                                    jobs_view.animate()
                                            .x(0 - 100)
                                            .y(jobs_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    free_view.animate()
                                            .x(metrics.xdpi + 100)
                                            .y(free_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    vol_view.animate()
                                            .x(metrics.xdpi / 2)
                                            .y(vol_view.getY())
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(500)
                                            .start();
                                  //  smallBang.bang(vol_view);

                                    vol_view.bringToFront();

                                    pos = -1;
                                    set_heading();

                                } else if (pos == 1) {
                                    jobs_view.animate()
                                            .x(metrics.xdpi / 2)
                                            .y(jobs_view.getY())
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(500)
                                            .start();
                                    jobs_view.bringToFront();
                                    free_view.animate()
                                            .x(0 - 100)
                                            .y(free_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    vol_view.animate()
                                            .x(metrics.xdpi + 100)
                                            .y(vol_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();


                                    pos = 0;
                                    set_heading();

                                }else if(pos==-1){
                                    vol_view.animate()
                                            .x(0 - 100)
                                            .y(vol_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    jobs_view.animate()
                                            .x(metrics.xdpi + 100)
                                            .y(jobs_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    free_view.animate()
                                            .x(metrics.xdpi / 2)
                                            .y(free_view.getY())
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(500)
                                            .start();
                                    free_view.bringToFront();
                                            pos=1;
                                    set_heading();

                                }
                            } else {

                                if (pos == 0) {
                                    vol_view.animate()
                                            .x(0 - 100)
                                            .y(jobs_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    jobs_view.animate()
                                            .x(metrics.xdpi + 100)
                                            .y(free_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    free_view.animate()
                                            .x(metrics.xdpi / 2)
                                            .y(vol_view.getY())
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(500)
                                            .start();
                                    free_view.bringToFront();
                                    pos = 1;
                                    set_heading();

                                } else if (pos == 1) {
                                    vol_view.animate()
                                            .x(metrics.xdpi / 2)
                                            .y(jobs_view.getY())
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(500)
                                            .start();
                                    vol_view.bringToFront();
                                    jobs_view.animate()
                                            .x(0 - 100)
                                            .y(free_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    free_view.animate()
                                            .x(metrics.xdpi + 100)
                                            .y(vol_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();


                                    pos = -1;
                                    set_heading();

                                }else if(pos==-1){
                                    free_view.animate()
                                            .x(0 - 100)
                                            .y(vol_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    vol_view.animate()
                                            .x(metrics.xdpi + 100)
                                            .y(jobs_view.getY())
                                            .scaleX(0.5f)
                                            .scaleY(0.5f)
                                            .setDuration(500)
                                            .start();
                                    jobs_view.animate()
                                            .x(metrics.xdpi / 2)
                                            .y(free_view.getY())
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(500)
                                            .start();
                                    jobs_view.bringToFront();
                                    pos=0;
                                    set_heading();

                                }

                            }
                        }
                            break;

                    default:
                        return false;
                }
                return true;
            }
        };

    dragger.setOnTouchListener(listener);
    }

    }
