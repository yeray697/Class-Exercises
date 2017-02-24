package com.ncatz.yeray.basicservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_SERVICE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BasicService.class);
                startService(intent);
            }
        });
        findViewById(R.id.btStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BasicService.class);
                stopService(intent);
            }
        });

        Intent intent = new Intent(MainActivity.this, InfinityService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,REQUEST_SERVICE,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC,0,5*1000,pendingIntent);
    }
}
