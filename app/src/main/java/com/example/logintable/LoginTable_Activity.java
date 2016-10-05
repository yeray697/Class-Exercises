package com.example.logintable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                resetValues();
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClick();
            }
        });
    }

    private void loginClick() {
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
            Toast.makeText(LoginTable_Activity.this,getResources().getString(R.string.data_empty),Toast.LENGTH_SHORT).show();
        }else {
            int result = loginTableController.validateCredentials(user,pass);
            switch (result){
                case 0: //Correct
                    break;
                case LoginTable_Controller.PASSWORD_DIGIT: //1 digit required
                    Toast.makeText(LoginTable_Activity.this,
                            getResources().getString(R.string.password_digit)
                            , Toast.LENGTH_SHORT).show();
                    break;
                case LoginTable_Controller.PASSWORD_CASE: //1 lowercase and 1 uppercase letter required
                    Toast.makeText(LoginTable_Activity.this,
                            getResources().getString(R.string.password_case)
                            , Toast.LENGTH_SHORT).show();
                    break;
                case LoginTable_Controller.PASSWORD_LENGTH: //Min length == 8
                    Toast.makeText(LoginTable_Activity.this,
                            getResources().getString(R.string.password_length)
                            , Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void resetValues() {
        etPass.setText("");
        etUser.setText("");
    }
}
