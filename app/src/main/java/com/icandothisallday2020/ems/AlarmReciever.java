package com.icandothisallday2020.ems;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {



        String state=intent.getExtras().getString("state");
        Intent playIntent=new Intent(context,AlarmSoundService.class);
        playIntent.putExtra("state",state);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            context.startForegroundService(playIntent);
        }else{
            context.startService(playIntent);
        }

    }
}
