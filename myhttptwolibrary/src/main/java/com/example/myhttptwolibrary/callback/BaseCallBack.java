package com.example.myhttptwolibrary.callback;

import com.example.myhttptwolibrary.BaseResponse;
import com.example.myhttptwolibrary.exception.exceptiopn.ExceptionEngine;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.myhttptwolibrary.callback
 * 文件名：BaseCallBack
 * 创建者：liangxq
 * 创建时间：2021/3/10  15:53
 * 描述：TODO
 */
public abstract class BaseCallBack<T> extends BaseObserver {
    boolean isConvert=true;
    @Override
    public void onNext(Object o) {
        T bean = parse(o.toString());
        if(isConvert&&isSucessfull()){
            onSucess(bean);
        }
    }
    //解析数据
    T parse(String json){
        T t=null;
        try {
            t = onConvert(json);
            isConvert=true;
        }catch (Exception e){
            isConvert=false;
            error("解析异常", ExceptionEngine.ANALYTIC_SERVER_DATA_ERROR);
        }
        return t;
    }

    //子类扩展解析
    protected abstract T onConvert(String result);
    //回调我们想要的数据
    protected abstract void onSucess(T t);
    //解析我们具体的类
    public abstract T convert(JsonElement jsonElement);

    public abstract boolean isSucessfull();

}
