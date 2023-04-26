package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.mapper.ActivitySubmitMapper;
import com.sass.studentactivityscoresystem.service.ActivitySubmitService;
import org.springframework.stereotype.Service;

@Service
public class ActivitySubmitServiceImpl extends ServiceImpl<ActivitySubmitMapper, ActivitySubmit> implements ActivitySubmitService {

    @Override
    public Page<ActivitySubmit> getByAcidPage(String acId,String current,String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getOneByUserId(page, Integer.parseInt(acId));
    }

    @Override
    public Page<ActivitySubmit> getByUserIdPage(String userId,String current,String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getOneByUserId(page, Integer.parseInt(userId));
    }


    @Override
    public Page<ActivitySubmit> getByUserNamePage(String userName, String current, String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<ActivitySubmit> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ActivitySubmit::getUserName,userName);
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }

    @Override
    public Page<ActivitySubmit> findAll(String current, String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getAll(page);
    }
}
