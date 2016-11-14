package com.example.yrj.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.yrj.manageproductrecycler.Product_Activity;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.interfaces.IValidateAccount;
import com.example.yrj.manageproductrecycler.utils.ErrorMapUtils;
import com.example.yrj.manageproductrecycler.model.Error;

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
    private int validatePass;
    private Context context;

    /**
     * Constructor
     * @param view View implemented from ILoginMvp.View
     */
    public LoginPresenter(IValidateAccount.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    public void validateCredentialsLogin(String user, String password) {
        validateUser = IValidateAccount.Presenter.validateUser(user);
        if (validateUser == Error.OK) {
            validatePass = IValidateAccount.Presenter.validatePass(password);
            if(validatePass == Error.OK) {
                Intent intent = new Intent(context, Product_Activity.class);
                view.startActivity(intent);
            } else {
                String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validatePass));
                view.setMessageError(nameResource, R.id.tilUser);
            }
        } else {
            String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.tilPass);
        }
    }
}
