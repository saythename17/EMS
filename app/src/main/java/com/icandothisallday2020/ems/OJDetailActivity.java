package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OJDetailActivity extends AppCompatActivity {
    int position;
    String date;
    OJDetailAdapter adapter;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oj_detail);
        Toolbar toolbar = findViewById(R.id.ojItemToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");



        Intent intent=getIntent();
//        position=intent.getIntExtra("Position",-1);
//        if(position==-1){//-1:선택이 잘못된 상황
//            finish();
//            return;
//        }

        date=intent.getStringExtra("Date");
        Toast.makeText(this, "OJDetailActivity:"+date, Toast.LENGTH_SHORT).show();
        for(int i=0;i<G.ojItems.size();i++){
            String itemDate=(G.ojItems.get(i).year)+(G.ojItems.get(i).month)+(G.ojItems.get(i).day);
            if(date.equals(itemDate)) position=i;
        }





        pager=findViewById(R.id.ojPager);
        adapter=new OJDetailAdapter(getLayoutInflater());
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_oj,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.close:
                finish();
                break;
            case R.id.edit:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
