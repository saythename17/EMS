package com.icandothisallday2020.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OJ extends Fragment {
    ArrayList<OJItem> items=new ArrayList<>();
    OJAdapter adapter;
    RecyclerView recyclerView;

    SwipeLayout swipe_sample1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_oj,container,false);














        swipe_sample1=(SwipeLayout)view.findViewById(R.id.swipe_sample1);
        swipe_sample1.setShowMode(SwipeLayout.ShowMode.LayDown);
//오른쪽에서 나오는 drag (tag로 설정한 HideTag가 보여짐
        swipe_sample1.addDrag(SwipeLayout.DragEdge.Right,swipe_sample1.findViewWithTag("HideTag"));
//swipe_layout을 클릭한 경우
        swipe_sample1.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });
//star버튼을 클릭한 경우
        swipe_sample1.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Star", Toast.LENGTH_SHORT).show();
            }
        });







        adapter=new OJAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.recyclerOJ);
//        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
//        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        Retrofit retrofit=RetrofitHelper.getInstanceFromGson();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<ArrayList<OJItem>> call=service.loadDataFromOJ();



        call.enqueue(new Callback<ArrayList<OJItem>>() {
            @Override
            public void onResponse(Call<ArrayList<OJItem>> call, Response<ArrayList<OJItem>> response) {

                items.clear();
                adapter.notifyDataSetChanged();

                G.ojItems=response.body();


                SharedPreferences preferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                String email=preferences.getString("Email","");




                for(OJItem item:G.ojItems){
                    if(email.equals(item.email)){
                        items.add(0,item);
                        adapter.notifyItemInserted(0);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OJItem>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
