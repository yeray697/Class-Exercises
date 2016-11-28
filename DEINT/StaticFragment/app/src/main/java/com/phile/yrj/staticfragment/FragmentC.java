package com.phile.yrj.staticfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by usuario on 28/11/16.
 */

public class FragmentC extends Fragment {
    WebView wvFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_fragment_c,container,false);
        if (rootView != null) {
            wvFragment = (WebView) rootView.findViewById(R.id.wvFragment3);
            wvFragment.loadData(getResources().getString(R.string.aboutus),"text/html","UTF-8");
        }
        return rootView;
    }

}
