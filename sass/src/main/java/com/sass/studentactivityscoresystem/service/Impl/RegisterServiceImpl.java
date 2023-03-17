package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.UserMapper;
import com.sass.studentactivityscoresystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserMapper userMapper;

    @Autowired
    RegisterServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public int register(User user) {
        if(user!=null){
            if(userMapper.insert(user)!=1){
                return -1;
            }else {
                return 0;
            }
        }else {
            return -2;
        }
    }
}
