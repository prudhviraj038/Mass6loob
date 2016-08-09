package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolunteerCatgoryAdapter extends BaseAdapter {
    Context context;
    HashMap<String,String> cate;
    ArrayList<Categories> categories;
    private static LayoutInflater inflater=null;
    public VolunteerCatgoryAdapter(Activity mainActivity, ArrayList<Categories> categories) {
        // TODO Auto-generated constructor stu
        cate=new HashMap<>();
        context=mainActivity;
        this.categories=categories;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return categories.size();
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
        TextView name;
        LinearLayout check_ll;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)     {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        if(convertView==null)
            rowView = inflater.inflate(R.layout.volunteer_category_item, parent,false);
        else
            rowView = convertView;
        holder.userimage=(ImageView) rowView.findViewById(R.id.chech_box);
        holder.userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check_ll.performClick();
            }
        });
        holder.name=(TextView) rowView.findViewById(R.id.name1);
        holder.check_ll=(LinearLayout)rowView.findViewById(R.id.check_ll);
        holder.name.setText(categories.get(position).get_title(context));
        holder.check_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cate.containsKey(String.valueOf(position))) {
                    cate.remove(String.valueOf(position));
                    holder.userimage.setImageResource(R.drawable.check_icon);
                }
                else {
                    cate.put(String.valueOf(position), categories.get(position).id);
                    holder.userimage.setImageResource(R.drawable.tick_icon);
                }
            }
        });


        return rowView;
    }
    public String get_cate(){
        String csv = "-1";
        for (Map.Entry<String, String> entry : cate.entrySet()) {
            if (csv.equals("-1"))
                csv = entry.getValue();
            else
                csv = csv + "," + entry.getValue();
        }
        return  csv;
    }
}