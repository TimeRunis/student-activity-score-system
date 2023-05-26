package com.sass.studentactivityscoresystem.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransportInfo {
    String info;
    Date updateTime;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TransportInfo{" +
                "info='" + info + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
