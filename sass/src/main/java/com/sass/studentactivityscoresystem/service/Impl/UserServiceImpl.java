package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.mapper.UserMapper;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public Object userInfoById(int id){
        return userMapper.selectById(id);
    }

    @Override
    public Object userInfoByMail(String mail){
        return userMapper.selectByMail(mail);
    }

    @Override
    public boolean isExist(int id) {
        if(id>0){
            return userMapper.getUserMailById(id) != null;
        }else {
            return false;
        }
    }

    @Override
    public boolean isExist(String mail) {
        if(!mail.isEmpty()){
            return userMapper.getUserIdByMail(mail) != null;
        }else {
            return false;
        }
    }
}
