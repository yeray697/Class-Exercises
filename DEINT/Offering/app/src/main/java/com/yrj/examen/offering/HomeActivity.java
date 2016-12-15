package com.yrj.examen.offering;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * This activity show offers' configuration
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class HomeActivity extends AppCompatActivity {

    public static final String HOME = "home";
    public static final String ELECTRONIC = "electronic";
    public static final String SPORT = "sport";
    public static final String PRIORITY = "priority";

    TextView tvTitle;
    CheckBox cbHome, cbElectronic, cbSport, cbPriority;
    Button btFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        cbHome = (CheckBox) findViewById(R.id.cbHomeOffers);
        cbElectronic = (CheckBox) findViewById(R.id.cbElectronicOffers);
        cbSport = (CheckBox) findViewById(R.id.cbSportsOffers);
        cbPriority = (CheckBox) findViewById(R.id.cbPriorityOffers);
        btFilter = (Button) findViewById(R.id.btFilter);
        btFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });
        InputStream is = null;
        try {
            //No sé ponerle la fuente
            is = getAssets().open("gloriahallelujah.ttf");
            AssetFileDescriptor asset = getAssets().openNonAssetFd("gloriahallelujah.ttf");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Method that show ListOffers_Activity and send CheckBoxs' values
     */
    private void filter() {
        boolean home, electronic, sport, priority;
        home = cbHome.isChecked();
        electronic = cbElectronic.isChecked();
        sport = cbSport.isChecked();
        priority = cbPriority.isChecked();
        if (home || electronic || sport) {
            Intent intent = new Intent(HomeActivity.this, ListOffers_Activity.class);
            intent.putExtra(HOME, home);
            intent.putExtra(ELECTRONIC, electronic);
            intent.putExtra(SPORT, sport);
            intent.putExtra(PRIORITY, priority);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_settings, Toast.LENGTH_SHORT).show();
        }
    }
}
