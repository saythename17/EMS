package com.icandothisallday2020.ems;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.race604.drawable.wave.WaveDrawable;

public class BP extends Fragment {
    Spinner spinner;
    ArrayAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bp,container,false);


        spinner=view.findViewById(R.id.spinnerBP);
        adapter=ArrayAdapter.createFromResource(getContext(),R.array.comments,R.layout.spinner_bp_selected);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);



        CircleProgressBar circleProgressBar=view.findViewById(R.id.cpb_circlebar);

        circleProgressBar.setProgress(100);  // 해당 퍼센트를 적용
        spinner.setAdapter(adapter);
        return view;
    }


}
