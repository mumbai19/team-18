package com.chandan.touchinglives;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRemoveStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_student);

        EditText et1,et2;
        Button btnAddAct;
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        btnAddAct=findViewById(R.id.btnAddAct);


        btnAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Student Added Successfully" ,Toast.LENGTH_SHORT).show();
            }
        });




    }

}
