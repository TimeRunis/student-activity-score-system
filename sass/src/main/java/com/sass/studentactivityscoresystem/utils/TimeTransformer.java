package com.sass.studentactivityscoresystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTransformer {
    public static Date string2Date(String time){
        SimpleDateFormat t=new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        try{
            return t.parse(time);
        }catch (Exception e){
            return null;
        }
    }
    public static Long time2Long(String time){
        SimpleDateFormat t=new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        try{
            return t.parse(time).getTime();
        }catch (Exception e){
            return -1L;
        }
    }
    public static String date2Format(Date time){
        SimpleDateFormat t=new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        try{
            return t.format(time);
        }catch (Exception e){
            return null;
        }
    }
}
