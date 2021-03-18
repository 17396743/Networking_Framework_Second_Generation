package com.example.myhttptwolibrary.callback;

import com.example.myhttptwolibrary.exception.exceptiopn.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名：Shopping
 * 包名：  com.example.myhttptwolibrary.callback
 * 文件名：BaseObserver
 * 创建者：liangxq
 * 创建时间：2021/3/10  10:39
 * 描述：TODO
 */
public abstract class BaseObserver implements Observer {
    @Override
    public void onSubscribe(Disposable d) {

    }


    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            error(apiException.getMsg(), apiException.getCode());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void error(String error, int code);
}
