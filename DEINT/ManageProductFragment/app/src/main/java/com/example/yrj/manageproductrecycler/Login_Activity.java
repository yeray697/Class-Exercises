package com.example.yrj.manageproductrecycler;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.yrj.manageproductrecycler.interfaces.IValidateAccount;
import com.example.yrj.manageproductrecycler.model.User;
import com.example.yrj.manageproductrecycler.presenter.LoginPresenter;

/**
 * Class that implements a login
 * @author Yeray Ruiz
 * @version 1.0
 */
public class Login_Activity extends AppCompatActivity implements IValidateAccount.View {

    private static final String TAG = "loginrelative";
    private LoginPresenter presenter;
    private EditText etUser, etPass;
    private TextInputLayout tilUser, tilPass;
    private Button btSubmit;
    private Button btSignUp;
    private ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface font = Typeface.createFromAsset(getAssets(),"hipsterfont.otf");
        presenter = new LoginPresenter(this); //Presenter has a reference to the view
        //Setting controls
        parent = (RelativeLayout) findViewById(R.id.activity_login);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        tilUser = (TextInputLayout) findViewById(R.id.tilUser);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        btSignUp = (Button) findViewById(R.id.btSignUp);
        //Events
        etUser.setTypeface(font);
        etPass.setTypeface(font);
        tilUser.setTypeface(font);
        tilPass.setTypeface(font);
        btSubmit.setTypeface(font);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClick();
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
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
        User user = ((Login_Application)getApplicationContext()).getUser();
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
        //Checking credentials. If there is an error, a Toast will appears
        presenter.validateCredentialsLogin(user,pass);

    }

    /**
     * Method implemented that if there is an error, it will show it on a Toast
     * @param messageError Error that will show
     * @param idView View where error is going to be set
     */
    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case R.id.tilUser:
                tilUser.setError(messageError);
                //Snackbar.make(parent,messageError,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.tilPass:
                tilPass.setError(messageError);
                break;
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Activity finalizada");
        //Checking Application Object
        User user = ((Login_Application)getApplicationContext()).getUser();
        if (user != null){
            Log.d(TAG, "User: "+ user.getUser());
            Log.d(TAG, "Pass: "+ user.getPassword());
        }
    }
}
