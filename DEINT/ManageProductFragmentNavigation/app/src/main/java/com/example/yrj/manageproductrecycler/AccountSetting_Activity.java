package com.example.yrj.manageproductrecycler;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by usuario on 2/11/16.
 */

public class AccountSetting_Activity extends PreferenceActivity{    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.account_setting);
}
}
