package com.example.a2005b.bean;

import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.a2005b.bean
 * 文件名：BaseResponse
 * 创建者：liangxq
 * 创建时间：2021/3/10  16:18
 * 描述：TODO
 */
public class BaseResponse {
    private int errorCode;
    private String errorMsg;
    private JsonElement data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
