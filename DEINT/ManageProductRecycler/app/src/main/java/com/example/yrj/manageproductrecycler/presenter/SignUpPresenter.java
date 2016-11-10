package com.example.yrj.manageproductrecycler.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.interfaces.ISignUp;

/**
 * Created by usuario on 10/11/16.
 */

public class SignUpPresenter implements ISignUp.Presenter{
    public static final int TOAST = -1;
    ISignUp.View view;
    public SignUpPresenter(ISignUp.View view){
        this.view = view;
    }

    @Override
    public int validateCredentials(String user, String email, String pass, String county, String city, boolean isBusinessType, String businessName, boolean privacyAccepted) {
        String error = "";
        int idView = -1;
        int result = ISignUp.CORRECT;

        if (privacyAccepted){
            if (TextUtils.isEmpty(user)){
                result = ISignUp.USER_EMPTY;
                error = ((Context)view).getResources().getString(R.string.data_empty_signup);
                idView = R.id.tilUser;
            } else if (TextUtils.isEmpty(pass)){
                result = ISignUp.PASS_EMPTY;
                error = ((Context)view).getResources().getString(R.string.data_empty_signup);
                idView = R.id.tilPass;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                result = ISignUp.EMAIL_EMPTY;
                error = ((Context)view).getResources().getString(R.string.invalid_email_signup);
                idView = R.id.tilEmail;
            } else if (TextUtils.isEmpty(county)){
                result = ISignUp.COUNTY_EMPTY;
                error = ((Context)view).getResources().getString(R.string.data_empty_county_signup);
                idView = TOAST;
            } else if (TextUtils.isEmpty(city)){
                result = ISignUp.CITY_EMPTY;
                error = ((Context)view).getResources().getString(R.string.data_empty_city_signup);
                idView = TOAST;
            } else {
                if (isBusinessType){
                    if (TextUtils.isEmpty(businessName)){
                        result = ISignUp.BUSINESS_NAME_EMPTY;
                        error = ((Context)view).getResources().getString(R.string.data_empty_signup);
                        idView = R.id.tilBusiness;
                    } else {
                        //All correct
                    }
                } else {
                    //All correct
                }
            }
        } else {
            result = ISignUp.PRIVACY_FALSE;
            error = ((Context)view).getResources().getString(R.string.privacy_false_signup);
            idView = TOAST;
        }
        if (result != ISignUp.CORRECT)
            view.setMessageError(error,idView);
        return result;
    }
}
