package com.sass.studentactivityscoresystem.service;


public interface UserService {
    //id查询用户信息
    Object userInfoById(int id);
    //邮箱查询用户信息
    Object userInfoByMail(String mail);
    //id查询用户信息是否存在
    boolean isExist(int id);
    //邮箱查询用户信息是否存在
    boolean isExist(String mail);
}
