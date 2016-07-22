package com.mass6loob.app.mass6loob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by sriven on 6/23/2016.
 */
public class Language_Activity extends  RootActivity {
    ImageView english,arabic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_lan);
        english = (ImageView)findViewById(R.id.image_english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.set_user_language(Language_Activity.this,"en");
                Settings.set_Is_first_time(Language_Activity.this,"en");
                Intent intent = new Intent(Language_Activity.this,Home_Activity.class);
                startActivity(intent);
                finish();

            }
        });
        arabic = (ImageView)findViewById(R.id.image_arabic);
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.set_user_language(Language_Activity.this,"ar");
                Settings.set_Is_first_time(Language_Activity.this, "ar");
                Intent intent = new Intent(Language_Activity.this,Home_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
