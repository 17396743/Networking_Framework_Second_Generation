package com.liangxq.mymvplibrary.view;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.view
 * 文件名：RxBaseView
 * 创建者：liangxq
 * 创建时间：2021/3/16  9:57
 * 描述：TODO
 */
public interface RxBaseView extends BaseView{
    void showProgress();
    void hideProgress();
    void onError(String message,int code);
}
