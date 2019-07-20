package com.chandan.touchinglives;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    private CardView AddActivity,AddAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

AddActivity=findViewById(R.id.addActivity);
AddAttendance=findViewById(R.id.addAttendance);
        AddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, AddActivity.class);
                startActivity(i);


            }
        });

        AddAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, AddAttendance.class);
                startActivity(i);

            }
        });




        }

}
