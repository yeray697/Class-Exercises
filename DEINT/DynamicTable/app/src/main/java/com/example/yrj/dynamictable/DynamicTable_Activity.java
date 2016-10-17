package com.example.yrj.dynamictable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author Yeray Ruiz
 * Creates a dynamic table getting data from an array resource
 * Resources used:
 *  Table layout
 *  Layout params
 *  Array string
 *  Colours
 *
 */
public class DynamicTable_Activity extends AppCompatActivity {

    private TableLayout tlHeader, tlBody;
    private TableRow.LayoutParams trLayoutId, trLayoutName, trLayout;

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
        trLayoutName = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        trLayoutName.setMargins(0,10,0,10);
        trLayout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        createHeader();
        createBody();
    }

    /**
     * Create the table body with a loop, getting names from an array resource
     */
    private void createBody() {
        String[] listName= getResources().getStringArray(R.array.listName);
        TableRow trBody;
        TextView tvId;
        TextView tvName;
        for (int i = 0; i < listName.length; i++) {
            trBody = new TableRow(this);
            //Adding id TextView
            tvId = new TextView(this);
            tvId.setText(String.valueOf(i+1));
            tvId.setLayoutParams(trLayoutId);
            tvId.setBackgroundResource(R.drawable.shape_table_body);
            //Adding name TextView
            tvName = new TextView(this);
            tvName.setText(listName[i]);
            tvName.setLayoutParams(trLayoutName);
            tvName.setBackgroundResource(R.drawable.shape_table_body);
            //Adding both to a TableRow
            trBody.addView(tvId);
            trBody.addView(tvName);
            //Adding TableRow to the TableLayout
            tlBody.addView(trBody);
        }
    }

    /**
     * Create the table header, with Id and Name columns
     */
    private void createHeader() {
        TableRow trHeader = new TableRow(this);
        //Adding id TextView
        TextView tvId = new TextView(this);
        tvId.setText(R.string.tvIdText);
        tvId.setLayoutParams(trLayoutId);
        tvId.setBackgroundResource(R.drawable.shape_table_header);
        //Adding name TextView
        TextView tvName = new TextView(this);
        tvName.setText(R.string.tvNameText);
        tvName.setLayoutParams(trLayoutName);
        tvName.setBackgroundResource(R.drawable.shape_table_header);
        //Adding both to a TableRow
        trHeader.addView(tvId);
        trHeader.addView(tvName);
        //Adding TableRow to the TableLayout
        tlHeader.addView(trHeader);
    }
}
