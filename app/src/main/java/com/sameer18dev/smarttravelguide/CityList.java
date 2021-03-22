package com.sameer18dev.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
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


public class CityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        new TestAsync().execute();

//        getCities();
    }


    class TestAsync extends AsyncTask<Void, Integer, String> {
        String TAG = getClass().getSimpleName();

        private List<city> cityList = new ArrayList<>();
        private RecyclerView recyclerView;
        private CityAdapter cAdapter;

        private List<String> cities = new ArrayList<>();


        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG + " PreExceute","On pre Exceute......");
            recyclerView = findViewById(R.id.recyclerView);
        }

        protected String doInBackground(Void...arg0) {
            Log.d(TAG + " DoINBackGround", "On doInBackground...");

            try {
                getCities();

                Thread.sleep(3000); // no need for a loop
            } catch (InterruptedException e) {
                Log.e("LongOperation", "Interrupted", e);
                return "Interrupted";
            }

            return "You are at PostExecute";
        }

        protected void onProgressUpdate(Integer...a) {
            super.onProgressUpdate(a);
            Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + a[0]);
        }

        protected void onPostExecute(String result) {

            cAdapter = new CityAdapter(cityList, CityList.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(cAdapter);
        }


        public void getCities(){

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url ="https://myandroidappstesting.000webhostapp.com/SmartTravelGuide/RetriveCities.php";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                        Log.d("TAG", "onResponse: "+response);

                            try {
                                JSONArray jsonArray = new JSONArray(response);

                                for(int i = 0; i<jsonArray.length(); i++){

                                    JSONObject obj = jsonArray.getJSONObject(i);

                                    cities.add(obj.getString("CName"));

                                    getWeather(obj.getString("CLat"), obj.getString("CLong"), i);

//                                Toast.makeText(CityList.this, "Inserted!", Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CityList.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(stringRequest);
        }

        public void getWeather(String lat, String lng, int index){

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            String url ="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lng+"&appid=e8cc34b82160878e27b76ffe49147703";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                            Toast.makeText(CityList.this, response, Toast.LENGTH_SHORT).show();

                            try {

                                JSONObject json = new JSONObject( response );

                                JSONObject mainObj = new JSONObject( json.getString("main") );

                                Log.d("TAG", "onResponse: "+mainObj.getInt("humidity" ));
                                Log.d("TAG", "onResponse: "+mainObj.getDouble("temp"));

                                Double tmp = mainObj.getDouble("temp");

                                tmp = tmp - 273.15;
//
//                            temp.add(String.valueOf(tmp));
//                            humid.add(String.valueOf(mainObj.getInt("humidity" )));

                                city cty = new city(cities.get(index), String.format("%.0f", tmp), String.valueOf(mainObj.getInt("humidity" )), lat, lng);
                                cityList.add(cty);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CityList.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(stringRequest);
        }
    }

}