package com.example.sendmessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Class that sends a message to another Activity
 * @see android.app.Activity
 * @author Yeray Ruiz
 * @version 1.0
 */

public class SendMessage_Activity extends AppCompatActivity {
    //TODO My first Activity. What a thrill!!
    private static final String TAG = "com.example.sendmessage"; //Used for Log
    protected static final String SENT = "sent"; //Name of the variable sent to the other activity
    private static EditText meTSend;
    private static Button mbtSend;

    /**
     * Method created by the Activity
     * @param savedInstanceState @see android.os.Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        //Setting controls
        mbtSend = (Button)findViewById(R.id.btSend);
        meTSend = (EditText)findViewById(R.id.eTSend);
        //onClick Events
        mbtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        Log.e(TAG, "La actividad se ha creado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "La actividad se ha iniciado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "La actividad es visible");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "La actividad no es visible (pause)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "La actividad se ha cerrado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "La actividad se ha destruido");
    }

    /**
     * That method send to another activity a message
     */
    private void send() {
        String text = meTSend.getText().toString();
        if (text.length()>0){
            Intent intent = new Intent(SendMessage_Activity.this, ReceiveMessage_Activity.class);
            intent.putExtra(SENT, text);
            startActivity(intent);
        }
    }
}