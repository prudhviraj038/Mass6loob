package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FreelancerAdapter extends BaseAdapter{
    Context context;
    TextView accept_tv;
    LinearLayout accept;
    int posi;
    ArrayList<Freelancer> freelancers;
    private static LayoutInflater inflater=null;
    public FreelancerAdapter(Activity mainActivity, ArrayList<Freelancer> freelancers) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.freelancers=freelancers;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return freelancers.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {

        ImageView userimage;
        TextView name,field,experience,gender;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.results_freelancers, parent,false);
        else
        rowView = convertView;
        posi=position;

        holder.userimage=(ImageView) rowView.findViewById(R.id.userimage_l);
        holder.name=(TextView) rowView.findViewById(R.id.name);
        holder.field=(TextView) rowView.findViewById(R.id.field);
        holder.experience=(TextView) rowView.findViewById(R.id.exp);
        holder.gender=(TextView) rowView.findViewById(R.id.gender);
        accept_tv=(TextView) rowView.findViewById(R.id.accept_free_tv);
        accept=(LinearLayout) rowView.findViewById(R.id.accept_free_ll);
      //   Picasso.with(context).load(users.get(position).image).into(holder.userimage);
         holder.name.setText(freelancers.get(position).name);
         holder.field.setText(freelancers.get(position).field);
         holder.experience.setText(freelancers.get(position).exp);
         holder.gender.setText(freelancers.get(position).gender);
        accept.setVisibility(View.VISIBLE);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

            }
        });
        return rowView;
    }
    public  void  submit(){
        String url = Settings.SERVERURL + "selected-freelancer.php?member_id="+Settings.getUserid(context)+"&freelancer_id="+freelancers.get(posi).id;
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("message");
                    if(status.equals("Failed")){
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                        accept_tv.setText("ACCEPTED");
                        accept.setVisibility(View.GONE);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(context, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}