package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class BaseController {
    @Autowired
    public RespBody rep;
    @Autowired
    public ReturnBody returnBody;

    public int getSwitchWay(String[] item, Map<Object, String> map){
        List<String> list = new ArrayList<>(Arrays.asList(item));
        for (String i:list) {
            try{
                if(map.containsKey(i)){
                    return list.indexOf(i);
                }
            }catch (Exception ignored){}

        }
        return -1;
    }

}
