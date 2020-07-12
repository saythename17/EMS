package com.icandothisallday2020.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FFeedAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<FFeedItem> items;

    public FFeedAdapter(Context context, ArrayList<FFeedItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View feedView=inflater.inflate(R.layout.recycler_feed_item,parent,false);
        VH vh=new VH(feedView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH) holder;
        FFeedItem feedItem=items.get(position);
        vh.title.setText(feedItem.title);
        vh.text.setText(feedItem.text);

        Glide.with(context).load(feedItem.imageURL).into(vh.feedImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView feedImage;
        TextView title,text;
        public VH(@NonNull View itemView) {
            super(itemView);
            feedImage=itemView.findViewById(R.id.feedImage);
            title=itemView.findViewById(R.id.feedTitle);
            text=itemView.findViewById(R.id.feedText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
