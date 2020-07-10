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

public class FOJ extends Fragment {
    ArrayList<OJItem> items=new ArrayList<>();
    OJAdapter adapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_oj,container,false);
        items.add(new OJItem("What Are You Wondering?","2020\n07-10"));
        items.add(new OJItem("What Are You Wondering?","2020\n07-10"));
        items.add(new OJItem("What Are You Wondering?","2020\n07-10"));
        items.add(new OJItem("What Are You Wondering?","2020\n07-10"));

        adapter=new OJAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.ojRecycler);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
