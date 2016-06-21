package com.mass6loob.app.mass6loob;

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PDFdemo extends Activity {

    Button button1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1= (Button)findViewById(R.id.button);


        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/Solution_Guide.pdf");
                Log.e("path", file.toString());
                if (file.exists())
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.fromFile(file);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try
                    {
                        startActivity(intent);
                    }
                    catch(ActivityNotFoundException e)
                    {
                        Toast.makeText(PDFdemo .this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(PDFdemo .this, "File Not Found", Toast.LENGTH_LONG).show();
                }

            }

        });




    }

}

