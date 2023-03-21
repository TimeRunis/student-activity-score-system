package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import com.sass.studentactivityscoresystem.mapper.ActivityMapper;
import com.sass.studentactivityscoresystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends BaseService implements ActivityService {
    private final ActivityMapper activityMapper;

    @Autowired
    ActivityServiceImpl(ActivityMapper activityMapper){
        this.activityMapper=activityMapper;
    }

    @Override
    public ReturnBody getActivityInfoById(int id) {
        if(id>0){
            returnBody.setBody(0,activityMapper.selectById(id));
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody getActivityByName(String name) {
        if(!name.isEmpty()){
            returnBody.setBody(0,activityMapper.getInfoByName(name));
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody addActivity(Activity activity) {
        if(activity!=null){
            //插入
            if(activityMapper.insert(activity)==1){
                returnBody.setBody(0,null);
            }else {
                returnBody.setBody(-2,null);
            }
        }else {
            //数据空
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody updateActivity(Activity activity) {
        if(activity!=null){
            //更新
            if(activityMapper.updateById(activity)==1){
                returnBody.setBody(0,null);
            }else {
                returnBody.setBody(-2,null);
            }
        }else {
            //数据空
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody removeActivityById(int id) {
        if(id>0){
            returnBody.setBody(activityMapper.deleteById(id),null);
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }
}
