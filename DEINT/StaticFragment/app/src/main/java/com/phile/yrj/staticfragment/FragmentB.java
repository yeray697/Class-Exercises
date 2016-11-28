package com.phile.yrj.staticfragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {
    TextView tvFragment2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_fragment_b,container,false);
        if (rootView != null) {
            tvFragment2 = (TextView) rootView.findViewById(R.id.tvFragment2);
        }
        return rootView;
    }

    public void changeTextView(String text, int size) {
        tvFragment2.setText(text);
        tvFragment2.setTextSize(size);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            tvFragment2.setText(savedInstanceState.getString("textview_text"));
            tvFragment2.setTextSize(savedInstanceState.getFloat("textview_textsize") / getResources().getDisplayMetrics().scaledDensity);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textview_text",tvFragment2.getText().toString());
        outState.putFloat("textview_textsize",tvFragment2.getTextSize());
    }
}
