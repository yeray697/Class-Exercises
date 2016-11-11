package com.example.yrj.manageproductrecycler.interfaces;

import android.util.Patterns;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateUser extends IValidateAccount {

    int EMAIL_INVALIDATE = 15;

    interface Presenter{
        static int validateEmail(String email){
            int result = OK;
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = EMAIL_INVALIDATE;
            return result;
        }
    }
}
