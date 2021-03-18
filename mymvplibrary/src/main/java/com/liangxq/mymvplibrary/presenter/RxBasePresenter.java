package com.liangxq.mymvplibrary.presenter;

import com.liangxq.mymvplibrary.model.BaseModel;
import com.liangxq.mymvplibrary.view.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 项目名：Shopping
 * 包名：  com.liangxq.mymvplibrary.presenter
 * 文件名：RxBasePresenter
 * 创建者：liangxq
 * 创建时间：2021/3/16  9:02
 * 描述：TODO
 */
public class RxBasePresenter<V extends BaseView,M extends BaseModel> implements RxPresenter<V>{
    //弱引用
    WeakReference<V> weakReference;
    public V mView;
    private M model;

    @Override
    public void bindView(V v) {
        weakReference=new WeakReference<V>(v);
        iniModel();
    }

    private void iniModel() {
        ParameterizedType parameterizedType= (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<M> classModel = (Class<M>) actualTypeArguments[1];
        try {
            model=classModel.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public V getmView() {
        if(weakReference!=null){
            mView=weakReference.get();
        }
        return mView;
    }

    @Override
    public void destoryView() {
        if(weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
        if(mView!=null){
            mView=null;
        }
    }

    @Override
    public LifecycleProvider getLifecycleProvider() {
        return (LifecycleProvider) getmView();
    }

    public M getModel() {
        return model;
    }
}
