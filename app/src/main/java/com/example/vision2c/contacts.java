package com.example.vision2c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class contacts extends AppCompatActivity {
    ListView listView;
    TextView tv1;
    String TAG = contacts.class.getSimpleName();
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    Cursor c;
    ArrayList<String> contacts;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // showContacts();
            } else {
                Toast.makeText(this, "llalalal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        listView = (ListView) findViewById(R.id.listView);
        int permissionOnCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionOnCheck == PackageManager.PERMISSION_DENIED) {
            showContacts();
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_READ_CONTACTS);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, contacts);
//        listView.setAdapter(adapter);


    }


    private void showContacts() {
        {
            c = (Cursor)getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + "ABC");
            contacts = new ArrayList<>();
            while (c.moveToNext()) {
                @SuppressLint("Range") String
                        ContactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String
                        phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add("Name:" + ContactName + "\n" + "PhoneNo:" + phoneNumber);
                Log.d(TAG, "showContacts" + contacts);
            }
        }
        c.close();
    }
}


