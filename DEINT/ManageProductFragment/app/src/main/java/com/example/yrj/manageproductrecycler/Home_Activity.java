package com.example.yrj.manageproductrecycler;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yrj.manageproductrecycler.interfaces.ManageProductListener;

public class Home_Activity extends AppCompatActivity implements ManageProductListener,ListProduct_Fragment.ListProductListener{
    private ListProduct_Fragment listProduct_fragment;
    private ManageProduct_Fragment manageProduct_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listProduct_fragment = new ListProduct_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_home, listProduct_fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_settings_account:
                break;
            case R.id.action_settings_general:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProduct_fragment = new ManageProduct_Fragment(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_home, manageProduct_fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onListProductListener() {

    }
}
