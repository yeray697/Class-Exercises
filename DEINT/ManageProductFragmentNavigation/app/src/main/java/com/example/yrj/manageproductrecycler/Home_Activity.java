package com.example.yrj.manageproductrecycler;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_nav);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_hamburguer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mActionBarDrawerToggle = setupDrawerToggle();
        setupDrawerContent();
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        if (savedInstanceState == null){
            listProduct_fragment = new MultiListProduct_Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_home, listProduct_fragment).commit();
        }
        //listProduct_fragment = new ListProduct_Fragment();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close);
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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();

        } else if (getSupportFragmentManager().getBackStackEntryCount() != 0)
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

    private void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.action_products:
                        break;
                    case R.id.action_pharmacy:
                        break;
                    default:
                        item.setChecked(false);
                }
                drawerLayout.closeDrawers();
                setTitle(item.getTitle());
                return true;
            }
        });
    }

    /*private void selectItemDrawer(MenuItem itemDrawer) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = "";
        Bundle args;
        switch (itemDrawer.getItemId()) {
            case R.id.action_home:
                tag = "home";
                fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = Tracing_Fragment.newInstance(args);
                }
                break;
            case R.id.action_products:
                tag = "products";
                fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    args = new Bundle();
                    args.putParcelable(KID_KEY, kid);
                    fragment = Calendar_Fragment.newInstance(args);
                }
                break;
            case R.id.action_pharmacy:
                tag = "pharmacy";
                fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    args = new Bundle();
                    args.putParcelable(KID_KEY, kid);
                    fragment = Chat_Fragment.newInstance(args);
                }
                break;
            case R.id.action_invoice:
                tag = "invoice";
                fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    args = new Bundle();
                    args.putParcelable(KID_KEY, kid);
                    fragment = Profile_Fragment.newInstance(args);
                }
                break;
            case R.id.action_help:
                tag = "help";
                fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    args = new Bundle();
                    args.putParcelable(KID_KEY, kid);
                    fragment = WebCam_Fragment.newInstance(args);
                }
                break;
            case R.id.action_config:
                tag = "config";
                fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    args = new Bundle();
                    args.putParcelable(KID_KEY, kid);
                    fragment = Contact_Fragment.newInstance(args);
                }
                break;
        }
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_home, fragment,tag)
                    .commit();
        }
    }*/
}
