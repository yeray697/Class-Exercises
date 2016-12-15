package com.phile.yrj.favsports;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.phile.yrj.favsports.adapter.CustomListAdapter;
import com.phile.yrj.favsports.model.Sport;

import java.util.Map;

public class Main_Activity extends AppCompatActivity {

    Button btOk;
    ListView lvSports;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSports = (ListView) findViewById(R.id.lvSports);
        getSavedSports();
        adapter = new CustomListAdapter(this);
        lvSports.setAdapter(adapter);
        if (savedInstanceState != null)
            adapter.setList(((CustomListAdapter) savedInstanceState.getSerializable("list")).getList());
        btOk = (Button) findViewById(R.id.btOk);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFavSports();
            }
        });
    }

    /**
     * Get the preferences and call a method to set it on the list
     */
    private void getSavedSports() {
        SharedPreferences sharedPreferences = getSharedPreferences("sports",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("saved",false))
            Repository.getInstance().setFavSports(sharedPreferences);
    }

    /**
     * Save the list preferences
     */
    private void saveFavSports() {
        SharedPreferences.Editor editor = getSharedPreferences("sports",MODE_PRIVATE).edit();
        for (Sport sportAux: Repository.getInstance().getSports()) {
            editor.putBoolean(sportAux.getName(),sportAux.isFavorite());
        }
        editor.putBoolean("saved", true).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            search();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that open a dialog to filter the list
     */
    private void search() {
        final LayoutInflater inflater = getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = inflater.inflate(R.layout.search,null);
        builder.setTitle(R.string.search_dialog_title);
        //builder.setView();
        builder.setView(view);
        final EditText etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = etSearch.getText().toString();
                if (text.length() != 0) {
                    if (text.length() > 1) {
                        text = text.substring(0, 1);
                        etSearch.setText(text);
                        etSearch.setSelection(1);
                    } else if (!text.matches("[a-zA-Z]")) {
                        text = "";
                        etSearch.setText(text);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                adapter.filterByChar(etSearch.getText().toString());
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("list",adapter);
    }
}
