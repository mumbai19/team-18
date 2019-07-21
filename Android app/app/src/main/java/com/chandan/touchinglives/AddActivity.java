package com.chandan.touchinglives;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final TextView etActName,etDescription;
        Button btnAddAct;

etActName=findViewById(R.id.etActName);
etDescription=findViewById(R.id.etDescription);
btnAddAct=findViewById(R.id.btnAddAct);

btnAddAct.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String actname = etActName.getText().toString();
        String desc = etDescription.getText().toString();

        HashMap<String, String> map = new HashMap<>();
        map.put("a_name", actname);
        map.put("description", desc);
        RequestQueue queue = Volley.newRequestQueue(AddActivity.this);
        String url ="http://52.77.233.40:5000/addactivities";


// Request a string response from the provided URL.
//        Log.e("a", ""+map.get("uid"));
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(map),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Add Activity Response "," "+response);

                        Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error "+ error);

                    }
                }
        );
        queue.add(jor);


        Toast.makeText(getApplicationContext(),"Activity record added successfully.", Toast.LENGTH_SHORT).show();

    }
});


    }

}
