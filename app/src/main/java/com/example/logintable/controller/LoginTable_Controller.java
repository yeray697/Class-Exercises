package com.example.logintable.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yeray on 5/10/16.
 */

/**
 * Class that implements Login business rules:
 *  -It needs at least one uppercase and lowercase letter
 *  -It needs at least one digit
 *  -It needs to be at least greater than 8 letters
 *  @author Yeray Ruiz
 *  @version 1.0
 */
public class LoginTable_Controller implements ILoginMvc{

    public static final int CORRECT = 0;
    public static final int PASSWORD_DIGIT = 1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;
    private static final int MINLEGTH = 8;

    private String user;
    private String pass;

    /**
     * Method that check if a password comply with some conditions
     * @param user Username used to login
     * @param pass Password used to login
     * @return Return a code with the result. 0 = Correct, else, it is an error
     */
    public int validateCredentials(String user, String pass) {
        this.user = user;
        this.pass = pass;
        int result = CORRECT;
        Boolean numericMatch, uppercaseMatch, lowercaseMatch, minLength;
        //Boolean conditions
        minLength = this.pass.length() < MINLEGTH;
        numericMatch = Pattern.matches(".*[0-9]+.*",this.pass);
        uppercaseMatch = Pattern.matches(".*[a-z]+.*",this.pass);
        lowercaseMatch = Pattern.matches(".*[A-Z]+.*",this.pass);
        //Checking conditions
        if (minLength)
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
