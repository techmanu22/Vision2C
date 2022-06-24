package com.example.vision2c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
        Button battery_status,audio_dialpad, view_contacts, Notes, settings, btn_sms, text_recognition;
        TextToSpeech tts;

        private long lastTouchTime = 0;
        private long currentTouchTime = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        speak = findViewById(R.id.speak);
//        double_click = findViewById(R.id.double_click);
//        long_press = findViewById(R.id.long_press);

        battery_status = findViewById(R.id.battery_status);


//      audio_dialpad = findViewById(R.id.audio_dialpad);

        view_contacts = findViewById(R.id.view_contacts);

        Notes = findViewById(R.id.Notes);

        settings = findViewById(R.id.settings);

        btn_sms = findViewById(R.id.btn_sms);

    //    text_recognition = findViewById(R.id.btn_text_recognition);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.US);
                    tts.setSpeechRate(0.8f);
                    tts.speak("Welcome to Vision2C",TextToSpeech.QUEUE_ADD,null);
                }
            }
        });

//        speak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tts.speak("Hello Manasi, how are you?",TextToSpeech.QUEUE_ADD,null);
//
//            }
//        });
//
//        long_press.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                tts.speak("you have long pressed", TextToSpeech.QUEUE_ADD,null);
//                return false;
//            }
//        });

//        double_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lastTouchTime = currentTouchTime;
//                currentTouchTime = System.currentTimeMillis();
//
//                if (currentTouchTime - lastTouchTime < 390) {
//                    Log.d("Double","Click");
//                    tts.speak("Double click initiated",TextToSpeech.QUEUE_ADD,null);
//                    lastTouchTime = 0;
//                    currentTouchTime = 0;
//                }
//            }
//
//        });

        battery_status.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getApplicationContext(),battery.class);
                startActivity(intent);
                return false;
            }
        });

//        audio_dialpad.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Audio_Dialpad.class);
//                startActivity(intent);
//                return false;
//            }
//        });


        view_contacts.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Audio_Dialpad.class);
                startActivity(intent);
                return false;
            }
        });

        Notes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main6Activity.class);
                startActivity(intent);
                return false;
            }
        });


        settings.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                return false;
            }
        });

        btn_sms.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sms_create.class);
                startActivity(intent);
                return false;
            }
        });

//        text_recognition.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),TextRecognitionApp.class);
//                startActivity(intent);
//                return false;
//            }
//        });

    }
}