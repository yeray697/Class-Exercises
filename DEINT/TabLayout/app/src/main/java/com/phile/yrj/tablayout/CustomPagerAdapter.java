package com.phile.yrj.tablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by usuario on 30/11/16.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public CustomPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        CustomFragment fragment = null;
        Bundle bundle = new Bundle();
        switch (position){
            case 0:
                bundle.putString(CustomFragment.KEY_REG_TEXT,"Tab 1");
                fragment = CustomFragment.newInstance(bundle);
                break;
            case 1:
                bundle.putString(CustomFragment.KEY_REG_TEXT,"Tab 2");
                fragment = CustomFragment.newInstance(bundle);
                break;
            case 2:
                bundle.putString(CustomFragment.KEY_REG_TEXT,"Tab 3");
                fragment = CustomFragment.newInstance(bundle);
                break;
            case 3:
                bundle.putString(CustomFragment.KEY_REG_TEXT,"Tab 4");
                fragment = CustomFragment.newInstance(bundle);
                break;
            case 4:
                bundle.putString(CustomFragment.KEY_REG_TEXT,"Tab 5");
                fragment = CustomFragment.newInstance(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return this.tabCount;
    }
}
