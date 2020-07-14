package com.icandothisallday2020.ems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FED extends Fragment {
    ArrayList<FEDItem> items=new ArrayList<>();
    FEDAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new FEDItem());
        items.add(new FEDItem());
        items.add(new FEDItem());



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ed,container,false);
        adapter=new FEDAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerED);
        recyclerView.setAdapter(adapter);
        return view;
    }



}
