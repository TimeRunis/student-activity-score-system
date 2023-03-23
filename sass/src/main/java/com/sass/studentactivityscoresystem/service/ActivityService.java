package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface ActivityService extends IService<Activity> {
    //模糊查询名字
    public ReturnBody findByName(String name,int current,int size);
    //模糊查询内容
    public ReturnBody findByContent(String name,int current,int size);
    //id查询活动信息
    ReturnBody getActivityInfoById(int id);
    //发布活动
    ReturnBody addActivity(Activity activity);
    //更新活动数据
    ReturnBody updateActivity(Activity activity);
    //删除活动
    ReturnBody removeActivityById(int id);
}
