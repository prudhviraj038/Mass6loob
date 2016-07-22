package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by sriven on 6/23/2016.
 */
public class Splash_Screen_Activity extends  RootActivity {
    ImageView english,arabic;
    TextView skip;
    Handler handler;
    Runnable runnable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        skip = (TextView)findViewById(R.id.skip);
        //english = (ImageView)findViewById(R.id.image_english);
        handler = new Handler();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
       // ImageView imageView=(ImageView)findViewById(R.id.splash);
        //imageView.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation));
         handler.postDelayed(runnable,3000);
        runnable=new Runnable() {
            @Override
            public void run() {newone();
            }
        };
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                newone();
            }
        });
    }
    public  void newone(){
        String abc= Settings.get_Is_first_time(Splash_Screen_Activity.this);
        Log.e("lng", abc);
        if(abc.equals("-1")) {
            Intent mainIntent = new Intent(getApplicationContext(), Language_Activity.class);
            startActivity(mainIntent);
            finish();
        }
        else {
            Intent mainIntent = new Intent(getApplicationContext(), Home_Activity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
