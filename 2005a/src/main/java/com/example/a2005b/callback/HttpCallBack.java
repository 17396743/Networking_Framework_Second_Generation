package com.example.a2005b.callback;

import com.example.myhttptwolibrary.BaseResponse;
import com.example.myhttptwolibrary.callback.BaseCallBack;
import com.google.gson.Gson;

/**
 * 项目名：Shopping
 * 包名：  com.example.a2005b.callback
 * 文件名：HttpCallBack
 * 创建者：liangxq
 * 创建时间：2021/3/10  16:19
 * 描述：TODO
 * @author Administrator
 */
public abstract class HttpCallBack<T>extends BaseCallBack<T> {
    BaseResponse baseResponse;
    @Override
    protected T onConvert(String result) {
        T t=null;
        baseResponse=new Gson().fromJson(result,BaseResponse.class);
        if(baseResponse!=null&&isSucessfull()){
            t=convert(baseResponse.getData());
        }else{
            error(baseResponse.getErrorMsg(),baseResponse.getErrorCode());
        }
        return t;
    }

    @Override
    public boolean isSucessfull() {
        return baseResponse.getErrorCode()==0;
    }
}
