package com.phile.yrj.staticfragment;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements FragmentA.FragmentIterationListener{

    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentB = (FragmentB) getFragmentManager().findFragmentById(R.id.fragment2);
    }

    @Override
    public void onFragmentIterationListener(String text, int size) {
        fragmentB.changeTextView(text, size);
    }


}
