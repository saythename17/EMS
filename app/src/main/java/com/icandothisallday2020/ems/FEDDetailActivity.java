package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FEDDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_e_d_detail);
        Toolbar toolbar = findViewById(R.id.edItemToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

//        PieChart chart=(PieChart) findViewById(R.id.pieChart);
//        ArrayList no=new ArrayList();
//
//        no.add(new Entry(945f,0));
//        no.add(new Entry(1040f,1));
//        no.add(new Entry(1133f,3));
//        PieDataSet dataSet=new PieDataSet(no,"Emotion");
//
//        ArrayList emo=new ArrayList();
//        emo.add("Angry");
//        emo.add("sad");
//        emo.add("Happy");
//        PieData data=new PieData(emo,dataSet);
//        chart.setData(data);
//        dataSet.setColor(R.color.colorAccent);
//        chart.animateXY(2000,2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.close:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
