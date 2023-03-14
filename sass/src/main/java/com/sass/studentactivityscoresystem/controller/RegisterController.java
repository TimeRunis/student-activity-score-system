package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.service.RegisterService;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController extends BaseController{
    @Autowired
    RegisterService registerService;

    @Autowired
    UserService userService;

    @Autowired
    User user;

    @GetMapping("/register")
    public Object doGet(){
        rep.setResp(0,null,"测试");
        return rep;
    }

    @PostMapping("/register")
    public Object doPost(@RequestBody Map<String,Object> map){
        if(!map.isEmpty()){
            if(userService.userInfoByMail(map.get("userMail").toString())==null){
                try{
                    user.setUserName(map.get("userName").toString());
                    user.setRelName(map.get("relName").toString());
                    user.setUserMail(map.get("userMail").toString());
                    user.setUserPsw(map.get("userPsw").toString());
                    user.setGender(Integer.parseInt(map.get("gender").toString()));
                    user.setCollegeId(Integer.parseInt(map.get("collegeId").toString()));
                }catch (Exception e){
                    rep.setResp(-1,null,"参数出错");
                }
                int flag=registerService.register(user);
                if(flag!=1){
                    rep.setResp(-1,null,"注册失败");
                }else {
                    rep.setResp(0,null,"注册成功");
                }
            }else {
                rep.setResp(-1,null,"邮箱已被注册");
            }
        }else {
            rep.setResp(-1,null,"参数错误");
        }
        return rep;
    }

}
