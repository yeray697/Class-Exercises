package com.example.yrj.manageproductrecycler.interfaces;

import android.text.TextUtils;

import com.example.yrj.manageproductrecycler.R;

import java.util.regex.Pattern;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateAccount{
    int OK = 0;
    int PASSWORD_DIGIT = 11;
    int PASSWORD_CASE = 12;
    int PASSWORD_LENGTH = 13;
    int DATA_EMPTY = 14;

    int MINLENGTH = 8;

    interface View {
        void setMessageError(int idMessageError, int idView);
    }
    interface Presenter {
        static int validateUser(String user) {
            int result = OK;
            int idErrorResource = -1;
            int idView = R.id.tilUser;

            if (TextUtils.isEmpty(user)) {
                idErrorResource = R.string.data_empty_user;
                result = DATA_EMPTY;
            }

            return result;
        }

        static int validatePass(String pass) {
            int result = OK;
            Boolean numericMatch, uppercaseMatch, lowercaseMatch, minLength;
            //Boolean conditions
            minLength = pass.length() < IValidateAccount.MINLENGTH;
            numericMatch = Pattern.matches(".*[0-9]+.*", pass);
            uppercaseMatch = Pattern.matches(".*[a-z]+.*", pass);
            lowercaseMatch = Pattern.matches(".*[A-Z]+.*", pass);

            //Checking conditions

            if (TextUtils.isEmpty(pass)) {
                result = DATA_EMPTY;
            } else if (minLength) {
                result = PASSWORD_LENGTH;
            } else if (!numericMatch) {
                result = PASSWORD_DIGIT;
            } else if (!uppercaseMatch) {
                result = PASSWORD_CASE;
            } else if (!lowercaseMatch) {
                result = PASSWORD_LENGTH;
            }

            return result;
        }
    }
}
