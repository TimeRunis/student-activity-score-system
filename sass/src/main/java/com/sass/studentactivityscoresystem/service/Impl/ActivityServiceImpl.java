package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.ActivityMapper;
import com.sass.studentactivityscoresystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper,Activity> implements ActivityService {
    private final ReturnBody returnBody;

    @Autowired
    ActivityServiceImpl(ReturnBody returnBody){
        this.returnBody = returnBody;
    }

    @Override
    public ReturnBody getActivityInfoById(int id) {
        if(id>0){
            returnBody.setBody(0,this.getBaseMapper().selectById(id));
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody find(String key,String current,String size) {
        if(!key.isEmpty()){
            //分页参数
            Page<Activity> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
            //queryWrapper组装查询where条件
            LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Activity::getActivityName,key).or().like(Activity::getActivityContent,key);
            this.getBaseMapper().selectPage(page,queryWrapper);
            returnBody.setBody(0,page);
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody findAll(String current,String size) {
        //分页参数
        Page<Activity> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Activity.class,fieldInfo->!fieldInfo.getColumn().equals("activity_content"));
        this.getBaseMapper().selectPage(page,queryWrapper);
        returnBody.setBody(0,page);
        return returnBody;
    }

    @Override
    public ReturnBody addActivity(Activity activity) {
        if(activity!=null){
            //插入
            if(this.getBaseMapper().insert(activity)==1){
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
            //插入
            if(this.getBaseMapper().updateById(activity)==1){
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
            returnBody.setBody(this.getBaseMapper().deleteById(id),null);
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

}
