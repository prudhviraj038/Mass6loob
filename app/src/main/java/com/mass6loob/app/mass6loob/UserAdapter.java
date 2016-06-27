package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter{
    Context context;
    ArrayList<User> users;
    private static LayoutInflater inflater=null;
    public UserAdapter(Activity mainActivity, ArrayList<User> users) {
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

        ImageView userimage;
        TextView userid,jobtitle,experience,education,nationality,gender,location,description;
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
        holder.userimage=(ImageView) rowView.findViewById(R.id.user_image_ll);
        holder.userid=(TextView) rowView.findViewById(R.id.id_num_ll);
        holder.jobtitle=(TextView) rowView.findViewById(R.id.job_title_ll);
        holder.experience=(TextView) rowView.findViewById(R.id.experience_ll);
        holder.education=(TextView) rowView.findViewById(R.id.educatiom_ll);
        holder.nationality=(TextView) rowView.findViewById(R.id.nationality_ll);
        holder.gender=(TextView) rowView.findViewById(R.id.gender_ll);
        holder.location=(TextView) rowView.findViewById(R.id.location_ll);
        holder.description=(TextView) rowView.findViewById(R.id.description_ll);

         Picasso.with(context).load(users.get(position).userimage).into(holder.userimage);
         holder.userid.setText(users.get(position).userid);
         holder.jobtitle.setText(users.get(position).jobtitle);
         holder.experience.setText(users.get(position).experience);
         holder.education.setText(users.get(position).education);
         holder.nationality.setText(users.get(position).nationality);
         holder.gender.setText(users.get(position).gender);
         holder.location.setText(users.get(position).location);
         holder.description.setText(users.get(position).description);


        return rowView;
    }

}