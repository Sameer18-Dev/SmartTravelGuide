package com.sameer18dev.smarttravelguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() // Builtin class.
        {


            public void run(){

                try {

                    sleep(1800);
                }
                catch (Exception e){



                    e.printStackTrace();


                }
                finally {

                    Intent intent = new Intent(splash.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }




            }





        };thread.start();

    }




}
