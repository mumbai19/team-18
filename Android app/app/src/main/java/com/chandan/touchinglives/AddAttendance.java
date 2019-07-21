package com.chandan.touchinglives;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import classes.JsonHelper;
import android.widget.ArrayAdapter;import android.widget.ListView;




public class AddAttendance extends AppCompatActivity {
private ListView studentListView;
//private static final String key_roll="Roll";
    private static final String key_nmae="Name";
   private ListView listView;
   private TextView textView;
   private String[] listItem;
    private String LOCAL_URL= "http://a490268b.ngrok.io/studentdata";
private ArrayList<HashMap<String,String>>studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ListView listView = (ListView) findViewById(R.id.listView1);

        setContentView(R.layout.activity_add_attendance);
        //simpleList = (ListView)findViewById(R.id.listView1);

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


        Map<String,Object> map = new HashMap();

        JsonHelper jsonHelper = new JsonHelper();
        try {

            map = jsonHelper.toMap(jsonObject);
            ListView listView;
            String[] SamsungPhones = new String[] { "Galaxy S", "Galaxy S2",
                    "Galaxy Note", "Galaxy Beam", "Galaxy Ace Plus", "Galaxy S3",
                    "Galaxy S Advance", "Galaxy Wave 3", "Galaxy Wave Y",
                    "Galaxy Nexus", "Galaxy W", "Galaxy Y", "Galaxy Mini",
                    "Galaxy Gio", "Galaxy Wave", "Galaxy Wave 2" };

            // Locate ListView in listview_main.xml
            listView = (ListView) findViewById(R.id.listView);

            // Bind array strings into an adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1,
                    SamsungPhones);
            listView.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(getBaseContext(), map.get("1").toString(),Toast.LENGTH_LONG).show();
          //  studentList.add((HashMap<String, String>) newmap);

    }


        //simpleList = (ListView)findViewById(R.id.listView1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_add_attendance, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }}
}
