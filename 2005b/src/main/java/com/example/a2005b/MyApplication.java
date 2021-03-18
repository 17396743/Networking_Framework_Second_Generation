package com.example.a2005b;

import android.app.Application;

import com.example.myhttptwolibrary.global.GlobalConfig;
import com.example.myhttptwolibrary.utils.LogUtils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 项目名：Shopping
 * 包名：  com.example.a2005b
 * 文件名：MyApplication
 * 创建者：liangxq
 * 创建时间：2021/3/9  17:09
 * 描述：TODO
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e("Okhttp拦截器：(文件名：MyApplication)       "+message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        GlobalConfig.getInstance()
                .addInterceptors(httpLoggingInterceptor);

    }
}
