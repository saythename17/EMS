package com.icandothisallday2020.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SelfTalkAdapter extends BaseAdapter {
    Context context;
    ArrayList<TalkItem> items;

    public SelfTalkAdapter() {
    }

    public SelfTalkAdapter(Context context, ArrayList<TalkItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TalkItem item=items.get(position);
        View view = null;


        if((item.type).equals("A")){
            view=LayoutInflater.from(context).inflate(R.layout.my_msg,parent,false);
            TextView msg=view.findViewById(R.id.myTV);
            msg.setText(item.msg);
        }else{
            view=LayoutInflater.from(context).inflate(R.layout.q_msg,parent,false);
            TextView msg=view.findViewById(R.id.myTV);
            msg.setText(item.msg);
        }

        return view;
    }
}
