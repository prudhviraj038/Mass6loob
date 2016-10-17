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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PackageAdapter extends BaseAdapter{
    Context context;
    ArrayList<Package> users;
    private static LayoutInflater inflater=null;
    public PackageAdapter(Activity mainActivity, ArrayList<Package> users) {
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

        ImageView userimage;
        TextView credit_price,credit_title;
        LinearLayout accept;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.package_item, parent,false);
        else
        rowView = convertView;
        holder.userimage=(ImageView) rowView.findViewById(R.id.user_image_ll);
        holder.credit_price=(TextView) rowView.findViewById(R.id.credit_price);
        holder.credit_title=(TextView) rowView.findViewById(R.id.credit_title);
        holder.credit_price.setText(users.get(position).price+" KD");
        Log.e("jhhhh",users.get(position).get_title(context));
        holder.credit_title.setText(users.get(position).title);
        return rowView;
    }

}