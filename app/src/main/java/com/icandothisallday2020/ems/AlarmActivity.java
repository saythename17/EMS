package com.icandothisallday2020.ems;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    TimePicker timePicker;
    PendingIntent pendingIntent;
    Intent intent;
    CheckBox mon,tue,wen,thr,fri,sat,sun;
    ComponentName receiver;
    PackageManager pm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        mon=findViewById(R.id.cb_mon);
        tue=findViewById(R.id.cb_tues);
        wen=findViewById(R.id.cb_wed);
        thr=findViewById(R.id.cb_thurs);
        fri=findViewById(R.id.cb_fri);
        sat=findViewById(R.id.cb_sat);
        sun=findViewById(R.id.cb_sun);
        //TODO :  Weekly alarm set

        receiver = new ComponentName(getBaseContext(),AlarmReciever.class);
        pm = this.getPackageManager();


        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker=findViewById(R.id.edit_alarm_time_picker);
        intent=new Intent(AlarmActivity.this,AlarmReciever.class);
    }

    public void close(View view) { finish(); return;  }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void save(View view) {
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
        c.set(Calendar.MINUTE,timePicker.getMinute());


        int hour=timePicker.getHour();
        int minute=timePicker.getMinute();
        Toast.makeText(AlarmActivity.this,"Set Custom Alarm",Toast.LENGTH_SHORT).show();

        intent.putExtra("state","on");
        pendingIntent=PendingIntent.getBroadcast(AlarmActivity.this,0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);

        boolean mon=this.mon.isChecked();
        boolean tue=this.tue.isChecked();
        boolean wen=this.wen.isChecked();
        boolean thr=this.thr.isChecked();
        boolean fri=this.fri.isChecked();
        boolean sat=this.sat.isChecked();
        boolean sun=this.sun.isChecked();





        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);


//        Log.i("log1111","INTERVAL:"+(AlarmManager.INTERVAL_DAY*7));
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
//                1000 * 60 * 20, pendingIntent);//INTERVAL:604800000
//        Log.i("log1111","NUM:"+(1000*60*20));//NUM:1200000

//        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);



        finish(); return;
    }

    public void deleteAlarm(View view){
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Toast.makeText(this, "Delete Alarm", Toast.LENGTH_SHORT).show();
        alarmManager.cancel(pendingIntent);
        intent.putExtra("state","off");
        sendBroadcast(intent);

    }
}
