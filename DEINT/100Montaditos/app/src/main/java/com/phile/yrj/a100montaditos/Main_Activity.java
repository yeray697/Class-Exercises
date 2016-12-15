package com.phile.yrj.a100montaditos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.phile.yrj.a100montaditos.adapter.CustomAdapter;
import com.phile.yrj.a100montaditos.model.Montadito;

import java.util.ArrayList;
import java.util.List;

public class Main_Activity extends ListActivity {
    CustomAdapter adapter;
    boolean paying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MontaditosPreferences.getInstance(this).getAmounts();
        adapter = new CustomAdapter(this);
        setListAdapter(adapter);
        ((FloatingActionButton) findViewById(R.id.fabPay)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Montadito> montaditosBought = ((Montaditos_Application)getApplicationContext()).getMontaditosBougth();
                if (montaditosBought.size() != 0) {
                    paying = true;
                    Intent intent = new Intent(Main_Activity.this, Pay_Activity.class);
                    intent.putParcelableArrayListExtra("payed", (ArrayList<? extends Parcelable>) montaditosBought);
                    startActivity(intent);
                }
            }
        });
        registerForContextMenu(getListView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (paying) {
            ((Montaditos_Application)getApplicationContext()).addInitialMontaditos();
            MontaditosPreferences.getInstance(this).setInitialAmounts();
            adapter.notifyDataSetChanged();
            paying = false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}
