package com.yeray697.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvColor;
    ColorMixer colorMixer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvColor = (TextView) findViewById(R.id.tvColor);
        colorMixer = (ColorMixer) findViewById(R.id.colorMixer);
        tvColor.setText( String.format("#%06X", 0xFFFFFF & colorMixer.getColor()));
        colorMixer.setOnColorChangedListener(new ColorMixer.OnColorChangedListener() {
            @Override
            public void OnColorChanged(int color) {
                tvColor.setText( String.format("#%X", 0xFFFFFF & color));  // --> #743684 Sin el alpha
                //tvColor.setText( Integer.toHexString(color)); // --> ff743684 Con el alpha y en minúsculas
                //tvColor.setText(String.format("#%X", color)); // --> #FF743684 Con el alpha y en mayúsculas
            }
        });
    }
}
