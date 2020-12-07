package com.team3.fdiosystem.repositories.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.team3.fdiosystem.R;

import java.util.Map;

public class MyFirebaseService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().isEmpty()){
            showNotification(remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody());
        } else{
            Map<String, String> data = remoteMessage.getData();
            String title = data.get("title");
            String body = data.get("body");

            showNotification(title,body);
        }
    }

    private void showNotification(String title, String body){
        NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.team3.fdiosystem.repositories.services";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notiChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "FDIO System", NotificationManager.IMPORTANCE_HIGH);

            notiChannel.setDescription("Test");
            notiChannel.enableLights(true);
            notiChannel.setLightColor(Color.BLUE);
            notiChannel.enableLights(true);
            notiManager.createNotificationChannel(notiChannel);
        }

        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notiBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Status")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setOnlyAlertOnce(true);

        notiManager.notify(0, notiBuilder.build());
        Log.d("T", "NOTI");
    }
}
