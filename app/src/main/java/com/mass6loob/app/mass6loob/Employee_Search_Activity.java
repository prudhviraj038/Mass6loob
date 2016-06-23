package com.mass6loob.app.mass6loob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sriven on 6/21/2016.
 */
public class Employee_Search_Activity extends Activity {
    EditText jobtitle,experience,nationality,education;
    LinearLayout search;
    String title_str,expe_str,nation_str,edu_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_search);
        jobtitle = (EditText)findViewById(R.id.emp_search_title);
        experience = (EditText)findViewById(R.id.emp_search_exp);
        nationality = (EditText)findViewById(R.id.emp_search_nationality);
        education = (EditText)findViewById(R.id.emp_search_edu);
        search = (LinearLayout)findViewById(R.id.emp_search_ll);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_str = jobtitle.getText().toString();
                expe_str = experience.getText().toString();
                nation_str = nationality.getText().toString();
                edu_str = nationality.getText().toString();
                 if(title_str.equals("")){
                    Toast.makeText(Employee_Search_Activity.this, "please enter jobtitle", Toast.LENGTH_SHORT).show();
                }
                else if(expe_str.equals("")){
                    Toast.makeText(Employee_Search_Activity.this, "please enter experience", Toast.LENGTH_SHORT).show();
                }
                else if(nation_str.equals("")){
                    Toast.makeText(Employee_Search_Activity.this, "please select ur nationality", Toast.LENGTH_SHORT).show();
                }
                 else if(edu_str.equals("")){
                     Toast.makeText(Employee_Search_Activity.this, "please select ur education", Toast.LENGTH_SHORT).show();
                 }
                Intent intent = new Intent(Employee_Search_Activity.this,Users_List_Activity.class);
                startActivity(intent);


            }
        });
    }

}
