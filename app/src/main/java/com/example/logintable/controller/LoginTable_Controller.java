package com.example.logintable.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by usuario on 5/10/16.
 */

public class LoginTable_Controller {

    public static final int CORRECT = 0;
    public static final int PASSWORD_DIGIT = 1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;
    private static final int MINLEGTH = 8;
    private String user;
    private String pass;

    public int validateCredentials(String user, String pass) {
        this.user = user;
        this.pass = pass;
        int result = 0;
        Boolean numericMatch, uppercaseMatch, lowercaseMatch, minlength;
        minlength = this.pass.length() < MINLEGTH;
        numericMatch = Pattern.matches(".*[0-9]+.*",this.pass);
        uppercaseMatch = Pattern.matches(".*[a-z]+.*",this.pass);
        lowercaseMatch = Pattern.matches(".*[A-Z]+.*",this.pass);
        if (minlength)
            result = PASSWORD_LENGTH;
        else if (!numericMatch)
            result = PASSWORD_DIGIT;
        else if (!uppercaseMatch)
            result = PASSWORD_CASE;
        else if (!lowercaseMatch)
            result = PASSWORD_CASE;
        return result;
    }
}
