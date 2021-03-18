package com.example.myhttptwolibrary.global;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;

/**
 * 项目名：Shopping
 * 包名：  com.example.myhttptwolibrary.global
 * 文件名：GlobalConfig
 * 创建者：liangxq
 * 创建时间：2021/3/9  17:02
 * 描述：TODO
 */
public class GlobalConfig {
    //baseUrl
    private String baseUrl;
    private long timeout;
    private TimeUnit timeUnit;
    //公共请求参数
    private Map<String, Object> baseparams;
    //公共的请求头信息
    private Map<String, Object> baseHeades;
    //公共的拦截器
    private List<Interceptor> interceptors;
    //日志开关
    private boolean isShowLog;

    private Context context;

    private Handler handler;

    //存储各种appkey的集合
    private Map<String, String> appkeys;

    private static class GlobalConfigHodel {
        private static GlobalConfig INSTANCE = new GlobalConfig();
    }

    public static GlobalConfig getInstance() {
        return GlobalConfigHodel.INSTANCE;
    }

    public GlobalConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public GlobalConfig setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    public GlobalConfig setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public GlobalConfig setBaseparams(Map<String, Object> baseparams) {
        this.baseparams = baseparams;
        return this;
    }

    public GlobalConfig addBaseparams(String key, String value) {
        if (this.baseparams == null) {
            this.baseparams = new HashMap<>();
        }
        this.baseparams.put(key, value);
        return this;
    }

    public GlobalConfig setBaseHeades(Map<String, Object> baseHeades) {
        this.baseHeades = baseHeades;
        return this;
    }

    public GlobalConfig addBaseHeades(String key, String value) {
        this.baseHeades = baseHeades;
        if (this.baseHeades == null) {
            this.baseHeades = new HashMap<>();
        }
        this.baseHeades.put(key, value);
        return this;
    }

    public GlobalConfig setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
        return this;
    }

    public GlobalConfig addInterceptors(Interceptor interceptors) {
        if (this.interceptors == null) {
            this.interceptors = new ArrayList<>();
        }
        this.interceptors.add(interceptors);
        return this;
    }

    public GlobalConfig setShowLog(boolean showLog) {
        isShowLog = showLog;
        return this;
    }

    public GlobalConfig setContext(Context context) {
        this.context = context;
        return this;
    }

    public GlobalConfig setHandler(Handler handler) {
        this.handler = new Handler(Looper.getMainLooper());
        return this;
    }

    public GlobalConfig setAppkeys(Map<String, String> appkeys) {
        this.appkeys = appkeys;
        return this;
    }

    public GlobalConfig addAppkey(String key, String value) {
        this.appkeys = appkeys;
        if (this.appkeys == null) {
            this.appkeys = new HashMap<>();
        }
        this.appkeys.put(key, value);
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Map<String, Object> getBaseparams() {
        return baseparams;
    }

    public Map<String, Object> getBaseHeades() {
        return baseHeades;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public Context getContext() {
        return context;
    }

    public Handler getHandler() {
        return handler;
    }

    public Map<String, String> getAppkeys() {
        return appkeys;
    }

    public String getKey(String key) {
        if (key != null && !key.equals("") && appkeys != null) {
            if (appkeys.get(key)==null) {
                new RuntimeException("value not find for key");
            }else{
                return appkeys.get(key);
            }
        }
        return null;
    }
}
