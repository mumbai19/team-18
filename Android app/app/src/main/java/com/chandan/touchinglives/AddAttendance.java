package com.chandan.touchinglives;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.Map;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import classes.JsonHelper;
import android.widget.ArrayAdapter;import android.widget.ListView;




public class AddAttendance extends AppCompatActivity {
private ListView simpleList;
    private String LOCAL_URL= "http://a490268b.ngrok.io/studentdata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = (ListView) findViewById(R.id.listView1);

        setContentView(R.layout.activity_add_attendance);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = LOCAL_URL;
//retrieving from list
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getBaseContext(),"Response is: "+ response,Toast.LENGTH_LONG).show();
                        json(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),"That didn't work!" + error,Toast.LENGTH_LONG).show();
            }
        });





    }
    public void json(String result){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            Toast.makeText(getBaseContext(),"NO NO NO -- " + e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }


        Map<String,Object> map = new HashMap<>();
        JsonHelper jsonHelper = new JsonHelper();
        try {
            map = jsonHelper.toMap(jsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
        Toast.makeText(getBaseContext(), map.get("1").toString(),Toast.LENGTH_LONG).show();

        simpleList = (ListView)findViewById(R.id.listView1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_add_attendance, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }}
