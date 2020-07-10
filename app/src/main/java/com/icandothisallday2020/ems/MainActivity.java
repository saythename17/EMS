package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ViewPager pager;
    MainAdapter adapter;
    ImageView btnFeed, btnED, btnWrite, btnOJ, btnBP, btnMenu;

    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=findViewById(R.id.mainDrawer);
        pager=findViewById(R.id.mainPager);
        nv=findViewById(R.id.nv);


        btnMenu=findViewById(R.id.menu);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.nv_ani);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMenu.startAnimation(animation);
                drawer.openDrawer(nv);
            }
        });







        btnFeed=findViewById(R.id.feed);
        btnFeed.setOnClickListener(btnClick);

        btnED=findViewById(R.id.ed);
        btnED.setOnClickListener(btnClick);

        btnWrite=findViewById(R.id.write);
        btnWrite.setOnClickListener(btnClick);

        btnOJ=findViewById(R.id.oj);
        btnOJ.setOnClickListener(btnClick);

        btnBP=findViewById(R.id.bp);
        btnBP.setOnClickListener(btnClick);







        adapter=new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(2);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        btnFeed.setImageResource(R.drawable.ic_feed_color);
                        btnED.setImageResource(R.drawable.ic_ed_white);
                        btnWrite.setImageResource(R.drawable.ic_write_white);
                        btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                        btnOJ.setImageResource(R.drawable.ic_oj_white);
                        btnBP.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 1:
                        btnFeed.setImageResource(R.drawable.ic_feed_white);
                        btnED.setImageResource(R.drawable.ic_ed_color);
                        btnWrite.setImageResource(R.drawable.ic_write_white);
                        btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                        btnOJ.setImageResource(R.drawable.ic_oj_white);
                        btnBP.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 2:
                        btnFeed.setImageResource(R.drawable.ic_feed_white);
                        btnED.setImageResource(R.drawable.ic_ed_white);
                        btnWrite.setImageResource(R.drawable.ic_write_color);
                        btnWrite.setBackgroundResource(R.drawable.menu_write_colorline);
                        btnOJ.setImageResource(R.drawable.ic_oj_white);
                        btnBP.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 3:
                        btnFeed.setImageResource(R.drawable.ic_feed_white);
                        btnED.setImageResource(R.drawable.ic_ed_white);
                        btnWrite.setImageResource(R.drawable.ic_write_white);
                        btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                        btnOJ.setImageResource(R.drawable.ic_oj_color);
                        btnBP.setImageResource(R.drawable.ic_bp_white);
                        break;
                    case 4:
                        btnFeed.setImageResource(R.drawable.ic_feed_white);
                        btnED.setImageResource(R.drawable.ic_ed_white);
                        btnWrite.setImageResource(R.drawable.ic_write_white);
                        btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                        btnOJ.setImageResource(R.drawable.ic_oj_white);
                        btnBP.setImageResource(R.drawable.ic_bp_color);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

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
                    btnFeed.startAnimation(animation);

                    btnFeed.setImageResource(R.drawable.ic_feed_color);
                    btnED.setImageResource(R.drawable.ic_ed_white);
                    btnWrite.setImageResource(R.drawable.ic_write_white);
                    btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                    btnOJ.setImageResource(R.drawable.ic_oj_white);
                    btnBP.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(0);
                    break;
                case R.id.ed:


                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    btnED.startAnimation(animation);



                    btnFeed.setImageResource(R.drawable.ic_feed_white);
                    btnED.setImageResource(R.drawable.ic_ed_color);
                    btnWrite.setImageResource(R.drawable.ic_write_white);
                    btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                    btnOJ.setImageResource(R.drawable.ic_oj_white);
                    btnBP.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(1);
                    break;
                case R.id.write:


                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.write_ani);
                    btnWrite.startAnimation(animation);

                    btnFeed.setImageResource(R.drawable.ic_feed_white);
                    btnED.setImageResource(R.drawable.ic_ed_white);
                    btnWrite.setImageResource(R.drawable.ic_write_color);
                    btnWrite.setBackgroundResource(R.drawable.menu_write_colorline);
                    btnOJ.setImageResource(R.drawable.ic_oj_white);
                    btnBP.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(2);
                    break;
                case R.id.oj:


                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    btnOJ.startAnimation(animation);


                    btnFeed.setImageResource(R.drawable.ic_feed_white);
                    btnED.setImageResource(R.drawable.ic_ed_white);
                    btnWrite.setImageResource(R.drawable.ic_write_white);
                    btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                    btnOJ.setImageResource(R.drawable.ic_oj_color);
                    btnBP.setImageResource(R.drawable.ic_bp_white);
                    pager.setCurrentItem(3);
                    break;
                case R.id.bp:




                    animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.menu_ani);
                    btnBP.startAnimation(animation);


                    btnFeed.setImageResource(R.drawable.ic_feed_white);
                    btnED.setImageResource(R.drawable.ic_ed_white);
                    btnWrite.setImageResource(R.drawable.ic_write_white);
                    btnWrite.setBackgroundResource(R.drawable.menu_write_outline);
                    btnOJ.setImageResource(R.drawable.ic_oj_white);
                    btnBP.setImageResource(R.drawable.ic_bp_color);
                    pager.setCurrentItem(4);
                    break;
            }
        }
    };
}
