package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.kakao.auth.KakaoSDK;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ViewPager pager;
    MainAdapter adapter;
    ImageView feed, ed, write, oj, bp, menu, menu2;

    NavigationView nv;
    LinearLayout nvHeader;

    int Hour,Min;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=findViewById(R.id.mainDrawer);
        pager=findViewById(R.id.mainPager);
        nv=findViewById(R.id.nv);

        nvHeader=nv.getHeaderView(0).findViewById(R.id.nameGroup);
        TextView userName=nv.getHeaderView(0).findViewById(R.id.nv_name);
        if(!(G.userName==null)) userName.setText(G.userName);
        ImageView userProfile=nv.getHeaderView(0).findViewById(R.id.nv_profile);
        if(!(G.uri==null)) Glide.with(this).load(G.uri).into(userProfile);






        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Hour=hourOfDay;
                Min=minute;

                Toast.makeText(MainActivity.this, "Set alarm daily at specified time \n★"+Hour+":"+Min, Toast.LENGTH_SHORT).show();
            }
        };

        nv.setItemTextColor(ColorStateList.valueOf(R.color.colorDarkGray));
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.alarm:
                        Intent intent=new Intent(MainActivity.this,AlarmActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.scrap:
                        break;
                    case R.id.my:
                        break;
                    case R.id.privacyPolicy:
                        break;
                }
                drawer.closeDrawer(nv);
                return false;
            }
        });

        menu =findViewById(R.id.menu);
        menu.setOnClickListener(menuListener);
        menu2=findViewById(R.id.menu2);
        menu2.setVisibility(View.GONE);
        menu2.setOnClickListener(menuListener);



        feed =findViewById(R.id.feed);
        feed.setOnClickListener(btnClick);

        ed =findViewById(R.id.ed);
        ed.setOnClickListener(btnClick);

        write =findViewById(R.id.write);
        write.setOnClickListener(btnClick);

        oj =findViewById(R.id.oj);
        oj.setOnClickListener(btnClick);

        bp =findViewById(R.id.bp);
        bp.setOnClickListener(btnClick);









        adapter=new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(2);
        pager.setPageTransformer(true, new CubeOutTransformer());
//        pager.setPageTransformer(true, new RotateUpTransformer());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        menu2.setVisibility(View.GONE);



                        feed.setImageResource(R.drawable.ic_feed_color);
                        ed.setImageResource(R.drawable.ic_ed_white);
                        write.setImageResource(R.drawable.ic_write_white);
                        write.setBackgroundResource(R.drawable.menu_write_outline);
                        oj.setImageResource(R.drawable.ic_oj_white);
                        bp.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 1:
                        menu2.setVisibility(View.VISIBLE);
                        menu2.setImageResource(R.drawable.ic_graph);



                        feed.setImageResource(R.drawable.ic_feed_white);
                        ed.setImageResource(R.drawable.ic_ed_color);
                        write.setImageResource(R.drawable.ic_write_white);
                        write.setBackgroundResource(R.drawable.menu_write_outline);
                        oj.setImageResource(R.drawable.ic_oj_white);
                        bp.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 2:
                        menu2.setVisibility(View.GONE);



                        feed.setImageResource(R.drawable.ic_feed_white);
                        ed.setImageResource(R.drawable.ic_ed_white);
                        write.setImageResource(R.drawable.ic_write_color);
                        write.setBackgroundResource(R.drawable.menu_write_colorline);
                        oj.setImageResource(R.drawable.ic_oj_white);
                        bp.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 3:
                        menu2.setVisibility(View.VISIBLE);
                        menu2.setImageResource(R.drawable.ic_calender);



                        feed.setImageResource(R.drawable.ic_feed_white);
                        ed.setImageResource(R.drawable.ic_ed_white);
                        write.setImageResource(R.drawable.ic_write_white);
                        write.setBackgroundResource(R.drawable.menu_write_outline);
                        oj.setImageResource(R.drawable.ic_oj_color);
                        bp.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 4:
                        menu2.setVisibility(View.GONE);



                        feed.setImageResource(R.drawable.ic_feed_white);
                        ed.setImageResource(R.drawable.ic_ed_white);
                        write.setImageResource(R.drawable.ic_write_white);
                        write.setBackgroundResource(R.drawable.menu_write_outline);
                        oj.setImageResource(R.drawable.ic_oj_white);
                        bp.setImageResource(R.drawable.ic_bp_color);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    //nv Drawer -------------------------------
    public void changeProfile(View view){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,615);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv=nv.getHeaderView(0).findViewById(R.id.nv_profile);
        switch (requestCode){
            case 615:
                if(resultCode!=RESULT_CANCELED){
                    Uri uri=data.getData();
                    G.uri=uri;
                    if(uri==null) Toast.makeText(this, "No Image", Toast.LENGTH_SHORT).show();
                    else Glide.with(this).load(uri).into(iv);
                }
        }
    }

    public void changeName(View view){
        view.setVisibility(View.GONE);

        EditText et=nv.getHeaderView(0).findViewById(R.id.nv_userName);
        ImageView iv=nv.getHeaderView(0).findViewById(R.id.nv_userNameSet);

        et.setVisibility(View.VISIBLE);
        iv.setVisibility(View.VISIBLE);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et.getText().toString();
                G.userName=name;
                et.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
                TextView tv=(TextView) view;
                tv.setText(name);
            }
        });
    }//-----------------------------------------

    View.OnClickListener menuListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.menu:
                    Animation animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.nv_ani);
                    menu.startAnimation(animation);
                    drawer.openDrawer(nv);
                    break;
                case R.id.menu2:
                    animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu2_ani);
                    menu2.startAnimation(animation);
                    if(pager.getCurrentItem()==1){

                    }else if(pager.getCurrentItem()==3){
                        Calendar c=Calendar.getInstance();
                        int year=c.get(Calendar.YEAR);
                        int month=c.get(Calendar.MONTH);
                        int day=c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Intent intent=new Intent(MainActivity.this,OJDetailActivity.class);
                                intent.putExtra("Date",""+year+(month+1)+dayOfMonth);
                                startActivity(intent);
                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                    break;

            }
        }
    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate();
//        return super.onCreateOptionsMenu(menu);
//    }

    //Bottom Image button click listener
    View.OnClickListener btnClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.feed:



                    Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    feed.startAnimation(animation);

                    feed.setImageResource(R.drawable.ic_feed_color);
                    ed.setImageResource(R.drawable.ic_ed_white);
                    write.setImageResource(R.drawable.ic_write_white);
                    write.setBackgroundResource(R.drawable.menu_write_outline);
                    oj.setImageResource(R.drawable.ic_oj_white);
                    bp.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(0);
                    break;
                case R.id.ed:


                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    ed.startAnimation(animation);



                    feed.setImageResource(R.drawable.ic_feed_white);
                    ed.setImageResource(R.drawable.ic_ed_color);
                    write.setImageResource(R.drawable.ic_write_white);
                    write.setBackgroundResource(R.drawable.menu_write_outline);
                    oj.setImageResource(R.drawable.ic_oj_white);
                    bp.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(1);
                    break;
                case R.id.write:


                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.write_ani);
                    write.startAnimation(animation);

                    feed.setImageResource(R.drawable.ic_feed_white);
                    ed.setImageResource(R.drawable.ic_ed_white);
                    write.setImageResource(R.drawable.ic_write_color);
                    write.setBackgroundResource(R.drawable.menu_write_colorline);
                    oj.setImageResource(R.drawable.ic_oj_white);
                    bp.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(2);
                    break;
                case R.id.oj:


                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    oj.startAnimation(animation);


                    feed.setImageResource(R.drawable.ic_feed_white);
                    ed.setImageResource(R.drawable.ic_ed_white);
                    write.setImageResource(R.drawable.ic_write_white);
                    write.setBackgroundResource(R.drawable.menu_write_outline);
                    oj.setImageResource(R.drawable.ic_oj_color);
                    bp.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(3);
                    break;
                case R.id.bp:




                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    bp.startAnimation(animation);


                    feed.setImageResource(R.drawable.ic_feed_white);
                    ed.setImageResource(R.drawable.ic_ed_white);
                    write.setImageResource(R.drawable.ic_write_white);
                    write.setBackgroundResource(R.drawable.menu_write_outline);
                    oj.setImageResource(R.drawable.ic_oj_white);
                    bp.setImageResource(R.drawable.ic_bp_color);
                    pager.setCurrentItem(4);
                    break;
            }
        }
    };

}
