package com.chandan.touchinglives;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AmoutForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amout_form);
        EditText etAmountAdd;
        Button btnAddAmt;
        etAmountAdd=findViewById(R.id.etAmountAdd);
        btnAddAmt=findViewById(R.id.btnAddAmt);

        btnAddAmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Amount Added" ,Toast.LENGTH_SHORT).show();


            }
        });

    }
}
