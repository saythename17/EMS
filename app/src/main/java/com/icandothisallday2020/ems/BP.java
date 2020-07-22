package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BP extends Fragment {
    ArrayList<BPItem> items=new ArrayList<>();
    Spinner spinner;
    ArrayAdapter adapter;
    TextView reason,ideal,reality,goal,deadline;
    CircleProgressBar circleProgressBar2;
    LinearLayout bp;
    String[] plans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bp,container,false);
        reason=view.findViewById(R.id.reason);
        ideal=view.findViewById(R.id.ideal);
        reality=view.findViewById(R.id.reality);
        goal=view.findViewById(R.id.goal);
        deadline=view.findViewById(R.id.deadline);
        circleProgressBar2=view.findViewById(R.id.cpb_reality);
        bp=view.findViewById(R.id.table);

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
                G.bpItems=response.body();
                SharedPreferences preferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                String email=preferences.getString("Email","");

                StringBuffer buffer=new StringBuffer();
                String[] arr;
                int i=0;
                for(BPItem item:G.bpItems){
                    if(email.equals(item.email)){
                        items.add(0,item);

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

            }

            @Override
            public void onFailure(Call<ArrayList<BPItem>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_LONG).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reason.setText(G.bpItems.get(position).reason);
                ideal.setText(G.bpItems.get(position).ideal);
                reality.setText(G.bpItems.get(position).reality);
                goal.setText(G.bpItems.get(position).goal);
                deadline.setText(G.bpItems.get(position).deadline);
                circleProgressBar2.setProgress(Integer.parseInt(G.bpItems.get(position).progress));

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




        return view;
    }


}
