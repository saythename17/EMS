package com.icandothisallday2020.ems;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //KakaoAdapter : 카카오 SDK 와 앱의 Application 연결
        //카카오 초기화
        KakaoSDK.init(new KakaoAdapter() {
            @Override
            public IApplicationConfig getApplicationConfig() {
                //Application 이 가지고 있는 정보를 얻기 위한 인터페이스
                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return MyApp.this;//나를 리턴
                    }
                };
            }
        });

    }
}
