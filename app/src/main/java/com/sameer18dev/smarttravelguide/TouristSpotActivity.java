package com.sameer18dev.smarttravelguide;

import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TouristSpotActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<touristspot> touristspots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_spot);

        setTitle("Tourist Spots");

        getTouristSpots();

//        Toast.makeText(TouristSpotActivity.this, String.valueOf(touristspots.size()), Toast.LENGTH_SHORT).show();

    }


    public void getTouristSpots(){

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://myandroidappstesting.000webhostapp.com/SmartTravelGuide/RetriveTouristSpots.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Toast.makeText(VideoActivity.this, response, Toast.LENGTH_SHORT).show();

//                        Log.d("mydata", "onResponse: "+response);

                        try {


                            JSONArray jsonArray = new JSONArray(response);

//                            Toast.makeText(VideoActivity.this, "Here!", Toast.LENGTH_SHORT).show();
//
                            for (int i = 0; i<jsonArray.length(); i++) {

                                JSONObject json = jsonArray.getJSONObject(i);

                                Log.d("data", "onResponse: "+json.getString("tsname") );

//                                Toast.makeText(VideoActivity.this, json.getString("vlink"), Toast.LENGTH_SHORT).show();

                                touristspot spot = new touristspot(json.getString("tsname") , json.getString("tsdesp"), json.getString("tscity"), json.getString("tsratings"));
                                touristspots.add(spot);

                            }


                            Toast.makeText(TouristSpotActivity.this, String.valueOf(touristspots.size()), Toast.LENGTH_SHORT).show();
                            recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager( new LinearLayoutManager(TouristSpotActivity.this));
                            TouristSpotAdapter touristSpotAdapter = new TouristSpotAdapter(touristspots);
                            recyclerView.setAdapter(touristSpotAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TouristSpotActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}
