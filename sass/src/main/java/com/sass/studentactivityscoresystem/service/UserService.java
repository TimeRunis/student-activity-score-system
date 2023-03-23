package com.sass.studentactivityscoresystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import com.sass.studentactivityscoresystem.entity.User;

public interface UserService extends IService<User> {
    //用户名模糊查询
    ReturnBody findInfoByName(String name,int current,int size);
    //真实姓名模糊查询
    ReturnBody findInfoByRelName(String name,int current,int size);
    //id查询用户信息
    Object userInfoById(int id);
    //邮箱查询用户信息
    Object userInfoByMail(String mail);
    //id查询用户信息是否存在
    boolean isExist(int id);
    //邮箱查询用户信息是否存在
    boolean isExist(String mail);
}
