package com.icandothisallday2020.ems;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OJAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<OJItem> items;

    public OJAdapter(Context context, ArrayList<OJItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View ojView = inflater.inflate(R.layout.recycler_oj_item,parent,false);
        VH holder=new VH(ojView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        OJItem ojItem=items.get(position);

        vh.date.setText(ojItem.year+"\n"+ojItem.month+"/"+ojItem.day);
        vh.q.setText(ojItem.q);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView q,date;

        public VH(@NonNull View itemView) {
            super(itemView);
            q=itemView.findViewById(R.id.foj_q);
            date=itemView.findViewById(R.id.foj_date);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OJItem ojItem=items.get(getLayoutPosition());
                    //Reference item data--now position
                    Intent intent=new Intent(context, OJDetailActivity.class);
//                    intent.putExtra("Position",getLayoutPosition());
                    intent.putExtra("Date",ojItem.year+ojItem.month+ojItem.day);


                    if(Build.VERSION.SDK_INT<21) context.startActivity(intent);
                    else{
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(
                                (Activity)context,new Pair<View,String>(q,"OJItem"));
                        context.startActivity(intent,options.toBundle());
                    }


                }
            });
        }
    }
}
