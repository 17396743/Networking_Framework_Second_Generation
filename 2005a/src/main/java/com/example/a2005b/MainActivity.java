package com.example.a2005b;

//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.app.*;
//import android.support.v4.app.*;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2005b.bean.Demo;
import com.example.a2005b.callback.HttpCallBack;
import com.example.myhttptwolibrary.HttpServer;
import com.example.myhttptwolibrary.client.HttpClient;
import com.example.myhttptwolibrary.http.HttpManager;
import com.example.myhttptwolibrary.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.HTTP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpManager.getInstance().createServer(HttpServer.class,"https://wanandroid.com/",5, TimeUnit.SECONDS)
                .get("article/listproject/0/json",new HashMap<String, Object>(),new HashMap<String, Object>())
                .subscribeOn(Schedulers.io())
                .map(new Function<JsonElement, Object>() {
                    @Override
                    public Object apply(JsonElement jsonElement) throws Exception {
                        LogUtils.e("OkhttpMap数据：（文件名：MainActivity）      "+Thread.currentThread().getName()+"1111111");
                        return new Gson().toJson(jsonElement);
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new HttpCallBack<Demo>() {
                    @Override
                    protected void onSucess(Demo demo) {

                        Log.e("liangxq", "Okhttp全部网络解析数据：（文件名：MainActivity）     +onSucess: "+demo.getDatas() );
                    }

                    @Override
                    public Demo convert(JsonElement jsonElement) {
                        return new Gson().fromJson(jsonElement,Demo.class);
                    }

                    @Override
                    protected void error(String error, int code) {

                    }
                });



        new HttpClient.Buidler()
                .setBaseUrl("https://wanandroid.com/")
                .setApiUrl("article/listproject/0/json")
                .get()
                .build()
                .excute(new HttpCallBack() {
            @Override
            protected void onSucess(Object o) {

            }

            @Override
            public Object convert(JsonElement jsonElement) {
                return null;
            }

            @Override
            protected void error(String error, int code) {

            }
        });

    }
}
