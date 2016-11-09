package com.example.yrj.manageproductrecycler;

import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    Spinner spCounty;
    Spinner spCity;
    RadioGroup rgType;
    Button btSubmit;
    TextInputLayout tilBusiness;
    ArrayAdapter<CharSequence> adapterCounty, adapterCity;

    private AdapterView.OnItemSelectedListener spinnerListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spCity = (Spinner) findViewById(R.id.spCity);
        spCounty = (Spinner) findViewById(R.id.spCounty);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        tilBusiness = (TextInputLayout) findViewById(R.id.tilBusiness);
        rgType = (RadioGroup) findViewById(R.id.rgType);
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbPart:
                        showCompany(false);
                        break;
                    case R.id.rbBusiness:
                        showCompany(true);
                        break;
                }
            }
        });
        loadSpinnerCounty();

    }

    private void showCompany(boolean b) {
        if (b)
            tilBusiness.setVisibility(View.VISIBLE);
        else
            tilBusiness.setVisibility(View.GONE);
    }

    private void signUp(View v) {

    }

    private void loadSpinnerCounty(){
        adapterCounty = ArrayAdapter.createFromResource(this,R.array.provincias,android.R.layout.simple_spinner_dropdown_item);
        spCounty.setAdapter(adapterCounty);

        spCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypedArray ids = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);

                String[] data = getResources().getStringArray(ids.getResourceId(position, -1));

                ids.recycle();
                adapterCity = new ArrayAdapter<CharSequence>(SignUpActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, data);
                spCity.setAdapter(adapterCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
 }
