package com.yeray697.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

/**
 * Created by usuario on 19/01/17.
 */

public class ColorMixer extends RelativeLayout {
    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private View swatch;
    private int color;
    private OnColorChangedListener mCallback;

    private SeekBar.OnSeekBarChangeListener listener;

    public interface OnColorChangedListener {
        void OnColorChanged(int color);
    }

    public ColorMixer(Context context) {
        super(context);
        inflatingView(null);
    }

    public ColorMixer(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflatingView(attrs);
    }

    private void inflatingView(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.mixer,this,true);
        red = (SeekBar) findViewById(R.id.red);
        green = (SeekBar) findViewById(R.id.green);
        blue = (SeekBar) findViewById(R.id.blue);
        swatch = findViewById(R.id.swatch);

        red.setMax(0xFF);
        green.setMax(0xFF);
        blue.setMax(0xFF);

        if (attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.ColorMixer, 0, 0);
            color = a.getColor(R.styleable.ColorMixer_initColor,Color.rgb(0,0,0));
            a.recycle();
        } else {
            color = Color.rgb(0,0,0);
        }
        updateSwatch(color);
        listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateSwatch();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        red.setOnSeekBarChangeListener(listener);
        green.setOnSeekBarChangeListener(listener);
        blue.setOnSeekBarChangeListener(listener);
    }

    private void updateSwatch() {
        color = Color.rgb(red.getProgress(),green.getProgress(),blue.getProgress());
        swatch.setBackgroundColor(color);
        if (mCallback != null)
            mCallback.OnColorChanged(color);
    }

    private void updateSwatch(int color) {
        swatch.setBackgroundColor(color);
        red.setProgress(Color.red(color));
        green.setProgress(Color.green(color));
        blue.setProgress(Color.blue(color));
        if (mCallback != null)
            mCallback.OnColorChanged(color);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        updateSwatch(color);
    }

    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.mCallback = listener;
    }
}
