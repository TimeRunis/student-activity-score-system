package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.UserMapper;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final ReturnBody returnBody;

    UserServiceImpl(ReturnBody returnBody){
        this.returnBody = returnBody;
    }

    @Override
    public ReturnBody findInfoByName(String name,int current,int size) {
        if(!name.isEmpty()){
            //分页参数
            Page<User> page = new Page<>(current, size);
            //queryWrapper组装查询where条件
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(User::getUserName,name);
            this.getBaseMapper().selectPage(page,queryWrapper);
            returnBody.setBody(0,page);
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public ReturnBody findInfoByRelName(String relName,int current,int size) {
        if(!relName.isEmpty()){
            //分页参数
            Page<User> page = new Page<>(current, size);
            //queryWrapper组装查询where条件
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(User::getRealName,relName);
            this.getBaseMapper().selectPage(page,queryWrapper);
            returnBody.setBody(0,page);
        }else {
            returnBody.setBody(-1,null);
        }
        return returnBody;
    }

    @Override
    public Object userInfoById(int id){
        return this.getBaseMapper().selectById(id);
    }

    @Override
    public Object userInfoByMail(String mail){
        if(!mail.isEmpty()){
            return this.getBaseMapper().selectByMail(mail);
        }else {
            return -1;
        }

    }

    @Override
    public boolean isExist(int id) {
        return this.getBaseMapper().getUserMailById(id) == null;

    }

    @Override
    public boolean isExist(String mail) {
        return this.getBaseMapper().getUserIdByMail(mail) == null;

    }
}
