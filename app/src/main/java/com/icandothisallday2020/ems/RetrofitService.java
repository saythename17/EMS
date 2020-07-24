package com.icandothisallday2020.ems;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface RetrofitService {

    @Multipart
    @POST("/EMS/insertOJ.php")
    Call<String> postDataToOJ(@PartMap Map<String,String> oj);

    @GET("/EMS/loadOJ.php")
    Call<ArrayList<OJItem>> loadDataFromOJ();

    @Multipart
    @POST("/EMS/insertED.php")
    Call<String> postDataToED(@PartMap Map<String,String> ed);

    @GET("EMS/loadED.php")
    Call<ArrayList<EDItem>> loadDataFromED();

    @Multipart
    @POST("/EMS/insertBP.php")
    Call<String> postDataToBP(@PartMap Map<String,String> bp);

    @GET("EMS/loadBP.php")
    Call<ArrayList<BPItem>> loadDataFromBP();

    @GET("EMS/loadFeed.php")
    Call<ArrayList<FeedItem>> loadDataFromFeed();

}
