package com.sass.studentactivityscoresystem.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class CheckEntity {
    String[] param;
    List<String> paraList;

    public void setParam(String[] param) {
        this.param = param;
    }

    public boolean checkParam(Map<Object, String> map){
        for(String i:param){
            if(Objects.equals(map.get(i), "")){
                return false;
            }
        }
        return true;
    }
}
