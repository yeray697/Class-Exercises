package com.ncatz.yeray.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by usuario on 17/02/17.
 */

public class BoundService extends Service {
    private IBinder mBinder = new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private class MyBinder extends Binder {
        BoundService getService(){
            return BoundService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mChronometer.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
