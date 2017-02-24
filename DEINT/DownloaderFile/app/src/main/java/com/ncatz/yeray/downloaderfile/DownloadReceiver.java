package com.ncatz.yeray.downloaderfile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by usuario on 16/02/17.
 */

public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,R.string.download_file,Toast.LENGTH_SHORT).show();
    }
}
