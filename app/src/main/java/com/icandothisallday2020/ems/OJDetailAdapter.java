package com.icandothisallday2020.ems;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class OJDetailAdapter extends PagerAdapter {
    LayoutInflater inflater;

    public OJDetailAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return G.ojItems.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View page=inflater.inflate(R.layout.page_oj_detail,null);

        TextView q=page.findViewById(R.id.ojQ),
                 a=page.findViewById(R.id.ojA),
                 date=page.findViewById(R.id.ojD);
        ImageView previous=page.findViewById(R.id.ojPrevious),
                  next=page.findViewById(R.id.ojNext);

        OJItem item=G.ojItems.get(position);
        q.setText(item.q);
        a.setText(item.a);
        date.setText(item.date);

        if(position==0)previous.setVisibility(View.INVISIBLE);
        else previous.setVisibility(View.VISIBLE);
        if(position==getCount()-1)next.setVisibility(View.INVISIBLE);
        else next.setVisibility(View.VISIBLE);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager pager=(ViewPager) container;
                pager.setCurrentItem(pager.getCurrentItem()-1,true);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager pager=(ViewPager) container;
                pager.setCurrentItem(pager.getCurrentItem()+1,true);
            }
        });

        container.addView(page);
        return page;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
