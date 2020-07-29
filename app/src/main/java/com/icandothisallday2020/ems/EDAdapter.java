package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    ArrayList<String> tag;
    ArrayList<String> intensity;
    ArrayList<String> feelings;



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
        EDItem item=G.edItems.get(position);
        tag=new ArrayList<>();
        intensity=new ArrayList<>();
        feelings=new ArrayList<>();



        vh.date.setText(item.date);

        String[] arr=item.emotion.split("!");
        String[][] selections=new String[arr.length][];
        ArrayList<String[]> emotions=new ArrayList<>();

        for(int i=0;i<=arr.length-1;i++){
            selections[i]=arr[i].split("_");
            tag.add(selections[i][0]);
            Log.i("log",""+i+selections[i][0]);
            String[] strings=(selections[i][1]).split(",");
            Log.i("log",""+i+selections[i][1]);
            emotions.add(strings);
//            feelings.add(selections[i][1]);
            intensity.add(selections[i][2]);
            Log.i("log",""+i+selections[i][2]);
            Log.i("log","----------------");
        }
        StringBuffer finalBuffer=new StringBuffer();

        for(int i=0; i<=arr.length-1;i++){

            StringBuffer buffer=new StringBuffer();
            for(int j=0;j<=emotions.get(i).length-1;j++){
                String a=(emotions.get(i))[j];
                buffer.append(a);
                if(j<=emotions.get(i).length-2){
                    buffer.append(", ");
                }

            }
            buffer.append("\n");
            feelings.add(buffer.toString());
            finalBuffer.append(buffer.toString());
            Log.i("log",finalBuffer.toString());//check ---Fondly,Favourable, Joy


        }
        String emo=finalBuffer.toString();
        vh.emoTV.setText(emo);

        vh.emoIV.setImageResource(R.drawable.e00_love+Integer.parseInt(tag.get(0)));
        vh.time.setText(item.time);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }





    class VH extends RecyclerView.ViewHolder{
        ImageView emoIV;
        TextView date,time,emoTV;
        public VH(@NonNull View itemView) {
            super(itemView);
            emoIV=itemView.findViewById(R.id.ed_emo);
            date=itemView.findViewById(R.id.ed_itemD);
            time=itemView.findViewById(R.id.ed_itemT);
            emoTV=itemView.findViewById(R.id.ed_itemE);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EDItem item=G.edItems.get(getLayoutPosition());
                    Intent intent=new Intent(context, EDDetailActivity.class);
                    intent.putExtra("Position",getLayoutPosition());
                    intent.putStringArrayListExtra("tag",tag);
                    intent.putStringArrayListExtra("feelings",feelings);
                    intent.putStringArrayListExtra("intensity",intensity);


                    context.startActivity(intent);
//                    tag.clear();
//                    intensity.clear();
//                    feelings.clear();
                }
            });


        }
    }
}
