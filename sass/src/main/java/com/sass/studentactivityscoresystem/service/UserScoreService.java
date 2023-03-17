package com.sass.studentactivityscoresystem.service;

public interface UserScoreService {
    //增加用户积分
    int addUserScore(int id,int number);
    //减少用户积分
    int minusUserScore(int id,int number);
    //设置用户积分
    int setUserScore(int id,int number);
    //增减用户历史总积分
    int changeUserTotalActivityScore(int id,int number);
    //设置用户历史总积分
    int setUserTotalActivityScore(int id,int number);
}
