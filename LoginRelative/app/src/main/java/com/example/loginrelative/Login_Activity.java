package com.example.loginrelative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Class that implements a login
 * @author Yeray Ruiz
 * @version 1.0
 */
public class Login_Activity extends AppCompatActivity implements ILoginMvp.View {

    private ILoginMvp.Presenter loginMvp;
    private EditText etUser, etPass;
    private Button btSubmit, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginMvp = new LoginPresenter(this); //Presenter has a reference to the view
        //Setting controls
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        btCancel = (Button) findViewById(R.id.btCancel);
        //Click events
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClick();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
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
     * Method used when user click on btCancel
     */
    private void resetValues() {
        etPass.setText("");
        etUser.setText("");
    }

    /**
     * Method implemented that if there is an error, it will show it on a Toast
     * @param messageError Error that will show
     */
    @Override
    public void setMessageError(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }
}
