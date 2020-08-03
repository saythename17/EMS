package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
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

    RewardedVideoAd ad;

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

        ad=MobileAds.getRewardedVideoAdInstance(this);


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

    boolean isLike=false;
    public void like(View view) {
        if(!isLike){
            Toast.makeText(this, "\uD83E\uDD29❤", Toast.LENGTH_SHORT).show();
            TextView like=(TextView) view;
            Drawable img = this.getResources().getDrawable( R.drawable.ic_like_red );
            img.setBounds(60,60,60,60);
            like.setCompoundDrawablesWithIntrinsicBounds(null, img,null,null);
            like.setText(""+(Integer.parseInt(like.getText().toString())+1));
            isLike=true;
        }else{
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            TextView like=(TextView) view;
            Drawable img = this.getResources().getDrawable( R.drawable.ic_like );
            img.setBounds(60,60,60,60);
            like.setCompoundDrawablesWithIntrinsicBounds(null, img,null,null);
            like.setText(""+(Integer.parseInt(like.getText().toString())-1));
            isLike=false;
        }



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

    public void adView(View view) {

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        AdLoader adLoader = new AdLoader.Builder(FeedDetailActivity.this, "ca-app-pub-2102574529234286~1004069335")
//                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                    @Override
//                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                        // Show the ad.
//                    }
//                })
//                .withAdListener(new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(int errorCode) {
//                        // Handle the failure by logging, altering the UI, and so on.
//                    }
//                })
//                .withNativeAdOptions(new NativeAdOptions.Builder()
//                        // Methods in the NativeAdOptions.Builder class can be
//                        // used here to specify individual options settings.
//                        .build())
//                .build();
//        adLoader.loadAd(new AdRequest.Builder().build());
        ad.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                ad.show();
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                Toast.makeText(FeedDetailActivity.this, "Thank you♡", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
        AdRequest request=new AdRequest.Builder().build();
        ad.loadAd("ca-app-pub-3940256099942544/5224354917",request);
        //TODO change Id  : ca-app-pub-2102574529234286/4100064012
        //test ID : ca-app-pub-3940256099942544/5224354917
    }
}
