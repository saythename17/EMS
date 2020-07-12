package com.icandothisallday2020.ems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FFeed extends Fragment {
   ArrayList<FFeedItem> items=new ArrayList<>();
   FFeedAdapter adapter;
   RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new FFeedItem("https://images.unsplash.com/photo-1569360531163-a61fa3da86ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=376&q=80",
                "How to Write the Emotion Diary1","These rooms got a lot of space. Crowded but a lonely place Sittin' at a table ful of double dates And everywhere I go I wanna see your face. "));
        items.add(new FFeedItem("https://images.unsplash.com/photo-1569360531163-a61fa3da86ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=376&q=80",
                "How to Write the Emotion Diary2","These rooms got a lot of space. Crowded but a lonely place Sittin' at a table ful of double dates And everywhere I go I wanna see your face. "));
        items.add(new FFeedItem("https://images.unsplash.com/photo-1569360531163-a61fa3da86ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=376&q=80",
                "How to Write the Emotion Diary3","These rooms got a lot of space. Crowded but a lonely place Sittin' at a table ful of double dates And everywhere I go I wanna see your face. "));
        items.add(new FFeedItem("https://images.unsplash.com/photo-1569360531163-a61fa3da86ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=376&q=80",
                "How to Write the Emotion Diary4","These rooms got a lot of space. Crowded but a lonely place Sittin' at a table ful of double dates And everywhere I go I wanna see your face. "));


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_feed,container,false);
        adapter=new FFeedAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerFeed);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerView.setLayoutManager(manager);
        //TODO focus on most recent item
        return view;
    }
}
