package com.example.yrj.manageproduct;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yrj.manageproduct.ILoginMvp;

/**
 * Class that implements a login
 * @author Yeray Ruiz
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity implements ILoginMvp.View {

    private static final String TAG = "loginrelative";
    private ILoginMvp.Presenter loginMvp;
    private EditText etUser, etPass;
    private TextInputLayout tilUser, tilPass;
    private Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginMvp = new LoginPresenter(this); //Presenter has a reference to the view
        //Setting controls
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        tilUser = (TextInputLayout) findViewById(R.id.tilUser);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        //Events
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClick();
            }
        });
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilUser.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPass.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Checking Application Object
        User user = ((LoginApplication)getApplicationContext()).getUser();
        if (user != null){
            Log.d(TAG, "User: "+ user.getUser());
            Log.d(TAG, "Pass: "+ user.getPassword());
        }
    }

    /**
     * Method used when user clicks on btOk
     */
    private void loginClick() {
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();
        //Checking credentials. If there is an error, a Toast will appear
        loginMvp.validateCredentials(user,pass);
    }

    /**
     * Method implemented that if there is an error, it will show it on a Toast
     * @param messageError Error that will show
     */
    @Override
    public void setMessageError(String messageError, int idView) {
        //Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
        switch (idView){
            case R.id.tilPass:
                tilPass.setError(messageError);
                break;
            case R.id.tilUser:
                tilUser.setError(messageError);
                break;
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Activity finalizada");
        //Checking Application Object
        User user = ((LoginApplication)getApplicationContext()).getUser();
        if (user != null){
            Log.d(TAG, "User: "+ user.getUser());
            Log.d(TAG, "Pass: "+ user.getPassword());
        }
    }
}
