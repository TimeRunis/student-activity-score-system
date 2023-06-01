package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.mapper.ActivitySubmitMapper;
import com.sass.studentactivityscoresystem.service.ActivitySubmitService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ActivitySubmitServiceImpl extends ServiceImpl<ActivitySubmitMapper, ActivitySubmit> implements ActivitySubmitService {

    @Override
    public Page<ActivitySubmit> getByAcidPage(String acId,String current,String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getOneByAcId(page, Integer.parseInt(acId));
    }

    @Override
    public Page<ActivitySubmit> getByUserIdPage(String userId,String current,String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getOneByUserId(page, Integer.parseInt(userId));
    }


    @Override
    public Page<ActivitySubmit> findAll(String current, String size) {
        //分页参数
        Page<ActivitySubmit> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getAll(page);
    }

    @Override
    public Map<String, Object> countActivitySubmit(String acId) {
        QueryWrapper<ActivitySubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("COUNT(as_id) as submitNumber");
        queryWrapper.eq("activity_id",acId);
        return this.getMap(queryWrapper);
    }
}
