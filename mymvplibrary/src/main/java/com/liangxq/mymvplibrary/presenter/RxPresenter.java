package com.liangxq.mymvplibrary.presenter;

import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.presenter
 * 文件名：RxPresenter
 * 创建者：liangxq
 * 创建时间：2021/3/16  8:57
 * 描述：TODO
 */
public interface RxPresenter<V> extends Presenter<V>{
    LifecycleProvider getLifecycleProvider();
}
