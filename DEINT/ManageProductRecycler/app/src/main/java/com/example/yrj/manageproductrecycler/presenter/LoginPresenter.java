package com.example.yrj.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.yrj.manageproductrecycler.Product_Activity;
import com.example.yrj.manageproductrecycler.interfaces.IValidateAccount;

/**
 * Class that implements Login business rules:
 *  -It needs at least one uppercase and lowercase letter
 *  -It needs at least one digit
 *  -It needs to be at least greater than 8 letters
 *  @author Yeray Ruiz
 *  @version 1.0
 */
public class LoginPresenter implements IValidateAccount.Presenter {
    private IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;

    /**
     * Constructor
     * @param view View implemented from ILoginMvp.View
     */
    public LoginPresenter(IValidateAccount.View view) {
        this.view = view;
    }

    public void validateCredentialsLogin(String user, String password){
        validateUser = IValidateAccount.Presenter.validateUser(user);
        validatePassword = IValidateAccount.Presenter.validatePass(password);
        if (validateUser == IValidateAccount.OK && validatePassword == IValidateAccount.OK) {
            Intent intent = new Intent((Context)view, Product_Activity.class);
            ((Context)view).startActivity(intent);
        }
    }

}
