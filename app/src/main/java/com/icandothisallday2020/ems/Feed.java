package com.icandothisallday2020.ems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Feed extends Fragment {
   ArrayList<FeedItem> items=new ArrayList<>();
   FeedAdapter adapter;
   RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Retrofit retrofit=RetrofitHelper.getInstanceFromGson();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<ArrayList<FeedItem>> call=service.loadDataFromFeed();
        call.enqueue(new Callback<ArrayList<FeedItem>>() {
            @Override
            public void onResponse(Call<ArrayList<FeedItem>> call, Response<ArrayList<FeedItem>> response) {
                items.clear();
                adapter.notifyDataSetChanged();

                G.feedItems=response.body();
                for(FeedItem item:G.feedItems){
                    items.add(item);
                    adapter.notifyItemInserted(0);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<FeedItem>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_feed,container,false);
        adapter=new FeedAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerFeed);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerView.setLayoutManager(manager);

        recyclerView.scrollToPosition(0);
//        recyclerView.scrollToPosition(items.size()-1);// focus on most recent item


        return view;
    }
}
