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
    public static final String FILE = "";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private static Context contextApp;

    private AccountPreferences(){

    }
    //Singleton
    public static IPreferences getInstance(Context context){
        contextApp = context;
        if (accountPreferences == null){
            accountPreferences = new AccountPreferences();
        }
        return accountPreferences;
    }

    public static void putUser(String user){
        getEditor().putString(USER, user).apply();
    }

    private static SharedPreferences.Editor getEditor(){
        SharedPreferences sharedPreferences = null;
        return sharedPreferences.edit();
    }
}
