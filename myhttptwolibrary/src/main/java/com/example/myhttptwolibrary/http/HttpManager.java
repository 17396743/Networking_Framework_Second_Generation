package com.example.myhttptwolibrary.http;

import com.example.myhttptwolibrary.global.GlobalConfig;
import com.example.myhttptwolibrary.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名：Shopping
 * 包名：  com.example.myhttptwolibrary.http
 * 文件名：HttpManager
 * 创建者：liangxq
 * 创建时间：2021/3/9  16:42
 * 描述：TODO
 */
public class HttpManager {

    private static volatile HttpManager httpManager;


    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }

        return httpManager;
    }


    private Retrofit getRetrofit(String baseUrl,long timeout, TimeUnit timeUnit) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpClient(timeout,timeUnit))
                .build();
    }

    public OkHttpClient getOkhttpClient(long timeout, TimeUnit timeUnit) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, timeUnit);
        builder.writeTimeout(timeout, timeUnit);
        builder.readTimeout(timeout, timeUnit);
        builder.retryOnConnectionFailure(true);
        if(GlobalConfig.getInstance().getInterceptors()!=null){
            for (Interceptor interceptor : GlobalConfig.getInstance().getInterceptors()) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }


    public <T> T createServer(Class<T> mClass, String baseUrl,long timeout, TimeUnit timeUnit) {
        return getRetrofit(baseUrl,timeout,timeUnit).create(mClass);
    }
}
