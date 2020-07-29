package com.icandothisallday2020.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EDDetailActivity extends AppCompatActivity {
    int position;
    EDDetailAdapter adapter;
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_detail);
        Toolbar toolbar = findViewById(R.id.edItemToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Intent intent=getIntent();
        position=intent.getIntExtra("Position",-1);
        if(position==-1){//-1:wrong position --can't exist
            finish();
            return;
        }


        pager=findViewById(R.id.edPager);
        adapter=new EDDetailAdapter(getLayoutInflater());
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);







        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.close:
                finish();
                break;
            case R.id.delete:
                AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.MyDialog);
                builder.setIcon(R.drawable.ic_alert);
                builder.setTitle("Do you want to delete this dairy?");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int no=G.edItems.get(position).no;
                        Retrofit retrofit=RetrofitHelper.getInstanceFromScalars();
                        RetrofitService service=retrofit.create(RetrofitService.class);

                        Call<String> call=service.deleteDataFromED(no);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(EDDetailActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(EDDetailActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;
            case R.id.edit:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
