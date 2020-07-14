package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FWriteEDActivity extends AppCompatActivity {
    ArrayList<ArrayList> emotions=new ArrayList<ArrayList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fwed);

        ArrayList love=new ArrayList();
        love.add("Lovely"); love.add("Fondly"); love.add("Favourable");
        love.add("Passionate");
        emotions.add(love);


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
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Choose Your Emotion");
        builder.setIcon(R.drawable.ic_ed_color);


    }
}
