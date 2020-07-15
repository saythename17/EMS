package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EDAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<EDItem> items;

    public EDAdapter(Context context, ArrayList<EDItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_ed_item,parent,false);
        VH vh=new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        EDItem item=items.get(position);
        //vh.emoIV.setImageResource();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }





    class VH extends RecyclerView.ViewHolder{
        ImageView emoIV,menu;
        TextView date,time,emoTV;
        public VH(@NonNull View itemView) {
            super(itemView);
            emoIV=itemView.findViewById(R.id.ed_emo);
            menu=itemView.findViewById(R.id.ed_itemM);
            date=itemView.findViewById(R.id.ed_itemD);
            time=itemView.findViewById(R.id.ed_itemT);
            emoTV=itemView.findViewById(R.id.ed_itemE);


            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EDItem item=items.get(getLayoutPosition());
                    Intent intent=new Intent(context, EDDetailActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}
