package com.example.sendmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Class that receive a message sent by another activity
 * @see android.app.Activity
 * @author Yeray Ruiz
 * @version 1.0
 */
public class ReceiveMessage_Activity extends AppCompatActivity {

    private static TextView mtvReceived;
    /**
     * Method created by the Activity
     * @param savedInstanceState @see android.os.Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        //Setting controls
        mtvReceived = (TextView)findViewById(R.id.tVReceived);
        //Setting the text received
        mtvReceived.setText(getResources().getString(R.string.tvReceivedText) +
                getIntent().getExtras().getString(SendMessage_Activity.SENT));
    }
}