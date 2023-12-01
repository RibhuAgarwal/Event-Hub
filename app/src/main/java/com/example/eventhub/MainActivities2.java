package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivities2 extends AppCompatActivity {
    Button startw,join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        long appID =1884903976 ;
        String appSign = "261b400bbed259c17fb4be1f8c9e43e8fdff5f108b479d75f96617934d891ef3";

        String userID = generateUserID();
        String userName = userID + "_Name";
        String liveID = "test_live_id";


        startw= findViewById(R.id.start_live);
        join = findViewById(R.id.watch_live);

        Log.e("Man:", String.valueOf(startw));
        startw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivities2.this, LiveStreaming.class);
                intent.putExtra("host", true);
                intent.putExtra("appID", appID);
                intent.putExtra("appSign", appSign);
                intent.putExtra("userID", userID);
                intent.putExtra("userName", userName);
                intent.putExtra("liveID", liveID);
                startActivity(intent);
                Log.e("Ribhu :", "Jai SHree Ram");
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivities2.this, LiveStreaming.class);
                intent.putExtra("appID", appID);
                intent.putExtra("appSign", appSign);
                intent.putExtra("userID", userID);
                intent.putExtra("userName", userName);
                intent.putExtra("liveID", liveID);
                startActivity(intent);
                Log.e("Ribhu:", "Jai SHree Ram");

            }
        });


    }

    private String generateUserID() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < 5) {
            int nextInt = random.nextInt(10);
            if (builder.length() == 0 && nextInt == 0) {
                continue;
            }
            builder.append(nextInt);
        }
        return builder.toString();
    }
}