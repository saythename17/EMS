package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteBPActivity extends AppCompatActivity {
    TextView deadline,answer1,answer2,answer3,answer4, userProgress;
    SeekBar seekBar;
    LinearLayout container;
    int tableRow=1;
    ArrayList<EditText> ets=new ArrayList<>();
    ArrayList<String> plans=new ArrayList<>();
    EditText goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_write);
        deadline=findViewById(R.id.plan_deadline);
        goal=findViewById(R.id.plan_goal);

        container=findViewById(R.id.table);
        answer1=findViewById(R.id.bpet1);
        answer2=findViewById(R.id.bpet2);
        answer3=findViewById(R.id.bpet3);
        answer4=findViewById(R.id.bpet4);
        seekBar=findViewById(R.id.now);
        userProgress=findViewById(R.id.real_progress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userProgress.setText(""+progress+"%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ets.add(findViewById(R.id.plan_detail));

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
            Toast.makeText(this, "Can't upload your text in BP internet board", Toast.LENGTH_SHORT).show();
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
                String bliss=answer1.getText().toString();
                String reason=answer2.getText().toString();
                String ideal=answer3.getText().toString();
                String reality=answer4.getText().toString();

                SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
                String email=preferences.getString("Email","Everyone/'s Board");
                if(email.equals("Everyone/'s Board")){
                    Toast.makeText(WriteBPActivity.this,
                            "You can't save own your text.\nYou must agree to provide your email when you login.",
                            Toast.LENGTH_SHORT).show();

                    finish();
                    return;
                }


                Retrofit retrofit=RetrofitHelper.getInstanceFromScalars();
                RetrofitService service=retrofit.create(RetrofitService.class);
                Map<String,String> dataBP=new HashMap<>();
                dataBP.put("Bliss",bliss);
                dataBP.put("Reason",reason);
                dataBP.put("Ideal",ideal);
                dataBP.put("Reality",reality);
                dataBP.put("Progress",""+seekBar.getProgress());
                dataBP.put("Goal",goal.getText().toString());
                dataBP.put("Deadline",deadline.getText().toString());



                for(int i=0;i<ets.size();i++){
                   plans.add(ets.get(i).getText().toString());
                }

                JSONArray jsonArray=new JSONArray(plans);
                String jsonString=jsonArray.toString();
                Log.i("json",jsonString);

                dataBP.put("Plans",jsonString);
                dataBP.put("Email",email);

                Call<String> call=service.postDataToBP(dataBP);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s=response.body();
                        Toast.makeText(WriteBPActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(WriteBPActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void createTable(View view) {

    }

    public void selectDeadLine(View view) {
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(WriteBPActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                Toast.makeText(MainActivity.this, ""+year+"/"+month+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
                deadline.setText(""+year+"-"+(month+1)+"-"+dayOfMonth);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void addTableRow(View view) {


        View tableRow = (View) getLayoutInflater().inflate(R.layout.table_row_bp_write,container,false);
        TextView tv=tableRow.findViewById(R.id.tableTV);
        this.tableRow++;
        tv.setText(""+ this.tableRow);
        ets.add(tableRow.findViewById(R.id.tableET));


        container.addView(tableRow);
        //hide keyboard
        InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
