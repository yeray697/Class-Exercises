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
import android.widget.Button;
import android.widget.EditText;

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
    private IValidateAccount.Presenter loginMvp;
    private EditText etUser, etPass;
    private TextInputLayout tilUser, tilPass;
    private Button btSubmit;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface font = Typeface.createFromAsset(getAssets(),"hipsterfont.otf");
        loginMvp = new LoginPresenter(this); //Presenter has a reference to the view
        //Setting controls
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
                Intent intent = new Intent(Login_Activity.this, SignUpActivity.class);
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

        if (IValidateAccount.Presenter.validateUser(user) == IValidateAccount.Pre)
    }

    /**
     * Method implemented that if there is an error, it will show it on a Toast
     * @param idMessageError Error's id that will show
     */
    @Override
    public void setMessageError(int idMessageError, int idView) {
        String messageError = "";
        if (idMessageError != -1)
            messageError = getResources().getString(idMessageError);
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
