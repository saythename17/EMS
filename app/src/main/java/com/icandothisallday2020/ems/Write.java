package com.icandothisallday2020.ems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Write extends Fragment {
    Button btnED,btnOJ,btnBP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        btnED=getView().findViewById(R.id.writeEDbtn);
//        btnOJ=getView().findViewById(R.id.writeOJbtn);
//        btnBP=getView().findViewById(R.id.writeBPbtn);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_write,container,false);
        btnED=view.findViewById(R.id.writeED);
        btnOJ=view.findViewById(R.id.writeOJ);
        btnBP=view.findViewById(R.id.writeBP);
        btnED.setOnClickListener(btnClick);
        btnOJ.setOnClickListener(btnClick);
        btnBP.setOnClickListener(btnClick);
        return view;
    }

    View.OnClickListener btnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.writeED:
                    Intent  intent=new Intent(getContext(), WriteEDActivity.class);
                    startActivity(intent);
                    break;
                case R.id.writeOJ:
                    Intent intent2=new Intent(getContext(), WriteOJActivity.class);
                    startActivity(intent2);
                    break;



                case R.id.writeBP:
                    Intent intent3=new Intent(getContext(), WriteBPActivity.class);
                    startActivity(intent3);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainActivity mainActivity=(MainActivity) getActivity();
        switch (requestCode){
            case 1:
                mainActivity.pager.setCurrentItem(1);
                break;
            case 2:
                mainActivity.pager.setCurrentItem(3);
                break;
            case 3:
                mainActivity.pager.setCurrentItem(4);
                break;

        }
    }
}
