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
    Call<String> postDataToOJB(@PartMap Map<String,String> oj);

    @GET("/EMS/loadOJ.php")
    Call<ArrayList<OJItem>> loadDataFromOJB();

}
