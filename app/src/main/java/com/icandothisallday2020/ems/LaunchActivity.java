package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
        boolean login=preferences.getBoolean("Login",false);
        if(!login) {
            firstHandler.sendEmptyMessageDelayed(0,4000);
        }
        else{
            mainHandler.sendEmptyMessageDelayed(0,4000);
        }
    }
    Handler firstHandler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Intent intent=new Intent(LaunchActivity.this,IntroActivity.class);
            startActivity(intent);
            finish();
        }
    };
    Handler mainHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Intent intent=new Intent(LaunchActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
