package com.example.user0000.fragmentexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String times = "5:15 | 4:41 | 4:27 | 3:13 | 5:01 | 4:47 | 4:50 | 5:20";
        String[] timesSplit = times.split(" | ");

        int hour = 0;
        int minute = 0;

        for(int i = 0; i < timesSplit.length; i += 2) {
            String[] time = timesSplit[i].split(":");
            hour += Integer.parseInt(time[0]);
            minute += Integer.parseInt(time[1]);
        }

        hour += minute / 60;
        minute %= 60;

        String result = hour + ":" + minute;

    }
}
