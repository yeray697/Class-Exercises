package com.ncatz.yeray.batterycontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by usuario on 13/02/17.
 */

public class Battery_Fragment extends Fragment {
    private ProgressBar pbLevel;
    private TextView tvLevel;
    private ImageView ivStatus;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            float batteryPct = level / (float)scale * 100;
            pbLevel.setProgress((int)batteryPct);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            switch (status){
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    if (level == 1) {
                        tvLevel.setText("Cargando");
                        ivStatus.setImageResource(R.drawable.charging);
                    } else {
                        tvLevel.setText("Cargado. Desenchufe");
                        ivStatus.setImageResource(R.drawable.full);
                    }
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    tvLevel.setText("Full");
                    ivStatus.setImageResource(R.drawable.full);
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    tvLevel.setText("Descargando");
                    ivStatus.setImageResource(R.drawable.unplugged);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout,container,false);
        pbLevel = (ProgressBar) rootView.findViewById(R.id.pbLevel);
        tvLevel = (TextView) rootView.findViewById(R.id.tvLevel);
        ivStatus = (ImageView) rootView.findViewById(R.id.ivStatus);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
