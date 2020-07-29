package com.icandothisallday2020.ems;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    public  static Retrofit getInstanceFromGson(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://soon0.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    public static Retrofit getInstanceFromScalars(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://soon0.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        return builder.build();
    }
}
