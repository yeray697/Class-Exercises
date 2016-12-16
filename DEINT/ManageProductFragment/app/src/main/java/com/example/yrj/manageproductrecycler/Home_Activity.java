package com.example.yrj.manageproductrecycler;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yrj.manageproductrecycler.interfaces.ListProductListener;
import com.example.yrj.manageproductrecycler.interfaces.ManageProductListener;

public class Home_Activity extends AppCompatActivity implements ManageProductListener,ListProductListener {
    //private ListProduct_Fragment listProduct_fragment;
    private MultiListProduct_Fragment listProduct_fragment;
    private ManageProduct_Fragment manageProduct_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //listProduct_fragment = new ListProduct_Fragment();
        listProduct_fragment = new MultiListProduct_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_home, listProduct_fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.action_settings_account:
                intent = new Intent(Home_Activity.this, AccountSetting_Activity.class);
                break;
            case R.id.action_settings_general:
                intent = new Intent(Home_Activity.this, GeneralSetting_Activity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProduct_fragment = ManageProduct_Fragment.newInstance(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_home, manageProduct_fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onListProductListener() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, listProduct_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private long lastPressedTime;
    private static final int PERIOD = 2000;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0)
            super.onBackPressed();
        else {
            if (System.currentTimeMillis() - lastPressedTime < PERIOD) {
                super.onBackPressed();
            } else {
                Toast.makeText(getApplicationContext(), "Pulsa de nuevo para salir.",
                        Toast.LENGTH_SHORT).show();
                lastPressedTime = System.currentTimeMillis();
            }
        }
    }
}
