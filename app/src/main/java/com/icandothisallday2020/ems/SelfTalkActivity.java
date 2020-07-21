package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    ArrayList<TalkItem> items =new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference reference1;
    String[] questions;
    int qNum=0;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_talk);


        et=findViewById(R.id.et_msg);
        listView=findViewById(R.id.selfTalk_listView);
        adapter=new SelfTalkAdapter(this, items);
        listView.setAdapter(adapter);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("talk");


        SharedPreferences preferences = getSharedPreferences("Data", MODE_PRIVATE);
        String email = preferences.getString("Email", "Email");
        String emailCompat=email.replace('.','@');
        reference1=reference.child(emailCompat);


        questions=getResources().getStringArray(R.array.QselfTalk);
        items.add(new TalkItem(questions[0],"Q"));
        qNum++;


        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               // String key=reference1.getKey(); //==woohyo17@icloud@com

                TalkItem item=dataSnapshot.getValue(TalkItem.class);
                items.add(item);
                adapter.notifyDataSetChanged();

                if(item.type.equals("Q")) {
                    if(qNum<)qNum++;
                }
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



    public void complete(View view) {

        TalkItem item=new TalkItem(questions[qNum],"Q");
        reference1.push().setValue(item);

    }

    public void send(View view) {

        String msg=et.getText().toString();
        TalkItem item=new TalkItem(msg,"A");
        reference1.push().setValue(item);


        //hide soft keyboard after push
        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus()/*current focus*/.getWindowToken()
                ,0/*flag:Googleing - 0: right now*/);
        et.setText("");

    }

    public void rewind(View view) {
        reference1.removeValue();
        items.clear();
        adapter.notifyDataSetChanged();

        items.add(new TalkItem(questions[0],"Q"));
        qNum++;
        adapter.notifyDataSetChanged();
    }


}
