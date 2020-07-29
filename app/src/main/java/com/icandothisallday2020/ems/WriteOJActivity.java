package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteOJActivity extends AppCompatActivity {
    ImageView guide, complete, write;
    TextView question;
    EditText userET;
    String edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oj_write);
        guide=findViewById(R.id.fwoj_guide);
        complete=findViewById(R.id.fwoj_complete);
        write=findViewById(R.id.fwoj_write);
        question =findViewById(R.id.ojQ);
        userET =findViewById(R.id.user_writeOJ);

//        Intent intent=getIntent();
//        edit=intent.getStringExtra("id");
//        int position=intent.getIntExtra("position",-1);
//        if(edit.equals("edit")){
//            if(position>-1) userET.setText(G.ojItems.get(position).a);
//        }







        //TODO  one day , one write oj TODO
        ///////////////////////////////////////////--Alarm Sound Off////////////////
        Intent playIntent=new Intent(this,AlarmSoundService.class);
        playIntent.putExtra("state","off");

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            this.startForegroundService(playIntent);
        }else{
            this.startService(playIntent);
        }////////////////////////////////////////////////////////////////////////////

        String[] questions=getResources().getStringArray(R.array.OJQ);
//        SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
//        SharedPreferences.Editor editor=preferences.edit();
//        editor.putInt("OJQ",G.ojItems.size());
        question.setText(questions[G.ojItems.size()-1]);


        userET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    write.setVisibility(View.GONE);
                }/*else{
                    if(!userOJ.getText().equals(""))  write.setVisibility(View.VISIBLE);
                }*/
            }
        });

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
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String q=question.getText().toString();
                String a= userET.getText().toString();
                Calendar now=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd h:mmaa");
                String date=format.format(now.getTime());
                SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
                String email=preferences.getString("Email","Everyone/'s Board");
                if(email.equals("Everyone/'s Board")){
                    Toast.makeText(WriteOJActivity.this,
                            "You can't save own your text.\nYou must agree to provide your email when you login.",
                            Toast.LENGTH_SHORT).show();

                    finish();
                    return;
                }



                Retrofit retrofit=RetrofitHelper.getInstanceFromScalars();
                RetrofitService service=retrofit.create(RetrofitService.class);
                Map<String, String> dataOJ=new HashMap<>();
                dataOJ.put("Q",q);
                dataOJ.put("A",a);
                dataOJ.put("Date",date);
                dataOJ.put("Email",email);

                Call<String> call=service.postDataToOJ(dataOJ);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            String s=response.body();
                            Toast.makeText(WriteOJActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                            G.numOJ+=1;

                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(WriteOJActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });


            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }





    //Uri -- > Absolute path  return method
    String getRealPathFromUri(Uri uri){
        String[] strings= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, strings, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

}
