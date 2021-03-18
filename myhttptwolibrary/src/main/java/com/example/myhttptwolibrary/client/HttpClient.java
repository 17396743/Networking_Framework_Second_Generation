package com.example.myhttptwolibrary.client;

import android.text.TextUtils;

import com.example.myhttptwolibrary.Constans;
import com.example.myhttptwolibrary.HttpServer;
import com.example.myhttptwolibrary.callback.BaseCallBack;
import com.example.myhttptwolibrary.global.GlobalConfig;
import com.example.myhttptwolibrary.http.HttpManager;
import com.example.myhttptwolibrary.http.Method;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * 项目名：Shopping
 * 包名：  com.example.myhttptwolibrary.client
 * 文件名：HttpClient
 * 创建者：liangxq
 * 创建时间：2021/3/11  15:44
 * 描述：TODO
 */
public class HttpClient {
    //请求方式
    Method method;
    //请求参数
    Map<String, Object> paramser;
    //请求头信息
    Map<String, Object> headres;
    //Rxjava绑定生命周期
    LifecycleProvider lifecycleProvider;
    //绑定Activity具体的生命周的
    ActivityEvent activityEvent;
    //绑定Fragment的具体的生命周期的
    FragmentEvent fragmentEvent;
    String baseUrl;
    //拼接的url
    String apiUrl;
    //是否是json上传表示
    boolean isJson;
    //json字符串
    String jsonbody;
    //超时时间
    long time;
    //时间单位
    TimeUnit timeUnit;
    //回调接口
    BaseCallBack baseCallBack;

    public HttpClient(Buidler buidler) {
        this.paramser=buidler.paramser;
        this.headres=buidler.headres;
        this.lifecycleProvider=buidler.lifecycleProvider;
        this.fragmentEvent=buidler.fragmentEvent;
        this.activityEvent = buidler.activityEvent;
        this.baseUrl=buidler.baseUrl;
        this.apiUrl=buidler.apiUrl;
        this.timeUnit=buidler.timeUnit;
        this.time=buidler.time;
        this.jsonbody=buidler.jsonbody;
        this.isJson=buidler.isJson;
        this.method = buidler.method;
    }

    public HttpClient() {
        this(new Buidler());
    }

    //发起请求
    public void excute(BaseCallBack baseCallBack){
        if(baseCallBack!=null){
            new RuntimeException("callback not null");
        }
        this.baseCallBack=baseCallBack;
        buildParams();
        buildHeaders();
        request();
    }




    //构建参数
    private void buildParams() {
        if(GlobalConfig.getInstance().getBaseparams()!=null){
            this.paramser.putAll(GlobalConfig.getInstance().getBaseparams());
        }
    }
    //构建请求头
    private void buildHeaders() {
        if(GlobalConfig.getInstance().getBaseHeades()!=null){
            this.headres.putAll(GlobalConfig.getInstance().getBaseHeades());
        }
    }

    //发起请求
    private void request() {
      if(this.baseUrl==null){
          this.baseUrl=GlobalConfig.getInstance().getBaseUrl();
      }
      if(this.baseUrl==null){
          new RuntimeException("baseUrl not null");
      }

      if(GlobalConfig.getInstance().getTimeout()!=0){
          this.time=GlobalConfig.getInstance().getTimeout();
      }

      if(GlobalConfig.getInstance().getTimeUnit()!=null){
          this.timeUnit=GlobalConfig.getInstance().getTimeUnit();
      }
        HttpServer server = HttpManager.getInstance().createServer(HttpServer.class, this.baseUrl, time, timeUnit);
        Observable observable = createObservable(server);
//        observable.subscribe(baseCallBack);

    }

    private Observable createObservable(HttpServer server) {
        boolean hasBodyString = !TextUtils.isEmpty(jsonbody);
        RequestBody requestBody = null;
        if (hasBodyString) {
            String mediaType = isJson ? "application/json; charset=utf-8" : "text/plain;charset=utf-8";
            requestBody = RequestBody.create(okhttp3.MediaType.parse(mediaType), jsonbody);
        }
        Observable observable=null;
        switch (this.method){
            case GET:
                observable=server.get(apiUrl,paramser,headres);
                break;
            case POST:
                if(isJson){
                    observable=server.postjson(apiUrl,requestBody,headres);
                }else{
                    observable=server.post(apiUrl,paramser,headres);
                }
                break;
        }
        return observable;
    }

    public static class Buidler {
        //请求方式
        Method method;
        //请求参数
        Map<String, Object> paramser;
        //请求头信息
        Map<String, Object> headres;
        //Rxjava绑定生命周期
        LifecycleProvider lifecycleProvider;
        //绑定Activity具体的生命周的
        ActivityEvent activityEvent;
        //绑定Fragment的具体的生命周期的
        FragmentEvent fragmentEvent;
        String baseUrl;
        //拼接的url
        String apiUrl;
        //是否是json上传表示
        boolean isJson;
        //json字符串
        String jsonbody;
        //超时时间
        long time;
        //时间单位
        TimeUnit timeUnit;

        public Buidler() {
            paramser=new HashMap<>();
            headres=new HashMap<>();
            this.time= Constans.TIME_OUT;
            this.timeUnit=Constans.TIMEUNIT;
        }

        public Buidler get(){
            this.method=Method.GET;
            return this;
        }

        public Buidler post(){
            this.method=Method.POST;
            return this;
        }

        public Buidler delete(){
            this.method=Method.DELETE;
            return this;
        }

        public Buidler put(){
            this.method=Method.PUT;
            return this;
        }

        public Map<String, Object> getParamser() {
            return paramser;
        }

        public Map<String, Object> getHeadres() {
            return headres;
        }

        public LifecycleProvider getLifecycleProvider() {
            return lifecycleProvider;
        }

        public ActivityEvent getActivityEvent() {
            return activityEvent;
        }

        public FragmentEvent getFragmentEvent() {
            return fragmentEvent;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }


        public Buidler setParamser(Map<String, Object> paramser) {
            if(paramser!=null){
                this.paramser.putAll(paramser);
            }
            return this;
        }

        public Buidler setHeadres(Map<String, Object> headres) {
            if(headres!=null){
                this.headres .putAll(headres);
            }
            return this;
        }

        public Buidler setLifecycleProvider(LifecycleProvider lifecycleProvider) {
            this.lifecycleProvider = lifecycleProvider;
            return this;
        }

        public Buidler setActivityEvent(ActivityEvent activityEvent) {
            this.activityEvent = activityEvent;
            return this;
        }

        public Buidler setFragmentEvent(FragmentEvent fragmentEvent) {
            this.fragmentEvent = fragmentEvent;
            return this;
        }

        public Buidler setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Buidler setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        public Buidler setTime(long time) {
            this.time = time;
            return this;
        }

        public Buidler setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }


        public Buidler buileJson(boolean isJson,String jsonbody){
            this.isJson=isJson;
            this.jsonbody=jsonbody;
            return this;
        }
        public HttpClient build() {
            return new HttpClient(this);
        }

    }
}
