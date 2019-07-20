package com.chandan.touchinglives;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        TextView etActName,etDescription;
        Button btnAddAct;

etActName=findViewById(R.id.etActName);
etDescription=findViewById(R.id.etDescription);
btnAddAct=findViewById(R.id.btnAddAct);

btnAddAct.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"Activity record added successfully.", Toast.LENGTH_SHORT).show();

    }
});


    }

}
