package com.example.vision2c;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class battery extends AppCompatActivity {

    private Battery_Status mBattery_status = new Battery_Status();
    private IntentFilter mIntent = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_status);
    }
    @Override
    protected void onPause() {
        unregisterReceiver(mBattery_status);
        super.onPause();
    }
    @Override
    protected void onPostResume() {
        registerReceiver(mBattery_status,mIntent);
        super.onPostResume();
    }
}

