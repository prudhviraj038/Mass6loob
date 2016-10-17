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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolunteerAdapter extends BaseAdapter{
    Context context;
    String temp;
    int posi;
    TextView accept_tv;
    ArrayList<Volunteer> users;
    private static LayoutInflater inflater=null;
    public VolunteerAdapter(Activity mainActivity, ArrayList<Volunteer> users) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.users=users;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return users.size();
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

        ImageView volunteerimage;
        TextView company,established,gender,catagory;
        LinearLayout accept;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.results_volunteer_company, parent,false);
        else
        rowView = convertView;
        posi=position;
        holder.volunteerimage=(ImageView) rowView.findViewById(R.id.volunteer_image);
        holder.company=(TextView) rowView.findViewById(R.id.volunteer_comp);
        holder.established=(TextView) rowView.findViewById(R.id.volunteer_estd);
        holder.catagory=(TextView) rowView.findViewById(R.id.volunteer_ctgry);
        holder.gender=(TextView) rowView.findViewById(R.id.volunteer_gen);

        accept_tv=(TextView) rowView.findViewById(R.id.accept_v_tv);
        holder.accept=(LinearLayout) rowView.findViewById(R.id.accept_v_ll);
//         Picasso.with(context).load(users.get(position).image).into(holder.userimage);
         holder.company.setText(users.get(position).company);
         holder.established.setText(users.get(position).established);
        holder.gender.setText(users.get(position).intrested);
        for (int i=0;i<users.get(position).categories.size();i++){

                String temp1=users.get(position).categories.get(i).get_title(context);
                Log.e("cat", temp1);
            if(i==0){
                temp=temp1;
            }else{
                temp=temp+" ,"+temp1;
            }
            holder.catagory.setText(temp);
        }
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

            }
        });
        return rowView;
    }
    public  void  submit(){
        String url = Settings.SERVERURL + "selected-volunteer.php?member_id="+Settings.getUserid(context)+"&company_id="+users.get(posi).id;
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