package com.icandothisallday2020.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedCommentAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<CItem> items;

    public FeedCommentAdapter(Context context, ArrayList<CItem> items) {
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
        if(!item.profileUrl.equals("No File")){
            Glide.with(context).load(item.profileUrl).into(vh.profile);
        }
        vh.date.setText(item.date);
        vh.up.setText("\t"+item.up);
        vh.down.setText("\t"+item.down);
        final boolean[] click = {true,true};
        vh.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click[0]){
                    vh.up.setText("\t"+(Integer.parseInt(item.up)+1));
                    click[0] =!click[0];
                }else{
                    vh.up.setText("\t"+item.up);
                    click[0] =!click[0];
                }
            }
        });

        vh.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click[1]){
                        vh.down.setText("\t"+(Integer.parseInt(item.down)+1));
                        click[1] =!click[1];

                }else{
                    vh.down.setText("\t"+item.down);
                    click[1] =!click[1];
                }
            }
        });

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
