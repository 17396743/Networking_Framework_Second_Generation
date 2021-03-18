package com.liangxq.mymvplibrary.ui.activity;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.ui.activity
 * 文件名：BaseActivity
 * 创建者：liangxq
 * 创建时间：2021/3/16  9:59
 * 描述：TODO
 */
public abstract class BaseActivity extends RxAppCompatActivity{

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayoutId());
        unbinder = ButterKnife.bind(this);
        initData();
    }

    //初始化数据
    protected abstract void initData();

    //设置布局文件
    protected abstract int createLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}
