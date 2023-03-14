package com.sass.studentactivityscoresystem.service;


public interface UserService {
    //id查询用户信息
    Object userInfoById(String id);
    //邮箱查询用户信息
    Object userInfoByMail(String mail);
}
