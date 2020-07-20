package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class WriteBPActivity extends AppCompatActivity {
    TextView deadline;
    LinearLayout container;
    int tableRow=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_write);
        deadline=findViewById(R.id.deadline);
        container=findViewById(R.id.table);
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
                deadline.setText(""+year+"-"+month+"-"+dayOfMonth);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void addTableRow(View view) {


        View tableRow = (View) getLayoutInflater().inflate(R.layout.table_row_bp,container,false);
        TextView tv=tableRow.findViewById(R.id.tableTV);
        this.tableRow++;
        tv.setText(""+ this.tableRow);
        EditText et=tableRow.findViewById(R.id.tableET);

        container.addView(tableRow);
        //hide keyboard
        InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
