package com.example.yrj.dynamictable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DynamicTable_Activity extends AppCompatActivity {

    private TableLayout tlHeader, tlBody;
    private TableRow.LayoutParams trLayoutId, trLayoutName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_table);
        //Setting controls
        tlHeader = (TableLayout) findViewById(R.id.tlHeader);
        tlBody = (TableLayout) findViewById(R.id.tlBody);
        //Setting row style
        trLayoutId = new TableRow.LayoutParams(130, TableRow.LayoutParams.WRAP_CONTENT);
        trLayoutId.setMargins(0,10,0,10);
        trLayoutName.setMargins(0,10,0,10);
        trLayoutName = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        createHeader();
        createBody();
    }

    private void createBody() {
        String[] idDefault = getResources().getStringArray(R.array.idDefault);
        String[] nameDefault = getResources().getStringArray(R.array.nameDefault);
        TableRow trHeader;
        for (int i = 0; i < idDefault.length; i++) {
            trHeader = new TableRow(this);
            //Adding id TextView
            TextView tvId = new TextView(this);
            tvId.setText(String.valueOf(idDefault[i]));
            tvId.setLayoutParams(trLayoutId);
            //Adding name TextView
            TextView tvName = new TextView(this);
            tvName.setText(nameDefault[i]);
            tvName.setLayoutParams(trLayoutName);
            //Adding both to a TableRow
            trHeader.addView(tvId);
            trHeader.addView(tvName);
            //Adding TableRow to the TableLayout
            tlHeader.addView(trHeader);
        }

    }

    private void createHeader() {
        TableRow trHeader = new TableRow(this);
        //Adding id TextView
        TextView tvId = new TextView(this);
        tvId.setText(R.string.tvIdText);
        tvId.setLayoutParams(trLayoutId);
        //Adding name TextView
        TextView tvName = new TextView(this);
        tvName.setText(R.string.tvNameText);
        tvName.setLayoutParams(trLayoutName);
        //Adding both to a TableRow
        trHeader.addView(tvId);
        trHeader.addView(tvName);
        //Adding TableRow to the TableLayout
        tlHeader.addView(trHeader);
    }
}
