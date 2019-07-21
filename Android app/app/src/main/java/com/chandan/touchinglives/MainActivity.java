package com.chandan.touchinglives;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class    MainActivity extends AppCompatActivity {

    Button button_login;
    EditText login_id, password;
 // private static final String BASE_URL = "";
    ProgressDialog pDialog;
    private int success;
String BASE_URL="http://52.77.233.40:5000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView;
        login_id = (EditText) findViewById(R.id.login_id);
        password = (EditText) findViewById(R.id.password);
        button_login = findViewById(R.id.button_login);
   //Intent i =new Intent(MainActivity.this,Dashboard.class);
   //startActivity(i);
button_login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String log_id = login_id.getText().toString();
        String pwd = password.getText().toString();

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", log_id);
        map.put("pwd", pwd);


//        JSONObject map = new JSONObject();
//        try {
//            map.put("uid",log_id);
//            map.put("pwd",pwd);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        HttpJsonParser httpJsonParser = new HttpJsonParser();
//        Log.e("a","Hi");
//        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
//                BASE_URL,"POST",map);
//        Log.e("a","Hi "+jsonObject);
//    Log.e("a","s "+success);
//        if(success==1) {
//
//            Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
//
//
//        }
//
//
//
//
//

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url ="http://52.77.233.40:5000/login";


// Request a string response from the provided URL.
//        Log.e("a", ""+map.get("uid"));
            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(map),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("a"," "+response);

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

    }

    });

    }}
