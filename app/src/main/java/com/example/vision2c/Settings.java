package com.example.vision2c;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    private WifiManager wifiManager;
    TextToSpeech toSpeech;
    Button on,off,bon,boff, back;
    String s ="You are on settings page.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        on = (Button) findViewById(R.id.wifi_on);
        off = (Button) findViewById(R.id.wifi_off);
        bon = (Button) findViewById(R.id.Bluetooth_on);
        boff = (Button) findViewById(R.id.Bluetooth_off);
        back = (Button) findViewById(R.id.btn_back);
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        toSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){                   @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            toSpeech.setLanguage(Locale.ENGLISH);
                            toSpeech.speak(s,TextToSpeech.QUEUE_ADD,null);
                        }
                    }
                });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak("Long press to on Wifi",TextToSpeech.QUEUE_ADD,null);
            }
        });

        on.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toSpeech.speak(" Wifi on",TextToSpeech.QUEUE_ADD,null);
                wifiManager.setWifiEnabled(true);
                return false;
            }
        });
        boff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak("Long press to off bluetooth",TextToSpeech.QUEUE_ADD,null);
            }
        });
        boff.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                }
                toSpeech.speak("Bluetooth is Disabled",TextToSpeech.QUEUE_ADD,null);
                return false;
            }
        });
        bon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BluetoothAdapter mBluetoothAdapter =
                        BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter.disable()==true) {
                    mBluetoothAdapter.enable();
                }
                toSpeech.speak("Bluetooth is Enabled",TextToSpeech.QUEUE_ADD,null);
                return false;
            }
        });
        bon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak("Long press to On Bluetooth",TextToSpeech.QUEUE_ADD,null);
            }
        });
        off.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                wifiManager.setWifiEnabled(false);
                speak(" Wifi off");
                return false;
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak("Long press to off Wifi");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak("Back",TextToSpeech.QUEUE_ADD,null);
            }
        });
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                speak("Back");
                finish();
                onBackPressed();
                return false;
            }
        });

    }
    private void speak(String message) {
        if(Build.VERSION.SDK_INT>=21)
        {
            toSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }
}