package com.ncatz.yeray.telefonbroadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

/**
 * Created by usuario on 15/02/17.
 */
public class Call_Broadcast extends BroadcastReceiver {

    private final int CALL_NOTIFICATION = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Intent newIntent = new Intent(context, Call_Activity.class);
                newIntent.putExtra("number",number);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,CALL_NOTIFICATION,intent,PendingIntent.FLA);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("RING RING");
                builder.setContentText("Llamada del número " + number);
                builder.setSmallIcon(R.mipmap.ic_launcher);

                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                builder.setDefaults(Notification.DEFAULT_LIGHTS);

                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(CALL_NOTIFICATION,builder.build());
            }
        }
    }
}
