package com.icandothisallday2020.ems;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BP extends Fragment {
    ArrayList<BPItem> items=new ArrayList<>();
    Spinner spinner;
    ArrayAdapter adapter;
    TextView reason,ideal,reality,goal,deadline;
//    CircleProgressBar circleProgressBar2;
    WaveLoadingView wave;
    LinearLayout bp;
    String[] plans;

    int positionNow;
    boolean isConnect=true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bp,container,false);

        reason=view.findViewById(R.id.reason);
        ideal=view.findViewById(R.id.ideal);
        reality=view.findViewById(R.id.reality);
        goal=view.findViewById(R.id.goal);
        deadline=view.findViewById(R.id.deadline);
//        circleProgressBar2=view.findViewById(R.id.cpb_reality);
        bp=view.findViewById(R.id.table);
        wave=view.findViewById(R.id.wave_reality);

        spinner=view.findViewById(R.id.spinnerBP);




//        adapter=ArrayAdapter.createFromResource(getContext(),R.array.comments,R.layout.spinner_bp_selected);
//        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
//        spinner.setAdapter(adapter);

        CircleProgressBar circleProgressBar=view.findViewById(R.id.cpb_ideal);
        circleProgressBar.setProgress(100);


        Retrofit retrofit=RetrofitHelper.getInstanceFromGson();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<ArrayList<BPItem>> call=service.loadDataFromBP();

        call.enqueue(new Callback<ArrayList<BPItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BPItem>> call, Response<ArrayList<BPItem>> response) {
                items.clear();
                ArrayList<BPItem> allBP=response.body();
                SharedPreferences preferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                String email=preferences.getString("Email","");

                StringBuffer buffer=new StringBuffer();
                String[] arr;
                int i=0;
                for(BPItem item:allBP){
                    if(email.equals(item.email)){
                        items.add(0,item);
                        G.bpItems.add(0,item);

                        buffer.append(item.bliss+"@#@");
                    }
                }

                String all=buffer.toString();
                String[] blisses=all.split("@#@");
                adapter=new ArrayAdapter(getContext(),R.layout.spinner_bp_selected,blisses);
                adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                adapter.notifyDataSetChanged();
                spinner.setAdapter(adapter);

                i++;
                isConnect=true;

            }

            @Override
            public void onFailure(Call<ArrayList<BPItem>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_LONG).show();
                isConnect=false;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionNow=position;
                reason.setText(G.bpItems.get(position).reason);
                ideal.setText(G.bpItems.get(position).ideal);
                reality.setText(G.bpItems.get(position).reality);
                goal.setText(G.bpItems.get(position).goal);
                deadline.setText(G.bpItems.get(position).deadline);
//                circleProgressBar2.setProgress(Integer.parseInt(G.bpItems.get(position).progress));
                wave.setProgressValue(Integer.parseInt(G.bpItems.get(position).progress));
                wave.setCenterTitle(G.bpItems.get(position).progress+"%");

                Gson gson=new Gson();
                plans=gson.fromJson(G.bpItems.get(position).plans,String[].class);
                int i=1;

                bp.removeAllViews();
                for(String plan:plans){
                    Log.i("plan",plan);
                    View tableRow = getLayoutInflater().inflate(R.layout.table_row_bp,container,false);
                    TextView num=tableRow.findViewById(R.id.table_bp_num);
                    num.setText(""+i); i++;
                    TextView tv=tableRow.findViewById(R.id.table_tv);
                    tv.setText(plan);
                    CheckBox cb=tableRow.findViewById(R.id.table_checkBox);

                    bp.addView(tableRow);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView delete=view.findViewById(R.id.deleteBP);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext(),R.style.MyDialog);
                builder.setIcon(R.drawable.ic_alert);
                builder.setTitle("Do you want to delete this plan?");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int no=G.bpItems.get(positionNow).no;
                        Call<String> call1=service.deleteDataFromBP(no);
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(getContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        spinner.setSelection(0);
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });


        if(G.bpItems.size()<1 || !isConnect){
            NestedScrollView ns=view.findViewById(R.id.scrollViewBP);
            ns.setVisibility(View.INVISIBLE);
            TextView tv=view.findViewById(R.id.noBP);
            tv.setVisibility(View.VISIBLE);
            return  view;
        }else{
            NestedScrollView ns=view.findViewById(R.id.scrollViewBP);
            ns.setVisibility(View.VISIBLE);
            TextView tv=view.findViewById(R.id.noBP);
            tv.setVisibility(View.INVISIBLE);
        }


        return view;
    }


}
