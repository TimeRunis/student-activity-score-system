package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.service.LoginService;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class LoginController extends BaseController{
    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    LoginController(LoginService loginService, UserService userService){
        this.loginService=loginService;
        this.userService = userService;
    }

    @PostMapping("/login")

    public Object login(@RequestBody Map<String,Object> map){
        if(!map.isEmpty()){
            try{
                String flag=loginService.login(map.get("userMail").toString(),map.get("userPsw").toString());
                if(Objects.equals(flag, "-1")){
                    rep.setResp(-1,null,"用户名或密码错误");
                }
                if(Objects.equals(flag, "-2")){
                    rep.setResp(-2,null,"未找到用户");
                }
                if(flag.length()>=30){
                    map.remove("userPsw");
                    //登录成功并返回用户信息
                    map.put("token",flag);
                    map.put("info",userService.userInfoByMail(map.get("userMail").toString()));
                    rep.setResp(0,map,"登录成功");
                }
            } catch (Exception e){
                rep.setResp(-1,null,"参数错误");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
