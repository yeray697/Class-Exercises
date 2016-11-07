package com.phile.yrj.buttonresponseevent;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_Activity extends AppCompatActivity {

    Button bt;
    private View.OnLongClickListener lstOnLongClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se implementan los listeners
        lstOnLongClick = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v,"Mensaje con Action", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
                return false;
            }
        };
        //Se instancia los widget y se registran los eventos
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Se ha pulsado sobre el botón", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
