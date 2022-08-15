package com.myapplicationdev.android.hospitaladmissions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.*;

public class MainActivity extends AppCompatActivity {

    ListView lvHospital;
    AsyncHttpClient client;
    ArrayAdapter<Hospital> aaHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvHospital = findViewById(R.id.lvHospital);
        client = new AsyncHttpClient();
    }


        @Override
        protected void onResume () {
            super.onResume();

            ArrayList<Hospital> alHospital = new ArrayList<Hospital>();

            client.get("https://data.gov.sg/api/action/datastore_search?resource_id=ba3c89a7-cfc2-4c87-afe3-b688b0f0ad75&limit=5", new JsonHttpResponseHandler() {

                String level;
                String value;
                String year;

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        JSONObject firstObj = response.getJSONObject("result");
                        JSONArray jsonArrItems = firstObj.getJSONArray("records");

                        for (int i = 0; i < jsonArrItems.length(); i++) {
                            JSONObject jsonObjForecast = jsonArrItems.getJSONObject(i);
                            level = jsonObjForecast.getString("level_1");
                            value = jsonObjForecast.getString("value");
                            year = jsonObjForecast.getString("year");
                            Hospital hospital = new Hospital(level, value, year);
                            alHospital.add(hospital);
                        }
                    } catch (JSONException e) {

                    }

                    //POINT X – Code to display List View
//                    aaHospital = new ArrayAdapter<Hospital>(MainActivity.this, android.R.layout.simple_list_item_1, alHospital);
//                    lvHospital.setAdapter(aaHospital);
                    aaHospital = new CustomAdapter(MainActivity.this,R.layout.row, alHospital);
                    lvHospital.setAdapter(aaHospital);

                }//end onSuccess
            });
        }//end onResume
    }
