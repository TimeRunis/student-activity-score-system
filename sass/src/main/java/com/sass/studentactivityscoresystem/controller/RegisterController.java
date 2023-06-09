package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.service.RegisterService;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RegisterController extends BaseController{
    private final RegisterService registerService;
    private final UserService userService;
    private User user;

    @Autowired
    RegisterController(RegisterService registerService,UserService userService,User user){
        this.registerService=registerService;
        this.userService=userService;
        this.user=user;
    }

    @PostMapping("/register")
    public Object doPost(@RequestBody Map<String,Object> map){
        if(!map.isEmpty()){
            if(!userService.isExist(map.get("userMail").toString())){
                try{
                    user.setUserName(map.get("userName").toString());
                    user.setRealName(map.get("realName").toString());
                    user.setUserMail(map.get("userMail").toString());
                    user.setUserPsw(map.get("userPsw").toString());
                    user.setGender(Integer.parseInt(map.get("gender").toString()));
                    user.setCollegeId(Integer.parseInt(map.get("collegeId").toString()));
                }catch (Exception e){
                    rep.setResp(-1,null,"参数出错");
                }
                try{
                    int flag=registerService.register(user);
                    if(flag!=0){
                        rep.setResp(flag,null,"注册失败");
                    }else {
                        rep.setResp(0,null,"注册成功");
                    }
                } catch (Exception e){
                    rep.setResp(-1,null,"参数错误请检查");
                }

            }else {
                rep.setResp(-1,null,"邮箱已被注册");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

}
