package com.icandothisallday2020.ems;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class EDDetailAdapter extends PagerAdapter {
    LayoutInflater inflater;
    ArrayList<String> tag;
    ArrayList<String> intensity;
    ArrayList<String> feelings;

    public EDDetailAdapter(LayoutInflater inflater, ArrayList<String> tag, ArrayList<String> intensity, ArrayList<String> feelings) {
        this.inflater = inflater;
        this.tag = tag;
        this.intensity = intensity;
        this.feelings = feelings;
    }

    @Override
    public int getCount() {
        return G.edItems.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View page=inflater.inflate(R.layout.page_ed_detail,null);

        TextView date=page.findViewById(R.id.edDate),
                situation=page.findViewById(R.id.detailSituation),
                thought=page.findViewById(R.id.detailThought),
                action=page.findViewById(R.id.detailAction),
                result=page.findViewById(R.id.detailResult);
        ImageView previous=page.findViewById(R.id.edPrevious),
                  next=page.findViewById(R.id.edNext);
        RecyclerView recyclerView=page.findViewById(R.id.recyclerEDDetail);
        EDDEAdapter adapterE=new EDDEAdapter(inflater.getContext(),tag,feelings);
        recyclerView.setAdapter(adapterE);

        EDItem item=G.edItems.get(position);
        date.setText(item.date);
        situation.setText(item.situation);
        thought.setText(item.thought);
        action.setText(item.action);
        result.setText(item.result);






        //--------------------------------PieChart--------------

        PieChart chart=page.findViewById(R.id.pieChart);
        int[] colorArray=new int[]{Color.parseColor("#727CB3"),
                Color.parseColor("#5B659A"),
                Color.parseColor("#4A5487"),
                Color.parseColor("#3F4978"),
                Color.parseColor("#2C3563"),
                Color.parseColor("#B05E438F"),
                Color.parseColor("#ACA08AC8")};

        //emotion chart value
        ArrayList<PieEntry> value=new ArrayList<>();
        for(int i=0;i<feelings.size();i++){
            value.add(new PieEntry(Integer.parseInt(intensity.get(i)),feelings.get(i)));
        }
        PieDataSet dataSet=new PieDataSet(value,"Emotion");
        dataSet.setColors(colorArray);
        PieData data=new PieData(dataSet);
        data.setValueTextColor(Color.LTGRAY);
        data.setValueTextSize(15);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setTransparentCircleColor(R.color.colorAlpha);
        chart.setTransparentCircleAlpha(110);
        chart.setTransparentCircleRadius(61f);
        chart.setCenterText("Intensity");
        chart.setCenterTextColor(Color.WHITE);
        chart.setCenterTextSize(20);
        chart.setCenterTextTypeface(Typeface.MONOSPACE);
        chart.setHoleRadius(56f);
        chart.setHoleColor(R.color.colorAlpha);
        chart.setDrawHoleEnabled(true);


        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setHighlightPerTapEnabled(true);


        chart.setEntryLabelTextSize(15);


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
        chart.setData(data);
        chart.animateY(2000, Easing.EaseInOutCirc);
        chart.invalidate();
//--------------------------PieChart End Line--------------------------


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
