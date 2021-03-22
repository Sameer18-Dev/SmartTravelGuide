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
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoActivity extends AppCompatActivity {

    SearchView searchView;

    RecyclerView recyclerView;

    VideoAdapter videoAdapter;

    List<YoutubeVideo> youtubeVideos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        setTitle("City Videos");

        searchView = findViewById(R.id.searchview);

        getVideos();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<YoutubeVideo> newList = new ArrayList<>();
                for (YoutubeVideo youtubeVideo: youtubeVideos){
                    String desp = youtubeVideo.getVdesp().toLowerCase();
                    String title = youtubeVideo.getVtitle().toLowerCase();
                    if (desp.contains(newText) || title.contains(newText)){
                        newList.add(youtubeVideo);
                    }
                }
                videoAdapter.setFilter(newList);
                return true;
            }
        });


    }


    public void getVideos(){

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://myandroidappstesting.000webhostapp.com/SmartTravelGuide/RetriveVideos.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray jsonArray = new JSONArray(response);

//                            Toast.makeText(VideoActivity.this, "Here!", Toast.LENGTH_SHORT).show();

                            for (int i = 0; i<jsonArray.length(); i++) {

                                JSONObject json = jsonArray.getJSONObject(i);

                                Log.d("data", "onResponse: "+json.getString("vlink") );

//                                Toast.makeText(VideoActivity.this, json.getString("vlink"), Toast.LENGTH_SHORT).show();

                                YoutubeVideo youtubeVideo = new YoutubeVideo(json.getString("vlink") , json.getString("vdesp"), json.getString("vname"));
                                youtubeVideos.add(youtubeVideo);
                            }

                            Toast.makeText(VideoActivity.this, String.valueOf(youtubeVideos.size()), Toast.LENGTH_SHORT).show();
                            recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager( new LinearLayoutManager(VideoActivity.this));
                            videoAdapter = new VideoAdapter(youtubeVideos);
                            recyclerView.setAdapter(videoAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}
