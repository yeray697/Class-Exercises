package com.example.yrj.manageproductrecycler.interfaces;

import android.util.Patterns;
import com.example.yrj.manageproductrecycler.model.Error;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateUser extends IValidateAccount {


    interface PresenterUser{
        int validateEmail(String email);
        /*static int validateEmail(String email){
            int result = Error.OK;
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = Error.EMAIL_INVALIDATE;
            return result;
        }*/

    }
}
