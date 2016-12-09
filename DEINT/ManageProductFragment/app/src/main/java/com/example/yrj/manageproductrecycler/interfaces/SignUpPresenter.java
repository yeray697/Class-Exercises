package com.example.yrj.manageproductrecycler.interfaces;

/**
 * Created by usuario on 11/11/16.
 */

public interface SignUpPresenter extends LoginPresenter {
        int validateEmail(String email);
        /*static int validateEmail(String email){
            int result = Error.OK;
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = Error.EMAIL_INVALIDATE;
            return result;
        }*/
}
