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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText register_id,password1;
        Button register;
       register = findViewById(R.id.button_register);
    register_id=findViewById(R.id.register_id);
    password1=findViewById(R.id.password1);


    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Toast.makeText(getApplicationContext(),"User registered successfully", Toast.LENGTH_SHORT).show();

        }
    });

    }

}
