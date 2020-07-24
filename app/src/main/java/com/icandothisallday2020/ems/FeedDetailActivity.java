package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class FeedDetailActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter spinnerAdapter;
    ImageView close,iv;
    RecyclerView recyclerView;
    ArrayList<CItem> items=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText comment;
    TextView count,like,title,text;
    FeedCommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        spinner=findViewById(R.id.spinner);
        recyclerView=findViewById(R.id.recycler_comments);
        comment=findViewById(R.id.FDet);
        count=findViewById(R.id.count);
        like=findViewById(R.id.FDlike);
        commentAdapter=new FeedCommentAdapter(this,items);
        recyclerView.setAdapter(commentAdapter);

        title=findViewById(R.id.FDtitle);
        text=findViewById(R.id.FDtext);
        iv=findViewById(R.id.FDiv);


        String title=getIntent().getStringExtra("Title");
        String content=getIntent().getStringExtra("Content");
        String file=getIntent().getStringExtra("File");

        this.title.setText(title);
        text.setText(content);
        Glide.with(this).load("http://soon0.dothome.co.kr/EMS/"+file).into(iv);




        spinnerAdapter =ArrayAdapter.createFromResource(this,R.array.comments,R.layout.spinner_selected);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        close=findViewById(R.id.FDx);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        database=FirebaseDatabase.getInstance();
        reference=database.getReference("comments");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                CItem item=dataSnapshot.getValue(CItem.class);
                items.add(item);
                commentAdapter.notifyItemInserted(items.size()-1);
                count.setText("\t"+items.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void like(View view) {
    }

    public void scrap(View view) {
    }

    public void share(View view) {
    }

    public void send(View view) {
        SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
        G.userName=preferences.getString("Name","User");
        G.userEmail=preferences.getString("Email","Unknown");
        G.userProfileUrl=preferences.getString("ProfileUrl","No File");
        Calendar calendar=Calendar.getInstance();
        @SuppressLint("WrongConstant")
        String date=""+calendar.get(Calendar.YEAR)+"."+
                    (calendar.get(Calendar.MONTH)+1)+"."+
                    calendar.get(Calendar.DAY_OF_MONTH);
        String up=""+0;
        String down=""+0;
        String comment=this.comment.getText().toString();

        CItem item=new CItem(G.userName,G.userProfileUrl,G.userEmail,comment,date,up,down);
        reference.push().setValue(item);

        this.comment.setText("");
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}
