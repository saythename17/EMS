package com.icandothisallday2020.ems;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FCMReceiveService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("TAG","message received");

        String fromWho=remoteMessage.getFrom();

        String title="EMS";//Default title
        String body="How was your day? Write down on your diary!";//Default body
        if(remoteMessage.getNotification()!=null){
            title=remoteMessage.getNotification().getTitle();
            body=remoteMessage.getNotification().getBody();
        }

        Map<String,String> data=remoteMessage.getData();
        String name="",msg="";
        if(data!=null){
            name=data.get("name");
            msg=data.get("msg");
        }

        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("ems","ems_message",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder=new NotificationCompat.Builder(this,"ems");
        }else  builder=new NotificationCompat.Builder(this,null);

        builder.setSmallIcon(R.drawable.ic_heartsignal_color);
        builder.setContentTitle(title);
        builder.setContentText(body);

        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        Notification notification=builder.build();
        manager.notify(111,notification);

    }


//    FirebaseInstanceId instanceId=FirebaseInstanceId.getInstance();
//        instanceId.getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//        @Override
//        public void onComplete(@NonNull Task<InstanceIdResult> task) {
//            String token=task.getResult().getToken();
//            //Logcat 창에 token 값 출력
//            Log.i("token",token);
//            String real_token="fFj3jtKgF4A:APA91bFF0R0mkazct77TI5czkystykXLf-mt9x-Fj7ihhmY1WFPKaBFv7TZMJZ5v6rVSuwdRClhSQEBzjDlYQz_nxX8uVcQ3TC1UtETeBgmyTFOVXEfRq5R6ToTRLMwqYS2RhLsvJjKd";
//            //실무에서는 이 token 값을 본인의 웹서버(soon0.dothome)에 전송하여 웹 DB에 token 값 저장
//        }
//    });

}