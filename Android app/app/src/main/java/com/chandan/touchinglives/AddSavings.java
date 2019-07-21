package com.chandan.touchinglives;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import classes.JsonHelper;

public class AddSavings extends AppCompatActivity {

    ListView lvData;
    ArrayList<Student> sdata=new ArrayList<>();
    ArrayAdapter<Student> adapter;
    private String LOCAL_URL= "http://a490268b.ngrok.io/getstudentdata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_savings);

        lvData=(ListView)findViewById(R.id.lvData1);


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



        queue.add(stringRequest);

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

            Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, Object> entry = itr.next();
                String roll_no = entry.getKey();
                String name = entry.getValue().toString();

                Student s = new Student();
                s.setRno(roll_no);
                s.setName(name);

                sdata.add(s);



            }

            adapter=new ArrayAdapter<Student>(AddSavings.this,android.R.layout.simple_expandable_list_item_1,sdata);
            lvData.setAdapter(adapter);

            lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // TODO Auto-generated method stub
                    String value= adapter.getItem(position);
                    Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

                }
            });


//            for (int i=0;i<map.size();i++){
//
//                Student s=map.get;
//                sdata.add(s);




            // Locate ListView in listview_main.xml

            // Bind array strings into an adapter


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  Toast.makeText(getBaseContext(), map.get("1").toString(),Toast.LENGTH_LONG).show();
        //  studentList.add((HashMap<String, String>) newmap);

    }


    //simpleList = (ListView)findViewById(R.id.listView1);

}
