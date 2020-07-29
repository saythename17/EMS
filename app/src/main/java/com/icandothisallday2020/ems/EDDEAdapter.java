package com.icandothisallday2020.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EDDEAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> tag;
    ArrayList<String> feelings;

    public EDDEAdapter(Context context, ArrayList<String> tag, ArrayList<String> feelings) {
        this.context = context;
        this.tag = tag;
        this.feelings = feelings;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.recycler_ed_detail,parent,false);
        VH vh=new VH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;

        vh.tv.setText(feelings.get(position));
        vh.iv.setImageResource(R.drawable.e00_love+Integer.parseInt(tag.get(position)));
    }

    @Override
    public int getItemCount() {
        return tag.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.detailETV);
            iv=itemView.findViewById(R.id.detailEIV);
        }
    }
}
