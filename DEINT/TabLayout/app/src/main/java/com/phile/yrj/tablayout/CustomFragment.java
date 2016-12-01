package com.phile.yrj.tablayout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CustomFragment extends Fragment {
    public static final String KEY_REG_TEXT = "text";

    public CustomFragment() {

    }

    public static CustomFragment newInstance(Bundle args) {
        CustomFragment fragment = new CustomFragment();
        if (args != null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        TextView tvFragment = (TextView) view.findViewById(R.id.tvFragment);
        tvFragment.setText(getArguments().getString(KEY_REG_TEXT));
        return view;
    }
        @Override
    public void onDetach() {
        super.onDetach();
    }

}
