package com.example.yrj.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.yrj.manageproductrecycler.Login_Activity;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.interfaces.IValidateAccount;
import com.example.yrj.manageproductrecycler.interfaces.IValidateUser;
import com.example.yrj.manageproductrecycler.model.Error;
import com.example.yrj.manageproductrecycler.preferences.AccountPreferences;

import java.util.regex.Pattern;

/**
 * Created by usuario on 10/11/16.
 */

public class SignUpPresenter implements IValidateUser.Presenter, IValidateUser.PresenterUser{
    public static final int SNACKBAR = -1;
    IValidateUser.View view;
    Context context;

    public SignUpPresenter(IValidateUser.View view){
        this.view = view;
        this.context = (Context)view;
    }
    public boolean validateCredentials(String user, String pass, String email, String county, String city, boolean isBusinessType, String businessName, boolean privacyAccepted){
        boolean result;
        Error error = checkCredentials(user,pass,email,county,city,isBusinessType,businessName,privacyAccepted);
        if (error.getCode() != Error.OK) {
            view.setMessageError(Error.getMessageError(context, error.getCode()), error.getIdView());
            result = false;
        } else {
            result = true;
            view.startActivity(new Intent(context, Login_Activity.class));
        }
        return result;
    }

    private Error checkCredentials(String user, String pass, String email, String county, String city, boolean isBusinessType, String businessName, boolean privacyAccepted) {
        Error error = new Error();
        error.setCode(validateUser(user));
        if (error.getCode() != Error.OK) {
            error.setIdView(R.id.tilUser);
        } else {
            error.setCode(validatePass(pass));
            if (error.getCode() != Error.OK) {
                error.setIdView(R.id.tilPass);
            } else {
                error.setCode(validateEmail(email));
                if (error.getCode() != Error.OK) {
                    error.setIdView(R.id.tilEmail);
                } else {
                    if (TextUtils.isEmpty(county)) {
                        error.setCode(Error.COUNTY_EMPTY);
                        error.setIdView(SNACKBAR);
                    } else {
                        if (TextUtils.isEmpty(city)) {
                            error.setCode(Error.CITY_EMPTY);
                            error.setIdView(SNACKBAR);
                        } else {
                            if (isBusinessType){
                                if (TextUtils.isEmpty(businessName)) {
                                    error.setCode(Error.BUSINESS_EMPTY);
                                    error.setIdView(R.id.tilBusiness);
                                }
                            }
                            if (error.getCode() == Error.OK) {
                                if (!privacyAccepted) {
                                    error.setCode(Error.PRIVACY_FALSE);
                                    error.setIdView(SNACKBAR);
                                }
                            }
                        }
                    }
                }
            }
        }
        return error;
    }

    public void savePreferences(String user, String email, String pass) {
        AccountPreferences accountPreferences = (AccountPreferences) AccountPreferences.getInstance(context);
        accountPreferences.putUser(user);
        accountPreferences.putEmail(email);
        accountPreferences.putPassword(pass);
    }

    @Override
    public int validateUser(String user) {
        int result = Error.OK;
        int idErrorResource = -1;
        int idView = R.id.tilUser;

        if (TextUtils.isEmpty(user)) {
            idErrorResource = R.string.data_empty_user;
            result = Error.DATA_EMPTY;
        }

        return result;
    }

    @Override
    public int validatePass(String pass) {
        int result = Error.OK;
        Boolean numericMatch, uppercaseMatch, lowercaseMatch, minLength;
        //Boolean conditions
        minLength = pass.length() < IValidateAccount.MINLENGTH;
        numericMatch = Pattern.matches(".*[0-9]+.*", pass);
        uppercaseMatch = Pattern.matches(".*[a-z]+.*", pass);
        lowercaseMatch = Pattern.matches(".*[A-Z]+.*", pass);

        //Checking conditions

        if (TextUtils.isEmpty(pass)) {
            result = Error.DATA_EMPTY;
        } else if (minLength) {
            result = Error.PASSWORD_LENGTH;
        } else if (!numericMatch) {
            result = Error.PASSWORD_DIGIT;
        } else if (!uppercaseMatch) {
            result = Error.PASSWORD_CASE;
        } else if (!lowercaseMatch) {
            result = Error.PASSWORD_CASE;
        }

        return result;
    }

    @Override
    public int validateEmail(String email) {
        int result = Error.OK;
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            result = Error.EMAIL_INVALIDATE;
        return result;
    }
}
