package com.example.yrj.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.yrj.manageproductrecycler.MultiListProduct_Fragment;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.interfaces.ILoginPresenter;
import com.example.yrj.manageproductrecycler.interfaces.ILoginView;
import com.example.yrj.manageproductrecycler.model.Error;

import java.util.regex.Pattern;

/**
 * Class that implements Login business rules:
 *  -It needs at least one uppercase and lowercase letter
 *  -It needs at least one digit
 *  -It needs to be at least greater than 8 letters
 *  @author Yeray Ruiz
 *  @version 1.0
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    private int validateUser;
    private int validatePass;
    private Context context;

    /**
     * Constructor
     * @param view View implemented from ILoginMvp.View
     */
    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.context = (Context)view;
    }

    public void validateCredentialsLogin(String user, String password) {
        validateUser = validateUser(user);
        if (validateUser == Error.OK) {
            validatePass = validatePass(password);
            if(validatePass == Error.OK) {
                Intent intent = new Intent(context, MultiListProduct_Fragment.class);
                ((Context)view).startActivity(intent);
            } else {
                view.setMessageError(Error.getMessageError(context, validatePass), R.id.tilPass);
            }
        } else {
            view.setMessageError(Error.getMessageError(context, validateUser), R.id.tilUser);
        }
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
        minLength = pass.length() < ILoginPresenter.MINLENGTH;
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
}
