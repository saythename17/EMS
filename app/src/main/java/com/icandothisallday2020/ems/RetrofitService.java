package com.icandothisallday2020.ems;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface RetrofitService {

    @Multipart
    @POST("/EMS/insertOJ.php")
    Call<String> postDataToOJ(@PartMap Map<String,String> oj);

    @GET("/EMS/loadOJ.php")
    Call<ArrayList<OJItem>> loadDataFromOJ();
    
    @GET("/EMS/deleteOJ.php")
    Call<String> deleteDataFromOJ(@Query("no") int no);

    @POST("/EMS/updateOJ.php")
    Call<String> updateDataFromOJ(@Query("no") int no,@Query("a") String a);

    @Multipart
    @POST("/EMS/insertED.php")
    Call<String> postDataToED(@PartMap Map<String,String> ed);

    @GET("EMS/loadED.php")
    Call<ArrayList<EDItem>> loadDataFromED();

    @GET("/EMS/deleteED.php")
    Call<String> deleteDataFromED(@Query("no") int no);

    @Multipart
    @POST("/EMS/insertBP.php")
    Call<String> postDataToBP(@PartMap Map<String,String> bp);

    @GET("EMS/loadBP.php")
    Call<ArrayList<BPItem>> loadDataFromBP();

    @GET("/EMS/deleteBP.php")
    Call<String> deleteDataFromBP(@Query("no") int no);

    @GET("EMS/loadFeed.php")
    Call<ArrayList<FeedItem>> loadDataFromFeed();
}
