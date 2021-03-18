package com.liangxq.mymvplibrary.presenter;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.presenter
 * 文件名：Presenter
 * 创建者：liangxq
 * 创建时间：2021/3/16  8:40
 * 描述：TODO
 */
public interface Presenter<V> {
    //绑定View
    void bindView(V v);
    //解绑View
    void destoryView();
}
