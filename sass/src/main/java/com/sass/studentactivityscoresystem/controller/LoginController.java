package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.service.LoginService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class LoginController extends BaseController{
    @Autowired
    LoginService loginService;

    @PostMapping("/login")

    public Object login(@RequestBody Map<String,Object> map){
        String flag=loginService.login(map.get("userMail").toString(),map.get("userPsw").toString());
        if(Objects.equals(flag, "-1")){
            rep.setResp(-1,null,"用户名或密码错误");
        }
        if(Objects.equals(flag, "-2")){
            rep.setResp(-2,null,"未找到用户");
        }
        if(flag.length()>=30){
            rep.setResp(0,flag,"登录成功");
        }
        return rep;
    }
}
