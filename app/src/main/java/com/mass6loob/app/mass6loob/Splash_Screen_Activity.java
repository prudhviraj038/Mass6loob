package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by sriven on 6/23/2016.
 */
public class Splash_Screen_Activity extends  RootActivity {
    ImageView english,arabic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        english = (ImageView)findViewById(R.id.image_english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.set_user_language(Splash_Screen_Activity.this,"en");
                Intent intent = new Intent(Splash_Screen_Activity.this,Home_Activity.class);
                startActivity(intent);
            }
        });
        arabic = (ImageView)findViewById(R.id.image_arabic);
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.set_user_language(Splash_Screen_Activity.this,"ar");
                Intent intent = new Intent(Splash_Screen_Activity.this,Home_Activity.class);
                startActivity(intent);
            }
        });
    }
}
