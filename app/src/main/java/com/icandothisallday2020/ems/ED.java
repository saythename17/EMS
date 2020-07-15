package com.icandothisallday2020.ems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ED extends Fragment {
    ArrayList<EDItem> items=new ArrayList<>();
    EDAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new EDItem());
        items.add(new EDItem());
        items.add(new EDItem());



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ed,container,false);
        adapter=new EDAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerED);
        recyclerView.setAdapter(adapter);
        return view;
    }



}
