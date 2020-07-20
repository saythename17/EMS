package com.icandothisallday2020.ems;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SelfTalkActivity extends AppCompatActivity {
    ListView listView;
    EditText et;
    SelfTalkAdapter adapter;
    ArrayList<String> talks=new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_talk);


        et=findViewById(R.id.et_msg);
        listView=findViewById(R.id.selfTalk_listView);
        adapter=new SelfTalkAdapter(this, talks);
        listView.setAdapter(adapter);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("talk");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String talk= (String) dataSnapshot.getValue();
                talks.add(talk);
                adapter.notifyDataSetChanged();
                listView.setSelection(talks.size()-1);
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

    public void send(View view) {
        Toast.makeText(this, "?", Toast.LENGTH_SHORT).show();

        SharedPreferences preferences = getSharedPreferences("Data", MODE_PRIVATE);
        String email = preferences.getString("Email", "");
        String msg=et.getText().toString();

        Talk talk=new Talk(email,msg,"");
        reference.push().setValue(talk);
//
//        //hide soft keyboard after push
//        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus()/*current focus*/.getWindowToken()
//                ,0/*flag:Googleing - 0: right now*/);


    }

    public void rewind(View view) {
    }
}
