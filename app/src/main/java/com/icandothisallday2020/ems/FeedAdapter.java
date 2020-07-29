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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<FeedItem> items;

    public FeedAdapter(Context context, ArrayList<FeedItem> items) {
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
        FeedItem feedItem=items.get(position);
        vh.title.setText(feedItem.title);
        vh.text.setText(feedItem.content);
        Glide.with(context).load("http://soon0.dothome.co.kr/EMS/"+feedItem.file).into(vh.feedImage);
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
                    FeedItem item=items.get(getLayoutPosition());
                    Intent intent=new Intent(context, FeedDetailActivity.class);
                    intent.putExtra("Title",item.title);
                    intent.putExtra("Content",item.content);
                    intent.putExtra("File",item.file);

                    if(Build.VERSION.SDK_INT<21) context.startActivity(intent);
                    else{
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(
                                (Activity)context,new Pair<View,String>(v,"Feed"));
                        context.startActivity(intent,options.toBundle());
                    }


                }
            });
        }
    }
}
