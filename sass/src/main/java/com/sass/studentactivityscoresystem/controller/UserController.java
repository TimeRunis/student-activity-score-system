package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.service.UserService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@RestController
@CrossOrigin(origins = "*")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/user")
    public Object getById(int id, HttpServletRequest request){
        if(id>0){
            int userId=JwtUtils.getUserId(request.getHeader("token"));
            if(Objects.equals(userId, id) || JwtUtils.checkPermission(request.getHeader("token"),9)){
                if(userService.isExist(id)){
                    rep.setResp(0,userService.userInfoById(id),"查询成功");
                }else {
                    rep.setResp(-1,null,"未查询到数据");
                }
            }else {
                rep.setResp(-1,null,"你的权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
