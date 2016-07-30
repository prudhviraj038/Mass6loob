package com.mass6loob.app.mass6loob;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mass6loob.app.mass6loob.AndroidMultiPartEntity.ProgressListener;

import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class UploadActivity extends ActionBarActivity {
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private String filePath = null;
    private TextView txtPercentage,image_stat;
    private ImageView imgPreview;
    private VideoView vidPreview;
    private Button btnUpload;
    private String emp_id;
    //HashMap<Integer,String> file_map = new HashMap<>();
    String file_path="";
    long totalSize = 0;
    int current = 1;
    int total =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ba2025")));
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        image_stat = (TextView) findViewById(R.id.image_status);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
      //  vidPreview = (VideoView) findViewById(R.id.videoPreview);
        // Receiving the data from previous activity
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        file_path = (String)i.getSerializableExtra("file_path");
        emp_id = i.getStringExtra("emp_id");

        // boolean flag to identify the media type, image or video


       // total = file_map.size();
       // new UploadFileToServer().execute();
      //  upload_with_ion();

    }

    /**
     * Displaying captured image/video on the screen
     * */
    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imgPreview.setVisibility(View.VISIBLE);
            vidPreview.setVisibility(View.GONE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

            imgPreview.setImageBitmap(bitmap);
        } else {
            imgPreview.setVisibility(View.GONE);
            vidPreview.setVisibility(View.VISIBLE);
            vidPreview.setVideoPath(filePath);
            // start playing
            vidPreview.start();
        }
    }

    /**
     * Uploading the file to server
     * */
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            progressBar.setProgress(0);
            image_stat.setText("Uploading file "+current+" of "+ total);
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible
            progressBar.setVisibility(View.VISIBLE);

            // updating progress bar value
            progressBar.setProgress(progress[0]);

            // updating percentage value
            txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }


        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Settings.SERVERURL+ "login.php");
            Log.e("cv ",Settings.SERVERURL+ "login.php");

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(file_path);
                // Adding file data to http body
              //  entity.addPart("resume", new FileBody(sourceFile));
             //   entity.addPart("employee_id", new StringBody(emp_id));

                entity.addPart("email", new StringBody("hii"));
                entity.addPart("password", new StringBody("hii"));
                Log.e("email", emp_id);
               // entity.addPart("member_id", new StringBody(Settings.getUserid(UploadActivity.this)));
             //   entity.addPart("file_count", new StringBody(String.valueOf(current)));
                totalSize = entity.getContentLength();
                Log.e("totalsize",String.valueOf(totalSize));
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(TAG, "Response from server: " + result);
            Intent intent= new Intent(UploadActivity.this,Employee_Search_Activity.class);
            startActivity(intent);
//            current++;
//            if(current<=total)
//            {
//                new UploadFileToServer().execute();
//            }
//            else{
//                showAlert("Your post uploaded sucessfully!!!");
//            }


        }

    }

    /**
     * Method to show alert dialog
     * */
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                                finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void goto_home(View view){

        Intent intent = new Intent(UploadActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void goto_add(View view){

        //Intent intent = new Intent(UploadActivity.this,MainActivity.class);
        //startActivity(intent);
        finish();
    }



}

