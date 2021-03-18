package com.liangxq.mymvplibrary.callback;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.callback
 * 文件名：BaseModelCallback
 * 创建者：liangxq
 * 创建时间：2021/3/17  9:45
 * 描述：TODO
 */
public interface BaseModelCallback<T> {
    void onSucess(T t);
    void onError(String message,int code);
}
