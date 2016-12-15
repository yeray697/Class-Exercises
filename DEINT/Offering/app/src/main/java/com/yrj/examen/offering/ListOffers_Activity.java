package com.yrj.examen.offering;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.yrj.examen.offering.adapter.CustomAdapter;

/**
 * This activity list the offers
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class ListOffers_Activity extends AppCompatActivity {

    private static final String FILTER = "filter";
    private static final int ADD_CODE = 1;

    ListView lvOffers;
    FloatingActionButton fabAdd;
    CustomAdapter adapter;

    boolean home, electronic, sport, priority;
    int filter = CustomAdapter.FILTER_ASC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offers);
        if (savedInstanceState != null) {
            filter = savedInstanceState.getInt(FILTER,CustomAdapter.NO_FILTER);
        }
        Bundle bundle = getIntent().getExtras();
        //Getting booleans
        home = bundle.getBoolean(HomeActivity.HOME);
        electronic = bundle.getBoolean(HomeActivity.ELECTRONIC);
        sport = bundle.getBoolean(HomeActivity.SPORT);
        priority = bundle.getBoolean(HomeActivity.PRIORITY);
        //Setting views
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        lvOffers = (ListView) findViewById(R.id.lvOffers);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOffer();
            }
        });
        registerForContextMenu(lvOffers);
        //Adapter
        adapter = new CustomAdapter(this,home,electronic,sport,priority, filter);
        lvOffers.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_asc:
                if (filter != CustomAdapter.FILTER_ASC) {
                    filter = CustomAdapter.FILTER_ASC;
                    adapter.filterList(filter);
                }
                break;
            case R.id.action_filter_desc:
                if (filter != CustomAdapter.FILTER_DESC) {
                    filter = CustomAdapter.FILTER_DESC;
                    adapter.filterList(filter);
                }
                break;
            case R.id.action_filter_type:
                if (filter != CustomAdapter.FILTER_TYPE) {
                    filter = CustomAdapter.FILTER_TYPE;
                    adapter.filterList(filter);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FILTER,filter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.list_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.dialog_title);
            dialog.setMessage(R.string.dialog_message);
            dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }

    private void addOffer() {
        Intent intent = new Intent(ListOffers_Activity.this, AddOffer_Activity.class);
        startActivityForResult(intent,ADD_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_CODE) {
                adapter.filterList(filter);
            }
        }
    }
}
