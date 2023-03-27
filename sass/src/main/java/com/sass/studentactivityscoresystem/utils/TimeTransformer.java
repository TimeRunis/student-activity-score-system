package com.sass.studentactivityscoresystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTransformer {
    public static Long time2Long(String time){
//        System.out.println("时间:"+time);
        SimpleDateFormat t=new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        try{
//            System.out.println("转换:"+t.parse(time).getTime());
            return t.parse(time).getTime();
        }catch (Exception e){
            return -1L;
        }
    }
    public static String date2Format(Date time){
//        System.out.println("时间:"+time);
        SimpleDateFormat t=new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        try{
//            System.out.println("转换:"+t.parse(time).getTime());
            return t.format(time);
        }catch (Exception e){
            return null;
        }
    }
}
