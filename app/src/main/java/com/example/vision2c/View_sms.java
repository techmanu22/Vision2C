package com.example.vision2c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class View_sms extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sms);

//        Uri uriSms =Uri.parse("content://sms/inbox");
//        Cursor cursor = getContentResolver().query(uriSms, null,null,null,null);
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);

                    Toast.makeText(this, msgData , Toast.LENGTH_SHORT).show();
                }
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(View_sms.this, Manifest.permission.READ_SMS))
            {
                ActivityCompat.requestPermissions(View_sms.this,new String[] {Manifest.permission.READ_SMS}, 1);
            }
            else
            {
                ActivityCompat.requestPermissions(View_sms.this,new String[] {Manifest.permission.SEND_SMS}, 1);
            }

        }
        else
        {
            /* do nothing */
            /* permission is granted */
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(View_sms.this,
                            Manifest.permission.READ_SMS) ==  PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(context, "No Permission granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}