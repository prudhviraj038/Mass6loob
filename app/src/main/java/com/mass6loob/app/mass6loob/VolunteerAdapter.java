package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VolunteerAdapter extends BaseAdapter{
    Context context;
    ArrayList<User> users;
    private static LayoutInflater inflater=null;
    public VolunteerAdapter(Activity mainActivity, ArrayList<User> users) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.users=users;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 10;
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
        holder.volunteerimage=(ImageView) rowView.findViewById(R.id.volunteer_image);
        holder.company=(TextView) rowView.findViewById(R.id.volunteer_comp);
        holder.established=(TextView) rowView.findViewById(R.id.volunteer_estd);
        holder.catagory=(TextView) rowView.findViewById(R.id.volunteer_ctgry);
        holder.gender=(TextView) rowView.findViewById(R.id.volunteer_gender);


      //   Picasso.with(context).load(users.get(position).image).into(holder.userimage);
       //  holder.name.setText(users.get(position).company);
        // holder.field.setText(users.get(position).established);
        // holder.experience.setText(users.get(position).catagory);
        // holder.name.setText(users.get(position).gender);

        return rowView;
    }

}