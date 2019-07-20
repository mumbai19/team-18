package com.chandan.touchinglives;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.CheckNetworkStatus;
import classes.HttpJsonParser;

public class MainActivity extends AppCompatActivity {

    Button button_login;
    EditText login_id, password;
  private static final String BASE_URL = "";
    ProgressDialog pDialog;
    private int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_id = (EditText) findViewById(R.id.login_id);
        password = (EditText) findViewById(R.id.password);
        button_login = findViewById(R.id.button_login);
    }

    public void button_click(View view) {
        String log_id = login_id.getText().toString();
        String pwd = password.getText().toString();

        if(log_id.equals("a") && pwd.equals("1"))
        {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getBaseContext(),Dashboard.class);
            i.putExtra("ID",log_id);
            i.putExtra("PWD",pwd);
            if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                checkLoginDetails();
            } else {
                Toast.makeText(MainActivity.this,
                        "Unable to connect to internet",
                        Toast.LENGTH_LONG).show();
            }
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Login Failed!",Toast.LENGTH_SHORT).show();
        }
    }

   private void checkLoginDetails() {
        if (!"".equals(login_id.getText().toString()) &&
                !"".equals(password.getText().toString()))
            new CheckLoginDetails().execute();
        else {
            Toast.makeText(MainActivity.this,
                    "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private class CheckLoginDetails extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Sending uid and pid. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            Map<String,String> httpParams = new HashMap<>();
            httpParams.put("", login_id.getText().toString());
            httpParams.put("", password.getText().toString());

            HttpJsonParser httpJsonParser = new HttpJsonParser();
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "first_file.php", "POST", httpParams);

            try {
                success = jsonObject.getInt("Key1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(MainActivity.this,
                                "Done Received Key " + success, Toast.LENGTH_LONG).show();
                        //Intent i = getIntent();
                        //send result code 20 to notify about movie update
                        //setResult(20, i);
                        //Finish ths activity and go back to listing activity
                        //finish();

                    } else {
                        Toast.makeText(MainActivity.this,
                                "Some error occurred while adding movie",
                                Toast.LENGTH_LONG).show();

                    }

                }

            });

        }
    }
}
