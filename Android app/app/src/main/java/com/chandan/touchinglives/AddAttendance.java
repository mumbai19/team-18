package com.chandan.touchinglives;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import classes.JsonHelper;
import android.widget.ArrayAdapter;import android.widget.ListView;




public class AddAttendance extends AppCompatActivity {

    ListView lvData;
    ArrayList<Student>sdata=new ArrayList<>();
    ArrayAdapter<Student> adapter;
Logger log=Logger.getLogger("AddAttendance");
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

        lvData=(ListView)findViewById(R.id.lvData);


        //simpleList = (ListView)findViewById(R.id.listView1);




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

                    adapter=new ArrayAdapter<Student>(AddAttendance.this,android.R.layout.simple_expandable_list_item_1,sdata);
                    lvData.setAdapter(adapter);
        //            for (int i=0;i<map.size();i++){
        //
        //                Student s=map.get;
        //                sdata.add(s);




                            // Locate ListView in listview_main.xml

                    // Bind array strings into an adapter


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getBaseContext(), map.get("1").toString(),Toast.LENGTH_LONG).show();
                  //  studentList.add((HashMap<String, String>) newmap);

            }


        //simpleList = (ListView)findViewById(R.id.listView1);

    }
