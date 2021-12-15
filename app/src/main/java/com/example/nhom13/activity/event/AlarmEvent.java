package com.example.nhom13.activity.event;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.example.nhom13.R;

public class AlarmEvent extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String text = bundle.getString("name");
        String date = bundle.getString("date") + " " + bundle.getString("time");
        Intent intent1 = new Intent(context, EventActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        NotificationManager m = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notify_001");
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Time Reminder")
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setOnlyAlertOnce(true)
                .build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            m.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        m.notify(1, builder.build());
    }
}
