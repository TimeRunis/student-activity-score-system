package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.UserMapper;
import com.sass.studentactivityscoresystem.service.LoginService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoginServiceImpl implements LoginService {
    private final UserMapper userMapper;

    @Autowired
    LoginServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public String login(String mail, String password){
        User user=userMapper.selectByMail(mail);
        if(user!=null){
            if(user.getUserPsw().equals(password)){
                //登录成功返回token
                return JwtUtils.getToken(String.valueOf(user.getUserId()),user.getAuthorityLevel(),user.getUserName());
            }else {
                //密码错误
                return "-1";
            }
        }else {
            //未找到用户
            return "-2";
        }
    }
}
