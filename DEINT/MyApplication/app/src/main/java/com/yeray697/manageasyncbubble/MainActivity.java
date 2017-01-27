package com.yeray697.manageasyncbubble;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements HiddenFragment.TasksCallback {

    private static final String FRAGMENT_TAG = "FRAGMENT_HIDDEN";
    TextView tvProgress;
    Button btInit, btCancel;

    HiddenFragment fragment;
    ProgressBar progressBar;

    public static final int LENGTH = 20000;
    public static int numbers[] = new int[LENGTH];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        btInit = (Button) findViewById(R.id.btInit);
        btCancel = (Button) findViewById(R.id.btCancel);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        tvProgress.setFreezesText(true);
        btInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        FragmentManager fm = getFragmentManager();
        fragment = (HiddenFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = new HiddenFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(fragment,FRAGMENT_TAG);
            ft.commit();
        }
        if (fragment.isRunning()){
            btInit.setEnabled(false);
            btCancel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            tvProgress.setText("");
        }
    }

    private int[] generatedNumber() {
        Random rnd = new Random();
        for (int i = 0; i < LENGTH; i++)
            numbers[i] = rnd.nextInt();
        return numbers;
    }

    private void start() {
        fragment.setNumbers(generatedNumber());
        fragment.execute();
    }

    private void cancel() {
        fragment.cancel();
    }

    @Override
    public void onPreExecute() {
        btInit.setEnabled(false);
        btCancel.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        tvProgress.setText("");
    }

    @Override
    public void onProgressUpdate(final int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onCancelled() {
        btInit.setEnabled(true);
        btCancel.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        progressBar.setProgress(0);
        tvProgress.setText("Cancelado...");
    }

    @Override
    public void onPostExecute(int time) {
        btInit.setEnabled(true);
        btCancel.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        progressBar.setProgress(0);
        tvProgress.setText("Ha tardado " + time + " segundos");
    }
}
