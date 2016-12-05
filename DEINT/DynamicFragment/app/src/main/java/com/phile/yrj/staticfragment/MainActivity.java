package com.phile.yrj.staticfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements FragmentA.FragmentIterationListener{
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        fragmentA = (FragmentA) fm.findFragmentByTag(FragmentA.TAG_FRAGMENTA);
        if (fragmentA == null) {
            fragmentA = new FragmentA();
            getFragmentManager().beginTransaction().add(R.id.activity_main, fragmentA, FragmentA.TAG_FRAGMENTA).commit();
        }
    }

    @Override
    public void onFragmentIterationListener(String text, int size) {
        Bundle args = new Bundle();
        args.putString(FragmentB.TV_TEXT, text);
        args.putInt(FragmentB.TV_TEXTSIZE, size);
        fragmentB = FragmentB.newInstance(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main,fragmentB, FragmentB.TAG_FRAGMENTB);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
