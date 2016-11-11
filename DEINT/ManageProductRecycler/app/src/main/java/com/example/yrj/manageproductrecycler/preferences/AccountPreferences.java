package com.example.yrj.manageproductrecycler.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.yrj.manageproductrecycler.interfaces.IPreferences;

/**
 * Created by usuario on 10/11/16.
 */

public class AccountPreferences implements IPreferences{
    private static AccountPreferences accountPreferences;
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private SharedPreferences sharedPreferences;

    private AccountPreferences(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    //Singleton
    public static IPreferences getInstance(Context context){
        if (accountPreferences == null){
            accountPreferences = new AccountPreferences(context);
        }
        return accountPreferences;
    }

    public void putUser(String user){
        getEditor().putString(USER, user).apply();
    }

    public void putPassword(String password){
        getEditor().putString(PASSWORD, password).apply();
    }

    public void putEmail(String email){
        getEditor().putString(EMAIL, email).apply();
    }

    private SharedPreferences.Editor getEditor(){
        return sharedPreferences.edit();
    }
}
