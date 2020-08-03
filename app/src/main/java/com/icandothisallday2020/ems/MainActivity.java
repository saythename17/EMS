package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
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
    boolean edGraph=false;



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=findViewById(R.id.mainDrawer);
        pager=findViewById(R.id.mainPager);
        nv=findViewById(R.id.nv);
        edGraph=false;



        nvHeader=nv.getHeaderView(0).findViewById(R.id.nameGroup);

        SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
        String name=preferences.getString("Name","User");
        String profileUrl=preferences.getString("ProfileUrl","");
        TextView userName=nv.getHeaderView(0).findViewById(R.id.nv_name);
        userName.setText(name);
        ImageView userProfile=nv.getHeaderView(0).findViewById(R.id.nv_profile);
        if(!(profileUrl.equals(""))) Glide.with(this).load(profileUrl).into(userProfile);






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
                        intent=new Intent(MainActivity.this,MyCommentActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.privacyPolicy:
                        intent=new Intent(MainActivity.this,PrivacyActivity.class);
                        startActivity(intent);
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
                        edGraph=false;
                        if(edGraph){
                            menu2.setImageResource(R.drawable.ic_list);
                            edGraph=!edGraph;
                        }else{
                            menu2.setImageResource(R.drawable.ic_graph);
                            edGraph=!edGraph;
                        }



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
                    SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("ProfileUrl",uri.toString());
                    editor.commit();
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
                SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("Name",name);
                editor.commit();
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
                    if(pager.getCurrentItem()==1){
                        animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha_ani);
                            if(edGraph){
                                menu2.setImageResource(R.drawable.ic_list);
                                menu2.setAnimation(animation);
                                edGraph=!edGraph;
                            }else{
                                menu2.setImageResource(R.drawable.ic_graph);
                                menu2.setAnimation(animation);
                                edGraph=!edGraph;
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                        menu2.startAnimation(animation);
                        Button monthGraphSet=findViewById(R.id.monthChange);
                        RecyclerView recyclerView=findViewById(R.id.recyclerED);
                        HorizontalBarChart chart=findViewById(R.id.chartHorizontal);





                        if(edGraph){
                            TextView tv=findViewById(R.id.noED);
                            tv.setVisibility(View.GONE);
                            monthGraphSet.setVisibility(View.VISIBLE);
                            monthGraphSet.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //TODO month select dialog
                                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this,R.style.MyDialog);
                                    LayoutInflater inflater=MainActivity.this.getLayoutInflater();
                                    Calendar cal=Calendar.getInstance();

                                    View dialog=inflater.inflate(R.layout.picker_month_year,null);
                                    NumberPicker monthPicker=dialog.findViewById(R.id.picker_month);
                                    NumberPicker yearPicker=dialog.findViewById(R.id.picker_year);
//                                    monthPicker.setTextColor(Color.WHITE);
                                    monthPicker.setMinValue(1); monthPicker.setMaxValue(12);
                                    monthPicker.setValue(cal.get(Calendar.MONTH)+1);
                                    yearPicker.setTextColor(Color.WHITE);
                                    yearPicker.setMinValue(2010); yearPicker.setMaxValue(cal.get(Calendar.YEAR));
                                    yearPicker.setValue(cal.get(Calendar.YEAR));

                                    builder.setView(dialog);
                                    builder.setTitle("Monthly Statistics");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //TODO set barChart data of the month
                                            Toast.makeText(MainActivity.this, ""+
                                                    yearPicker.getValue()+"-"+monthPicker.getValue(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    AlertDialog dialog1=builder.create();
                                    dialog1.show();
                                }
                            });



                            chart.setVisibility(View.VISIBLE);

                            final RectF mOnValueSelectedRectF = new RectF();
                            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                                @Override
                                public void onValueSelected(Entry e, Highlight h) {
                                    if (e == null)
                                        return;

                                    RectF bounds = mOnValueSelectedRectF;
                                    chart.getBarBounds((BarEntry) e, bounds);

                                    MPPointF position = chart.getPosition(e, chart.getData().getDataSetByIndex(h.getDataSetIndex())
                                            .getAxisDependency());

                                    Log.i("bounds", bounds.toString());
                                    Log.i("position", position.toString());

                                    MPPointF.recycleInstance(position);

                                }

                                @Override
                                public void onNothingSelected() {

                                }
                            });

                            chart.setDrawBarShadow(false);
                            chart.getDescription().setEnabled(false);
                            chart.setMaxVisibleValueCount(5);
                            chart.setDrawGridBackground(false);

                            ArrayList<BarEntry> barEntries = new ArrayList<>();
                            barEntries.add(new BarEntry(1f, 2f));
                            barEntries.add(new BarEntry(2f, 3f));
                            barEntries.add(new BarEntry(3f, 4f));
                            barEntries.add(new BarEntry(4f, 5f));


                            BarDataSet barDataSet = new BarDataSet(barEntries, "Stat");
                            int[] colors=new int[]{Color.parseColor("#9474E3"),
                                Color.parseColor("#715AE3"),
                                Color.parseColor("#62429C"),
                                Color.parseColor("#583AB1"),
                                Color.parseColor("#4B2FB1"),
                                R.color.colorAccent};
                            barDataSet.setColors(colors);
                            BarData barData = new BarData(barDataSet);
                            barData.setBarWidth(0.6f);
                            barData.setValueTextSize(20);
                            barData.setHighlightEnabled(true);



                            chart.setDrawGridBackground(false);
                            chart.setDrawValueAboveBar(false);

                            Legend l = chart.getLegend();
                            l.setEnabled(false);

                            ArrayList<String> xAxisName = new ArrayList<>();
                            xAxisName.add("\uD83D\uDC94123");
                            xAxisName.add("\uD83D\uDC9C45");
                            xAxisName.add("\uD83D\uDC9967");
                            xAxisName.add("\uD83D\uDC99aaa");
                            XAxis xAxis = chart.getXAxis();
                            xAxis.setTextSize(17);
                            xAxis.isDrawAxisLineEnabled();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setTextColor(Color.WHITE);
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisName));
                            xAxis.setDrawGridLines(false);

                            chart.animateY(2000);
                            chart.setData(barData);

                            recyclerView.setVisibility(View.GONE);
                        }else {
                            if(G.edItems.size()<1) {
                                TextView tv=findViewById(R.id.noED);
                                tv.setVisibility(View.VISIBLE);
                            }else{
                                TextView tv=findViewById(R.id.noED);
                                tv.setVisibility(View.GONE);
                            }
                            monthGraphSet.setVisibility(View.GONE);
                            chart.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }


                    }else if(pager.getCurrentItem()==3){
                        menu2.startAnimation(animation);
                        menu2.setImageResource(R.drawable.ic_calender);
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
                    edGraph=false;
                    if(edGraph){
                        menu2.setImageResource(R.drawable.ic_list);
                        edGraph=!edGraph;
                    }else{
                        menu2.setImageResource(R.drawable.ic_graph);
                        edGraph=!edGraph;
                    }

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
