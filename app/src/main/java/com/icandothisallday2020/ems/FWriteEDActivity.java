package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;

public class FWriteEDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fwed);

//        initRecyclerDialog();





    }



    public void back(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.MyDialog);
        builder.setIcon(R.drawable.ic_alert);
        builder.setTitle("Do you want to exit?");
        builder.setMessage("The text you wrote will not be saved.");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void guide(View view) {
    }

    public void complete(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.MyDialog);
        builder.setIcon(R.drawable.ic_write_color);
        builder.setTitle("\t\t\tDo you want to\n\t\t\t\t complete?");
        builder.setMessage("The text you wrote will be saved.");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void selectEmotion(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.EmoDialog);
        builder.setTitle("Choose Your Emotion");
        builder.setIcon(R.drawable.ic_ed_color);

        String[] emos=getResources().getStringArray(R.array.Love);

        View dv=getLayoutInflater().inflate(R.layout.dialog_fedw,null);
        ImageView emo=dv.findViewById(R.id.dialog_iv);
        final TextView tv=dv.findViewById(R.id.dialog_tv);
        SeekBar seekBar=dv.findViewById(R.id.dialog_sb);
        FlowLayout flowLayout=dv.findViewById(R.id.flow);

        for(String s:emos){
            ToggleButton btn=new ToggleButton(this);
            btn.setText(s);
            btn.setTextOff(s);
            btn.setTextOn(s);
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundResource(R.drawable.emod_btn_write_outline);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btn.isChecked()){
                        btn.setTextColor(Color.parseColor("#8E2FDC"));
                        btn.setBackgroundResource(R.drawable.menu_write_colorline);
                    }else{
                        btn.setTextColor(Color.WHITE);
                        btn.setBackgroundResource(R.drawable.emod_btn_write_outline);
                    }

                }
            });


            FlowLayout.LayoutParams params =btn.getLayoutParams();

            flowLayout.addView(btn);
        }
        builder.setView(dv);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

//
//    private void fillAutoSpacingLayout() {
//        FlowLayout flowLayout = findViewById(R.id.flow);
//        String[] dummyTexts = getResources().getStringArray(R.array.Love);
//
//        for (String text : dummyTexts) {
//            TextView textView = buildLabel(text);
//            flowLayout.addView(textView);
//        }
//    }
//
//    private TextView buildLabel(String text) {
//        TextView textView = new TextView(this);
//        textView.setText(text);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//        textView.setPadding((int)dpToPx(16), (int)dpToPx(8), (int)dpToPx(16), (int)dpToPx(8));
//        textView.setBackgroundResource(R.drawable.menu_write_outline);
//
//        return textView;
//    }
//
//    private float dpToPx(float dp){
//        return TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
//    }
//
//    private void initRecyclerDialog() {
//        RecyclerView recyclerView
//    }


}
