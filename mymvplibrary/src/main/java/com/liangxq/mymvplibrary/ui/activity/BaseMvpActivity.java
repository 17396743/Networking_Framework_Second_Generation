package com.liangxq.mymvplibrary.ui.activity;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.*;

import com.liangxq.mymvplibrary.model.BaseModel;
import com.liangxq.mymvplibrary.presenter.RxBasePresenter;
import com.liangxq.mymvplibrary.view.BaseView;
import com.liangxq.mymvplibrary.view.RxBaseView;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.ui.activity
 * 文件名：BaseMvpActivity
 * 创建者：liangxq
 * 创建时间：2021/3/16  10:12
 * 描述：TODO
 */
public abstract class BaseMvpActivity<V extends BaseView,M extends BaseModel,P extends RxBasePresenter<V,M>> extends BaseActivity implements RxBaseView{
    protected P mPresenter;
    @Override
    protected void initData() {
        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.bindView((V) this);
        }
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract P createPresenter();

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.destoryView();
            mPresenter=null;
        }
    }
}
