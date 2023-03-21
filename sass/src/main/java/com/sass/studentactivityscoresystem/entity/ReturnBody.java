package com.sass.studentactivityscoresystem.entity;


import org.springframework.stereotype.Component;

@Component
public class ReturnBody {
    int flag;
    Object data;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setBody(int flag,Object data){
        this.flag=flag;
        this.data=data;
    }
}
