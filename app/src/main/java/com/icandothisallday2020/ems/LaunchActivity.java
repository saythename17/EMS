package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import static com.kakao.util.helper.Utility.getPackageInfo;


public class LaunchActivity extends AppCompatActivity {
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        tv1=findViewById(R.id.launch_tv);
        tv2=findViewById(R.id.launch_tv2);




        ArrayList<String> passage = new ArrayList<>();
        ArrayList<String> who = new ArrayList<>();
        XmlResourceParser parser=getResources().getXml(R.xml.launch);
        boolean b=true;
        try {
                parser.next();
                int eventType=parser.getEventType();
                while (eventType!=XmlResourceParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String name=parser.getName();
                            if(name.equals("passage")) b=true;
                            else if(name.equals("who")) b=false;
                            break;
                        case XmlResourceParser.TEXT:
                            if(b) passage.add(parser.getText());
                            else who.add(parser.getText());
                            break;
                    }
                    eventType=parser.next();
                }

        } catch (Exception e) { e.printStackTrace(); }

        Random rnd=new Random();
        int random=rnd.nextInt(who.size());
        tv1.setText(passage.get(random));
        tv2.setText(who.get(random));



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
