package com.sameer18dev.smarttravelguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void gotocities(View view) {
            Intent intent = new Intent(Dashboard.this,CityList.class);
            startActivity(intent);
    }

    public void mapsgo(View view) {
        Intent intent = new Intent(Dashboard.this, MapActivity.class);
        startActivity(intent);
    }

    public void gotovideos(View view) {
        Intent intent = new Intent(Dashboard.this,VideoActivity.class);
        startActivity(intent);
    }

    public void gotospots(View view) {
        Intent intent = new Intent(Dashboard.this,TouristSpotActivity.class);
        startActivity(intent);
    }
}