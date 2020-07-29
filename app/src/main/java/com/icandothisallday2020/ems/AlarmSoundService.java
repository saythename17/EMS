package com.icandothisallday2020.ems;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmSoundService extends Service {
    MediaPlayer player;
    boolean isRun;
    String state;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT>=26){
            String ID="default";
            NotificationChannel channel=new NotificationChannel(ID,
                    "Self Producing Sound",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager=((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
            manager.createNotificationChannel(channel);

            Uri uri= Uri.parse("android.resources://"+getPackageName()+"/"+R.raw.get_coin);
            Intent intent=new Intent(this,WriteOJActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(
                    this,17,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification=new NotificationCompat.Builder(this,ID)
                    .setContentTitle("EMS Alarm")
                    .setContentText("Custom alarm starts.")
                    .setSmallIcon(R.drawable.e15_want)
                    .setVibrate(new long[]{0,2000,1000,2000,1000})
                    .setSound(uri)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true).build();
            startForeground(1,notification);

            manager.notify(17,notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        state =intent.getExtras().getString("state");

        assert  state !=null;
        if(!this.isRun && state.equals("on")){
            player=MediaPlayer.create(this,R.raw.alarm);
            player.start();
            this.isRun=true;
        }else if(this.isRun && state.equals("off")){
            player.stop();
            player.release();
            player=null;
            this.isRun = false;
        }else{
            return  START_NOT_STICKY;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
