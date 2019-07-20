package com.chandan.touchinglives;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.CheckNetworkStatus;
import classes.HttpJsonParser;

public class    MainActivity extends AppCompatActivity {

    Button button_login;
    EditText login_id, password;
 // private static final String BASE_URL = "";
    ProgressDialog pDialog;
    private int success;
String BASE_URL="http://52.77.233.40:5000/";


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

        Map<String,String> map = new HashMap<>();
        map.put("uid",log_id);
        map.put("pwd",pwd);
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL+"program","POST",map);
        /*if(success==0){

            Toast.makeText(getApplicationContext(),"User registered successfully", Toast.LENGTH_SHORT).show();




        }*/




    }
});

    }}
