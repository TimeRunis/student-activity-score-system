package com.sass.studentactivityscoresystem.service;

import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface ActivityService {
    //id查询活动信息
    ReturnBody getActivityInfoById(int id);
    //活动名查询学院信息
    ReturnBody getActivityByName(String name);
    //发布活动
    ReturnBody addActivity(Activity activity);
    //更新活动数据
    ReturnBody updateActivity(Activity activity);
    //删除活动
    ReturnBody removeActivityById(int id);
}
