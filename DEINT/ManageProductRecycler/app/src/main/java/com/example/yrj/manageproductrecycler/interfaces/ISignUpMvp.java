package com.example.yrj.manageproductrecycler.interfaces;

import android.net.Uri;

/**
 * Created by usuario on 10/11/16.
 */

public interface ISignUpMvp extends IValidateUser {
    int CORRECT = 0;
    int USER_EMPTY = 1;
    int EMAIL_EMPTY = 2;
    int PASS_EMPTY = 3;
    int COUNTY_EMPTY = 4;
    int CITY_EMPTY = 5;
    int BUSINESS_NAME_EMPTY = 6;
    int PRIVACY_FALSE = 7;

    interface View{
        void setMessageError(String messageError, int idView);
    }
    interface Presenter{

        //int validateCredentialsLogin(String user, String email, String pass, String county, String city, boolean isBusinessType, String businessName, boolean privacyAccepted);
    }
}
