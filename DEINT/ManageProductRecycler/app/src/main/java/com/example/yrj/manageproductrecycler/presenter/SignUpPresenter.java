package com.example.yrj.manageproductrecycler.presenter;

import android.content.Context;

import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.interfaces.IValidateAccount;
import com.example.yrj.manageproductrecycler.interfaces.IValidateUser;
import com.example.yrj.manageproductrecycler.model.Error;
import com.example.yrj.manageproductrecycler.model.User;
import com.example.yrj.manageproductrecycler.utils.ErrorMapUtils;

/**
 * Created by usuario on 10/11/16.
 */

public class SignUpPresenter implements IValidateUser.Presenter, IValidateUser.PresenterUser{
    public static final int TOAST = -1;
    IValidateUser.View view;
    Context context;

    public SignUpPresenter(IValidateUser.View view){
        this.view = view;
        this.context = (Context)view;
    }
    public boolean validateCredentials(String user,String pass, String email){
        int error = IValidateUser.Presenter.validateUser(user);
        if (error == Error.OK) {
            error = IValidateUser.Presenter.validatePass(pass);
            if (error == Error.OK){
                error = IValidateUser.PresenterUser.validateEmail(email);
                if (error == Error.OK) {
                    // t0do correcto
                } else {
                    String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(error));
                    view.setMessageError(nameResource, R.id.tilEmail);
                }
            } else {
                String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(error));
                view.setMessageError(nameResource, R.id.tilPass);
            }
        } else {
            String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(error));
            view.setMessageError(nameResource, R.id.tilUser);
        }
        IValidateUser.PresenterUser.validateEmail(email);
        return true;
    }

}
