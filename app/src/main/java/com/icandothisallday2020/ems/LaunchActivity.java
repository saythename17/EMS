package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LaunchActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {

    }

    boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);




        if(isFirst) {
            firstTimeHandler.sendEmptyMessageDelayed(0,4000);
            isFirst=false;
        }
        else{
            mainHandler.sendEmptyMessageDelayed(0,4000);
        }
    }
    Handler firstTimeHandler=new Handler(){
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
