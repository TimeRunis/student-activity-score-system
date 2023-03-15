package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.mapper.UserMapper;
import com.sass.studentactivityscoresystem.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserScoreServiceImpl implements UserScoreService {
    private final UserMapper userMapper;

    @Autowired
    UserScoreServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public int addUserScore(int id, int number) {
        if(id>0&&number>=0){
            userMapper.updateScore(id,number);
            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public int minusUserScore(int id, int number) {
        if(id>0&&number>=0){
            userMapper.updateScore(id,-number);
            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public int setUserScore(int id, int number) {
        if(id>0){


            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public int setUserTotalActivityScore(int id, int number) {
        return 0;
    }
}
