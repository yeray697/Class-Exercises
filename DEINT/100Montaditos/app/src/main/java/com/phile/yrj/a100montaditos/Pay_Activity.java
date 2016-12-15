package com.phile.yrj.a100montaditos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phile.yrj.a100montaditos.model.Montadito;

import java.util.ArrayList;

public class Pay_Activity extends AppCompatActivity {
    ArrayList<Montadito> montaditos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        montaditos = getIntent().getExtras().getParcelableArrayList("payed");
    }
}
