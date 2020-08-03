package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OJ extends Fragment {
    ArrayList<OJItem> items=new ArrayList<>();
    OJAdapter adapter;
    RecyclerView recyclerView;
    boolean isConnect=true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_oj,container,false);



        adapter=new OJAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerOJ);
//        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
//        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        Retrofit retrofit=RetrofitHelper.getInstanceFromGson();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<ArrayList<OJItem>> call=service.loadDataFromOJ();



        call.enqueue(new Callback<ArrayList<OJItem>>() {
            @Override
            public void onResponse(Call<ArrayList<OJItem>> call, Response<ArrayList<OJItem>> response) {
                isConnect=true;

                items.clear();
                adapter.notifyDataSetChanged();

                ArrayList<OJItem> allOJ=response.body();


                SharedPreferences preferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                String email=preferences.getString("Email","");




                for(OJItem item:allOJ){
                    if(email.equals(item.email)){
                        G.ojItems.add(0,item);
                        items.add(0,item);
                        adapter.notifyItemInserted(0);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OJItem>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                isConnect=false;
            }
        });




        if(G.ojItems.size()<1 || !isConnect){
            recyclerView.setVisibility(View.INVISIBLE);
            TextView tv=view.findViewById(R.id.noOJ);
            tv.setVisibility(View.VISIBLE);
            return  view;
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            TextView tv=view.findViewById(R.id.noOJ);
            tv.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
