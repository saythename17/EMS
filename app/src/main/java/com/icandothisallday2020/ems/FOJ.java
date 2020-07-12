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

public class FOJ extends Fragment {
    ArrayList<FOJItem> items=new ArrayList<>();
    FOJAdapter adapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_oj,container,false);


        adapter=new FOJAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerOJ);
//        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
//        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new FOJItem("What Are You Wondering?","2020\n07-10"));
        items.add(new FOJItem("What Are You Wondering?","2020\n07-10"));
        items.add(new FOJItem("What Are You Wondering?","2020\n07-10"));
        items.add(new FOJItem("What Are You Wondering?","2020\n07-10"));


    }
}
