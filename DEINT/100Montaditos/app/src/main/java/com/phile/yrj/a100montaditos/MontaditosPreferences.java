package com.phile.yrj.a100montaditos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.phile.yrj.a100montaditos.model.Montadito;

import java.util.ArrayList;

/**
 * Created by usuario on 12/12/16.
 */
public class MontaditosPreferences {
    private static final String KEY_AMOUNT = "id";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static MontaditosPreferences ourInstance;

    public static MontaditosPreferences getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new MontaditosPreferences(context);
        return ourInstance;
    }
    public static MontaditosPreferences getInstance() {
        return ourInstance;
    }

    private MontaditosPreferences(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = preferences.edit();
        this.context = context;
    }
    public void getAmounts(){
        ArrayList<Montadito> montaditos = (ArrayList<Montadito>) ((Montaditos_Application)context.getApplicationContext()).getMontaditos();
        for (Montadito aux: montaditos) {
            aux.setAmount(preferences.getInt(KEY_AMOUNT + aux.getNumber(), 0));
        }
    }
    public void setAmounts(){
        ArrayList<Montadito> montaditos = (ArrayList<Montadito>) ((Montaditos_Application)context.getApplicationContext()).getMontaditos();
        for (Montadito aux: montaditos) {
            if (aux.getAmount() > 0)
                editor.putInt(KEY_AMOUNT + aux.getNumber(), aux.getAmount()).apply();
        }
    }
    public void setInitialAmounts(){
        ArrayList<Montadito> montaditos = (ArrayList<Montadito>) ((Montaditos_Application)context.getApplicationContext()).getMontaditos();
        for (Montadito aux: montaditos) {
            editor.putInt(KEY_AMOUNT + aux.getNumber(), 0).apply();
        }
    }

    public void setAmount(int name, int amount) {
        editor.putInt(KEY_AMOUNT + name, amount).apply();
    }
}
