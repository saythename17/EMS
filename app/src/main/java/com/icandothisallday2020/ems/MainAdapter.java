package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainAdapter extends FragmentPagerAdapter {
    Fragment[] fragments=new Fragment[5];

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0]=new FFeed();
        fragments[1]=new FED();
        fragments[2]=new FWrite();
        fragments[3]=new FOJ();
        fragments[4]=new FBP();
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
