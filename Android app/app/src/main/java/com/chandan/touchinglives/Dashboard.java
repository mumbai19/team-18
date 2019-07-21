package com.chandan.touchinglives;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import classes.JsonHelper;

public class Dashboard extends AppCompatActivity {
    private String LOCAL_URL= "http://a490268b.ngrok.io/getstudentdata";
    private CardView AddActivity,AddAttendance,AddSavings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

AddActivity=findViewById(R.id.addActivity);
AddAttendance=findViewById(R.id.addAttendance);
AddSavings=findViewById(R.id.addSavings);
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

                RequestQueue queue = Volley.newRequestQueue(Dashboard.this);
                String url = LOCAL_URL;
//retrieving from list
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("a","++++++++++++");
                                // Display the first 500 characters of the response string.
          //                      Toast.makeText(getBaseContext(),"Response is: "+ response,Toast.LENGTH_LONG).show();
                                json(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"That didn't work!" + error,Toast.LENGTH_LONG).show();
                    }
                });
queue.add(stringRequest);


                Intent i = new Intent(Dashboard.this, AddAttendance.class);
                startActivity(i);

            }
        });




        }

    public void json(String result) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            Toast.makeText(getBaseContext(), "NO NO NO -- " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }

        Log.e("a",jsonObject.toString());
        Map<String, Object> map = new HashMap();

        JsonHelper jsonHelper = new JsonHelper();
        try {

            map = jsonHelper.toMap(jsonObject);

//            for (int i=0;i<map.size();i++){
//
//                Student s=map.get;
//                sdata.add(s);




            // Locate ListView in listview_main.xml

            // Bind array strings into an adapter


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Toast.makeText(getBaseContext(), map.get("1").toString(),Toast.LENGTH_LONG).show();
        //  studentList.add((HashMap<String, String>) newmap);

    }


}
