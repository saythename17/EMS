package com.icandothisallday2020.ems;

import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainAdapter extends FragmentPagerAdapter {
    Fragment[] fragments=new Fragment[5];

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0]=new FragmentFeed();
        fragments[1]=new FragmentED();
        fragments[2]=new FragmentWrite();
        fragments[3]=new FragmentOJ();
        fragments[4]=new FragmentBP();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
