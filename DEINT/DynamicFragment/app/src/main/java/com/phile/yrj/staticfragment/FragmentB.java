package com.phile.yrj.staticfragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {
    public static final String TV_TEXT = "textview_text";
    public static final String TV_TEXTSIZE = "textview_textsize";
    public static final String TAG_FRAGMENTB = "fragmentb";
    TextView tvFragment2;

    public static FragmentB newInstance(Bundle args) {
        FragmentB fragment = new FragmentB();
        if (args != null)
            fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_fragment_b,container,false);
        if (rootView != null) {
            tvFragment2 = (TextView) rootView.findViewById(R.id.tvFragment2);
            Bundle bundle = getArguments();
            if (bundle != null) {
                tvFragment2.setText(bundle.getString(TV_TEXT));
                tvFragment2.setTextSize(bundle.getInt(TV_TEXTSIZE));
            }
        }
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            tvFragment2.setText(savedInstanceState.getString(TV_TEXT));
            tvFragment2.setTextSize(savedInstanceState.getInt(TV_TEXTSIZE));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TV_TEXT,tvFragment2.getText().toString());
        outState.putFloat(TV_TEXTSIZE,tvFragment2.getTextSize());
    }
}
