package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import com.sass.studentactivityscoresystem.mapper.ActivitySubmitMapper;
import com.sass.studentactivityscoresystem.service.ActivitySubmitService;
import org.springframework.stereotype.Service;

@Service
public class ActivitySubmitServiceImpl extends ServiceImpl<ActivitySubmitMapper, ActivitySubmit> implements ActivitySubmitService {

    @Override
    public Page findByAcidPage(int acId,int current,int size) {
        ReturnBody returnBody =new ReturnBody();
        //分页参数
        Page<ActivitySubmit> page = new Page<>(current, size);
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<ActivitySubmit> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ActivitySubmit::getActivityId,acId);
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }
}
