package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ED extends Fragment {
    ArrayList<EDItem> items=new ArrayList<>();
    EDAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ed,container,false);
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

                G.edItems=response.body();

                SharedPreferences preferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                String email=preferences.getString("Email","");

                for(EDItem item:G.edItems){
                    if(email.equals(item.email)){
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


        return view;
    }



}
