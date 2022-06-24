package com.example.vision2c;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Main6Activity extends AppCompatActivity {
    DatabaseHelper myDB;
    Button btnAdd, btnView, back;
    TextView textView;
    TextToSpeech toSpeech;
    String s = "Notes page.Volume down button to add notes. Volume up to save it into list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main6activity);
        textView = (TextView) findViewById(R.id.textView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);
        back = (Button) findViewById(R.id.btn_back);
        toSpeech = new TextToSpeech(getApplicationContext(), new
                TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            toSpeech.setLanguage(Locale.ENGLISH);
                            toSpeech.speak(s, TextToSpeech.QUEUE_ADD, null);
                        }
                    }
                });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak("long press to add noted", TextToSpeech.QUEUE_ADD, null);
            }
        });
        btnAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String newEntry = textView.getText().toString();
                if (textView.length() != 0) {
                    AddData(newEntry);
                    toSpeech.speak("Data added", TextToSpeech.QUEUE_ADD, null);
                    textView.setText("");
                } else {
                    Toast.makeText(Main6Activity.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        btnView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(Main6Activity.this, View_notes.class);
                startActivity(intent);
                return false;
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak("View Saved notes", TextToSpeech.QUEUE_ADD, null);
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

    public void AddData(String newEntry) {
        boolean insertData = myDB.addData(newEntry);
        if (insertData == true) {
            Toast.makeText(this, "Data Successfully Inserted!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Something went wrong :(.",
                    Toast.LENGTH_LONG).show();
        }
    }


    private void speak(String message) {
        if(Build.VERSION.SDK_INT>=21)
        {
            toSpeech.speak(message,
                    TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action=event.getAction();
        int keycode=event.getKeyCode();
        switch (keycode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(KeyEvent.ACTION_UP==action)
                {
                    String newEntry = textView.getText().toString();
                    if(textView.length()!= 0){
                        AddData(newEntry);
                        textView.setText("");
                    }else{
                        speak("You must put something in the text field!");
                    }
                }
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(KeyEvent.ACTION_DOWN==action)
                {
                    Intent intent = new
                            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                            Locale.getDefault());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");
                    try {
                        startActivityForResult(intent, 1);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(),
                                Toast.LENGTH_SHORT);
                    }
                    return true;
                }
        }
        return super.dispatchKeyEvent(event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && null != data) {
                    Toast.makeText(getApplicationContext(), "Hello",Toast.LENGTH_SHORT);
                    ArrayList<String> result =
                            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                }
                break;
        }
    }
}
