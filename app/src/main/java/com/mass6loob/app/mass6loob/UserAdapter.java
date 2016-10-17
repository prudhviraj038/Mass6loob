package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter{
    Context context;
    int posi;
    LinearLayout accept,accept_g;
    TextView accept_tv,accept_g_tv;
    ArrayList<User> users;
    Users_List_Activity users_list_activity;
    private static LayoutInflater inflater=null;
    public UserAdapter(Activity mainActivity, ArrayList<User> users, Users_List_Activity users_list_activity) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.users=users;
        this.users_list_activity=users_list_activity;
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

        ImageView userimage;
        TextView userid,jobtitle,experience,education,nationality,gender,location,description,update;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.search_for_cv, parent,false);
        else
        rowView = convertView;
        posi=position;
        holder.userimage=(ImageView) rowView.findViewById(R.id.user_image_ll);
        holder.userid=(TextView) rowView.findViewById(R.id.id_num_ll);
        holder.jobtitle=(TextView) rowView.findViewById(R.id.job_title_ll);
        holder.experience=(TextView) rowView.findViewById(R.id.experience_ll);
        holder.education=(TextView) rowView.findViewById(R.id.educatiom_ll);
        holder.nationality=(TextView) rowView.findViewById(R.id.nationality_ll);
        holder.gender=(TextView) rowView.findViewById(R.id.gender_ll);
        holder.location=(TextView) rowView.findViewById(R.id.location_ll);
        holder.description=(TextView) rowView.findViewById(R.id.description_ll);
        holder.update=(TextView) rowView.findViewById(R.id.update_last);
        accept_tv=(TextView) rowView.findViewById(R.id.accept_tv);
        accept=(LinearLayout) rowView.findViewById(R.id.accept_res);
        accept_g_tv=(TextView) rowView.findViewById(R.id.acc_green_tv);
        accept_g=(LinearLayout) rowView.findViewById(R.id.acc_green_ll);
        Log.e("image", users.get(position).userid);
         Picasso.with(context).load(users.get(position).userimage).into(holder.userimage);
         holder.userid.setText(users.get(position).userid);
         holder.jobtitle.setText(users.get(position).jobtitle);
         holder.experience.setText(users.get(position).experience);
         holder.education.setText(users.get(position).bachelors+","+users.get(position).masters);
         holder.nationality.setText(users.get(position).nationality);
         holder.gender.setText(users.get(position).gender);
         holder.location.setText(users.get(position).location);
         holder.description.setText(users.get(position).description);
        holder.update.setText(users.get(position).update);
//        accept.setVisibility(View.VISIBLE);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_credit();

            }
        });
        if(users.get(position).status.equals("0")){
            accept_g.setVisibility(View.GONE);
            accept.setVisibility(View.VISIBLE);
        }else{
            accept.setVisibility(View.GONE);
            accept_g.setVisibility(View.VISIBLE);
        }
        return rowView;
    }
    public  void  get_credit(){
        String url = Settings.SERVERURL + "balance-credits.php?member_id="+Settings.getUserid(context);
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
                        String cr = jsonObject.getString("balance_credits");
                    if(!cr.equals("0")){
                        send_resume();
                    }else{
                        users_list_activity.get_package();
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
    public  void  send_resume(){
        String url = Settings.SERVERURL + "add-credit.php?member_id="+Settings.getUserid(context)+"&resume_id="+users.get(posi).userid;
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
                        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                        users_list_activity.get_list();

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