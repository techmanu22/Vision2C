package com.example.vision2c;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

public class TextRecognitionApp extends AppCompatActivity {

    private Button captureBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_text_recognition_app);
        captureBtn = findViewById(R.id.idBtnCapture);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i = new Intent(TextRecognitionApp.this, ScannerActivity.class);
                 startActivity(i);
            }
        });
    }
}