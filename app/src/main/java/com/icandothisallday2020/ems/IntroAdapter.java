package com.icandothisallday2020.ems;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {
    Fragment[] fragments=new Fragment[3];

    public IntroAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0]=new IntroFragment1();
        fragments[1]=new IntroFragment2();
        fragments[2]=new IntroFragment3();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }


    @Override
    public int getCount() {
        return fragments.length;
    }
}
