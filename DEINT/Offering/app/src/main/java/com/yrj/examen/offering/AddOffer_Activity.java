package com.yrj.examen.offering;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.yrj.examen.offering.model.Offer;

/**
 * This activity add an offer to the list
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class AddOffer_Activity extends AppCompatActivity {
    EditText etName, etStore, etDate;
    Spinner spType, spPriority;
    Button btAdd;
    String home, sport, electronic;
    String high, medium, low;
    private String[] priorityArray;
    private String[] typeArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        home = getResources().getString(R.string.home_type);
        sport = getResources().getString(R.string.sport_type);
        electronic = getResources().getString(R.string.electronic_type);

        high = getResources().getString(R.string.high_priority);
        medium = getResources().getString(R.string.medium_priority);
        low = getResources().getString(R.string.low_priority);

        typeArray = new String[]{
                home,sport,electronic
        };
        priorityArray = new String[]{
                low,medium,high
        };
        etName = (EditText) findViewById(R.id.etName_add);
        etStore = (EditText) findViewById(R.id.etStore_add);
        etDate = (EditText) findViewById(R.id.etDate_add);
        spType = (Spinner) findViewById(R.id.spType_add);
        spPriority = (Spinner) findViewById(R.id.spPriority_add);
        btAdd = (Button) findViewById(R.id.btAdd_add);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        spPriority.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, priorityArray));
        spType.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, typeArray));
    }

    /**
     * Method that add the offer to the list
     */
    private void add() {
        String name, store, date;
        int type, priority;
        name = etName.getText().toString();
        store = etStore.getText().toString();
        date = etDate.getText().toString();

        type = spType.getSelectedItemPosition();
        switch (type){
            case 0:
                type = Offer.TYPE_HOME;
                break;
            case 1:
                type = Offer.TYPE_SPORT;
                break;
            case 2:
                type = Offer.TYPE_ELECTRONIC;
                break;
        }
        priority = spPriority.getSelectedItemPosition();
        switch (priority){
            case 0:
                priority = Offer.Importancia.PRIORITY_NOT_IMPORTANT;
                break;
            case 1:
                priority = Offer.Importancia.PRIORITY_IMPORTANT;
                break;
            case 2:
                priority = Offer.Importancia.PRIORITY_VERY_IMPORTANT;
                break;
        }
        if (((Offers_Application)getApplicationContext()).isDuplicated(name)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.dialog_title);
            dialog.setMessage(R.string.dialog_add_message);
            dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            Offer offer = new Offer(name,store,date,type,priority);
            ((Offers_Application)getApplicationContext()).addOffer(offer);
            setResult(RESULT_OK);
            finish();
        }
    }
}
