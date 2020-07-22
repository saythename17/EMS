package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nex3z.flowlayout.FlowLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteEDActivity extends AppCompatActivity {
    EditText etSituation, etThought, etAction, etResult;
    String situation,thought,action,emotion,result,date,time,email;

    StringBuffer[] buffers=new StringBuffer[15];
    StringBuffer finalBuffer= new StringBuffer();
    TextView emoDegree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_write);

        etSituation =findViewById(R.id.wedSituation);
        etThought =findViewById(R.id.wedThought);
        etAction =findViewById(R.id.wedAction);
        etResult =findViewById(R.id.wedResult);


        String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(permissions[0])== PackageManager.PERMISSION_DENIED)
                requestPermissions(permissions,100);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100&&grantResults[0]==PackageManager.PERMISSION_DENIED)
            Toast.makeText(this, "Can't upload your text in OJ internet board", Toast.LENGTH_SHORT).show();
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
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {





                situation=etSituation.getText().toString();
                thought=etThought.getText().toString();

                for(StringBuffer buffer:buffers){
                    buffer=new StringBuffer();
                    String s=buffer.toString();
                    finalBuffer.append(s);
                }

                emotion=finalBuffer.toString();

                Toast.makeText(WriteEDActivity.this, ""+emotion, Toast.LENGTH_SHORT).show();
                action=etAction.getText().toString();
                result=etResult.getText().toString();
                Calendar now=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                date=format.format(now.getTime());
                SimpleDateFormat format1=new SimpleDateFormat("h:mmaa");
                time=format1.format(now.getTime());
                SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
                email=preferences.getString("Email","");

                if(email.equals("")){
                    Toast.makeText(WriteEDActivity.this,
                            "You can't save own your text.\nYou must agree to provide your email at login.", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                Retrofit retrofit=RetrofitHelper.getInstanceFromScalars();
                RetrofitService service=retrofit.create(RetrofitService.class);
                Map<String,String> dataED=new HashMap<>();
                dataED.put("Situation",situation);
                dataED.put("Thought",thought);
                dataED.put("Emotion",emotion);
                dataED.put("Action",action);
                dataED.put("Result",result);
                dataED.put("Date",date);
                dataED.put("Time",time);
                dataED.put("Email",email);

                Call<String> call=service.postDataToED(dataED);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            String s=response.body();
                            Toast.makeText(WriteEDActivity.this, ""+s, Toast.LENGTH_SHORT).show();

                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Toast.makeText(WriteEDActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        finish();

                    }
                });
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }




    ///////////////////////////////////////////////////////////////////////////////////////////
    public void selectEmotion(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.EmoDialog);
        builder.setTitle("Choose Your Emotion");
        builder.setIcon(R.drawable.ic_ed_color);

        String imageTag=view.getTag().toString();
        int tag=Integer.parseInt(imageTag);
        buffers[tag]= new StringBuffer();
        buffers[tag].append(tag+"_");

        ArrayList<ToggleButton> toggles=new ArrayList<>();


        String[] emos= getResources().getStringArray(R.array.Love);
        switch (tag){
            case 0:
                emos=getResources().getStringArray(R.array.Love);
                break;
            case 1:
                emos=getResources().getStringArray(R.array.Exciting);
                break;
            case 2:
                emos=getResources().getStringArray(R.array.Joy);
                break;
            case 3:
                emos=getResources().getStringArray(R.array.Bored);
                break;
            case 4:
                emos=getResources().getStringArray(R.array.Smile);
                break;
            case 5:
                emos=getResources().getStringArray(R.array.Confused);
                break;
            case 6:
                emos=getResources().getStringArray(R.array.Angry);
                break;
            case 7:
                emos=getResources().getStringArray(R.array.Sad);
                break;
            case 8:
                emos=getResources().getStringArray(R.array.Embarrassed);
                break;
            case 9:
                emos=getResources().getStringArray(R.array.Disappointed);
                break;
            case 10:
                emos=getResources().getStringArray(R.array.Sardonic);
                break;
            case 11:
                emos=getResources().getStringArray(R.array.Cloudy);
                break;
            case 12:
                emos=getResources().getStringArray(R.array.Vomited);
                break;
            case 13:
                emos=getResources().getStringArray(R.array.Shock);
                break;
            case 14:
                emos=getResources().getStringArray(R.array.WTF);
                break;


        }


        View dv=getLayoutInflater().inflate(R.layout.dialog_ed_write,null);
        ImageView emo=dv.findViewById(R.id.dialog_iv);

        emo.setImageResource(R.drawable.e00_love +tag);
        emoDegree=dv.findViewById(R.id.dialog_tv);
        SeekBar seekBar=dv.findViewById(R.id.dialog_sb);
        FlowLayout flowLayout=dv.findViewById(R.id.flow);


        for(String s:emos){
            ToggleButton btn=new ToggleButton(this);

            toggles.add(btn);


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
                emoDegree.setText(""+progress);
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
                for(ToggleButton tb: toggles){
                    if(tb.isChecked()) buffers[tag].append(tb.getText()+",");
                }

                buffers[tag].append("_"+emoDegree.getText()+"!");
                Log.i("log",""+buffers[tag].toString());

                if(buffers[tag].toString().length()<=10){
                    Toast.makeText(WriteEDActivity.this, "Please Select Your Emotions", Toast.LENGTH_SHORT).show();
                    ((ImageView) view).setColorFilter(null);

                }else{
                    Toast.makeText(WriteEDActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                    finalBuffer.append(buffers[tag].toString());

                    int color=Color.parseColor("#8E2FDC");
                    ((ImageView) view).setColorFilter(color);
                }


            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ImageView) view).setColorFilter(null);
            }
        });
        final AlertDialog dialog=builder.create();
        dialog.show();
    }//////////////////////////////////////////////////////////////////////////////////////////////


}
