package com.ncatz.yeray.boundservice;

import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btPrintTime;
    Button btStopService;
    TextView tvTime;
    private boolean mServiceBound;
    private ServiceConnection serviceConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btPrintTime = (Button) findViewById(R.id.btPrintTime);
        btStopService = (Button) findViewById(R.id.btStopService);
        tvTime = (TextView) findViewById(R.id.tvTime);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound){
            unbindService(serviceConnection);
            mServiceBound = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
}
