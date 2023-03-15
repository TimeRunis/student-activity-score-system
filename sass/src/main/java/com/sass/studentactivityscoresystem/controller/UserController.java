package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.service.UserService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@RestController
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/user")
    public Object getById(int id, HttpServletRequest request){
        if(id>0){
            int level=JwtUtils.getLevel(request.getHeader("token"));
            int userId=JwtUtils.getUserId(request.getHeader("token"));
            if(Objects.equals(userId, id) || level==10){
                if(userService.isExist(id)){
                    rep.setResp(0,userService.userInfoById(id),"查询成功");
                }else {
                    rep.setResp(404,null,"未查询到数据");
                }
            }else {
                rep.setResp(401,null,"你的权限不足");
            }

        }else {
            rep.setResp(400,null,"参数错误");
        }
        return rep;
    }
}
