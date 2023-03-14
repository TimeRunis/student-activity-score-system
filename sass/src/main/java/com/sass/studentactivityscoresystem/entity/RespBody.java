package com.sass.studentactivityscoresystem.entity;

import com.alibaba.fastjson.JSON;

public class RespBody {
    private int code;
    private Object data;
    private String message;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setResp(int code,Object data,String msg){
        this.setCode(code);
        this.setData(data);
        this.setMessage(msg);
    }

    public String Oj2Json() {
        return JSON.toJSONString(this);
    }
}
