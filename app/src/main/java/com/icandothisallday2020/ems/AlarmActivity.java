package com.icandothisallday2020.ems;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    TimePicker timePicker;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker=findViewById(R.id.edit_alarm_time_picker);
    }

    public void close(View view) { finish(); return;  }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void save(View view) {
        Calendar c=Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
        c.set(Calendar.MINUTE,timePicker.getMinute());


        int hour=timePicker.getHour();
        int minute=timePicker.getMinute();
        Toast.makeText(AlarmActivity.this,"Set Custom Alarm" + hour + ": " + minute ,Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(AlarmActivity.this,AlarmReciever.class);
        intent.putExtra("state","on");
        pendingIntent=PendingIntent.getBroadcast(AlarmActivity.this,0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);



        finish(); return;

//        int year=c.get(Calendar.YEAR);
//        int month=c.get(Calendar.MONTH);
//        int day=c.get(Calendar.DAY_OF_MONTH);
//
//        GregorianCalendar calendar=new GregorianCalendar(year,month,day,
//                timePicker.getHour(),timePicker.getMinute());
//        pendingIntent=PendingIntent.getActivity(AlarmActivity.this,
//                17,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
//        }else alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
}
