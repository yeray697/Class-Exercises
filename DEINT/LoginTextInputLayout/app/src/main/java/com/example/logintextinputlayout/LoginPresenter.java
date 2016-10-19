package com.example.logintextinputlayout;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Class that implements Login business rules:
 *  -It needs at least one uppercase and lowercase letter
 *  -It needs at least one digit
 *  -It needs to be at least greater than 8 letters
 *  @author Yeray Ruiz
 *  @version 1.0
 */
public class LoginPresenter implements ILoginMvp.Presenter {
    private ILoginMvp.View view;
    private String user;
    private String pass;

    /**
     * Constructor
     * @param view View implemented from ILoginMvp.View
     */
    public LoginPresenter(ILoginMvp.View view) {
        this.view = view;
    }

    /**
     * Method that check if a password comply with some conditions
     * @param user Username used to login
     * @param pass Password used to login
     * @return Return a code with the result. 0 = Correct, else, it there is an error
     */
    @Override
    public void validateCredentials(String user, String pass) {
        this.user = user;
        this.pass = pass;
        String error = "";
        int idView = -1;
        int result = ILoginMvp.CORRECT;
        Boolean numericMatch, uppercaseMatch, lowercaseMatch, minLength;
        //Boolean conditions
        minLength = this.pass.length() < ILoginMvp.MINLENGTH;
        numericMatch = Pattern.matches(".*[0-9]+.*",this.pass);
        uppercaseMatch = Pattern.matches(".*[a-z]+.*",this.pass);
        lowercaseMatch = Pattern.matches(".*[A-Z]+.*",this.pass);
        //Checking conditions
        if (TextUtils.isEmpty(this.user)) {
            result = ILoginMvp.DATA_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_user);
            idView = R.id.tilUser;
        }
        else if (TextUtils.isEmpty(this.pass)) {
            result = ILoginMvp.DATA_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass);
            idView = R.id.tilPass;
        }
        else if (minLength){
            result = ILoginMvp.PASSWORD_LENGTH;
            error = ((Context)view).getResources().getString(R.string.password_length);
            idView = R.id.tilPass;
        }
        else if (!numericMatch){
            result = ILoginMvp.PASSWORD_DIGIT;
            error = ((Context)view).getResources().getString(R.string.password_digit);
            idView = R.id.tilPass;
        }
        else if (!uppercaseMatch){
            result = ILoginMvp.PASSWORD_CASE;
            error = ((Context)view).getResources().getString(R.string.password_case);
            idView = R.id.tilPass;
        }
        else if (!lowercaseMatch){
            result = ILoginMvp.PASSWORD_CASE;
            error = ((Context)view).getResources().getString(R.string.password_case);
            idView = R.id.tilPass;
        }
        else { //If there is no error
            ((LoginApplication)((Context)view).getApplicationContext()).setUser(new User(this.user, this.pass));
        }
        //If there is an error, we set it on the view
        if (result != ILoginMvp.CORRECT) {
            view.setMessageError(error, idView);
        }
    }
}
