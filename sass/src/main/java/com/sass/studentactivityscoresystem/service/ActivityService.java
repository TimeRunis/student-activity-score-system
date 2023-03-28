package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface ActivityService extends IService<Activity> {
    //模糊查询名字
    ReturnBody find(String key,String current,String size);
    //查询所有内容
    ReturnBody findAll(String current,String size);
    //id查询活动信息
    ReturnBody getActivityInfoById(int id);
    //发布活动
    ReturnBody addActivity(Activity activity);
    //更新活动数据
    ReturnBody updateActivity(Activity activity);
    //删除活动
    ReturnBody removeActivityById(int id);
}
