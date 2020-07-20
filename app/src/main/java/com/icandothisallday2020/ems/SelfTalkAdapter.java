package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelfTalkAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> talks;

    public SelfTalkAdapter(Context context, ArrayList<String> talks) {
        this.context = context;
        this.talks = talks;
    }

    @Override
    public int getCount() {
        return talks.size();
    }

    @Override
    public Object getItem(int position) {
        return talks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        SharedPreferences preferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        String email = preferences.getString("Email", "");

        view = LayoutInflater.from(context).inflate(R.layout.my_msg, parent, false);

        TextView msg=view.findViewById(R.id.myTV);
        msg.setText(talks.get(position));
        return view;
    }
}
