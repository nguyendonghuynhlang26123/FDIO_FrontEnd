package com.team3.fdiosystem.repositories.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.team3.fdiosystem.Constant;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.DemoActivity;

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

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        LocalStorage.saveData(DemoActivity.getContext(), Constant.TOKEN, s);
    }

    public void debug() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        final String TAG = "TOKEN";
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        LocalStorage.saveData(DemoActivity.getContext(), Constant.TOKEN, token);
                    }
                });
    }

    private void showNotification(String title, String body){
        //debug();
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
                .setOnlyAlertOnce(false)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body));

        notiManager.notify(0, notiBuilder.build());
        Intent intent = new Intent("myFunction");
        // add data
        intent.putExtra("value1", title);
        intent.putExtra("value2", body);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
