package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import top.dodeman.waterdropindicator.WaterDropIndicator;

public class IntroActivity extends AppCompatActivity {
    ViewPager introVP;
    IntroAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        introVP=findViewById(R.id.introVP);

        adapter=new IntroAdapter(getSupportFragmentManager());
        WaterDropIndicator indicator=findViewById(R.id.indicator);
        introVP.setAdapter(adapter);
        indicator.setViewPager(introVP);
        indicator.setItemNum(3);
    }
}
