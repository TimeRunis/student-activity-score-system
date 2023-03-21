package com.sass.studentactivityscoresystem.service;


import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface UserService {
    //用户名模糊查询
    ReturnBody findInfoByName(String name);
    //真实姓名模糊查询
    ReturnBody findInfoByRelName(String name);
    //id查询用户信息
    Object userInfoById(int id);
    //邮箱查询用户信息
    Object userInfoByMail(String mail);
    //id查询用户信息是否存在
    boolean isExist(int id);
    //邮箱查询用户信息是否存在
    boolean isExist(String mail);
}
