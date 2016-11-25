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
}
