package com.example.logintable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logintable.controller.LoginTable_Controller;

public class LoginTable_Activity extends AppCompatActivity {

    private LoginTable_Controller loginTableController;
    private EditText etUser, etPass;
    private Button btOk, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_table);
        //Initializing variables
        loginTableController = new LoginTable_Controller();

        //Setting controls
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        btCancel = (Button) findViewById(R.id.btCancel);
        btOk = (Button) findViewById(R.id.btOk);

        //Click events
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
