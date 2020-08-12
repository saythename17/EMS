package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ED extends Fragment {
    ArrayList<EDItem> items=new ArrayList<>();
    EDAdapter adapter;
    RecyclerView recyclerView;

    HorizontalBarChart chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ed,container,false);
        if(G.edItems.size()==0) {
            TextView tv=view.findViewById(R.id.noED);
            tv.setVisibility(View.VISIBLE);
        }else{
            TextView tv=view.findViewById(R.id.noED);
            tv.setVisibility(View.GONE);
        }
        adapter=new EDAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerED);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit=RetrofitHelper.getInstanceFromGson();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<ArrayList<EDItem>> call=service.loadDataFromED();

        call.enqueue(new Callback<ArrayList<EDItem>>() {
            @Override
            public void onResponse(Call<ArrayList<EDItem>> call, Response<ArrayList<EDItem>> response) {
                items.clear();
                adapter.notifyDataSetChanged();

                ArrayList<EDItem> allED=response.body();

                SharedPreferences preferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                String email=preferences.getString("Email","");

                for(EDItem item:allED){
                    if(email.equals(item.email)){
                        G.edItems.add(0,item);
                        items.add(0,item);
                        adapter.notifyItemInserted(0);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EDItem>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.scrollToPosition(0);


        return view;
    }



}
