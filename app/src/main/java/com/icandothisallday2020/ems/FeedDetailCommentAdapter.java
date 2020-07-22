package com.icandothisallday2020.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedDetailCommentAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<CItem> items;

    public FeedDetailCommentAdapter(Context context, ArrayList<CItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.comments,parent,false);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;

        CItem item=items.get(position);
        vh.name.setText(item.name);
        vh.comment.setText(item.comment);
        vh.date.setText(item.comment);
        vh.up.setText(item.up);
        vh.down.setText(item.down);

        //TODO set user's profile photo to civ
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        CircleImageView profile;
        TextView name,comment,date,up,down;

        public VH(@NonNull android.view.View itemView) {
            super(itemView);

            profile=itemView.findViewById(R.id.c_profile);
            name=itemView.findViewById(R.id.c_name);
            comment=itemView.findViewById(R.id.comment);
            date=itemView.findViewById(R.id.c_date);
            up=itemView.findViewById(R.id.c_up);
            down=itemView.findViewById(R.id.c_down);
        }
    }
}
