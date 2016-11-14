package com.example.yrj.manageproductrecycler.interfaces;

import android.content.Intent;
import android.text.TextUtils;

import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.model.Error;

import java.util.regex.Pattern;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateAccount{
    int MINLENGTH = 8;

    interface View {
        void setMessageError(String nameResource, int idView);
        void startActivity(Intent intent);
    }
    interface Presenter {
        static int validateUser(String user) {
            int result = Error.OK;
            int idErrorResource = -1;
            int idView = R.id.tilUser;

            if (TextUtils.isEmpty(user)) {
                idErrorResource = R.string.data_empty_user;
                result = Error.DATA_EMPTY;
            }

            return result;
        }

        static int validatePass(String pass) {
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
                result = Error.PASSWORD_LENGTH;
            }

            return result;
        }

    }
}
