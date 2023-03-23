package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.service.UserService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


@RestController
@CrossOrigin(origins = "*")
public class UserController extends BaseController implements GetController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/user")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //用户查询自己信息
            if(map.get("id")!=null){
                int tId=Integer.parseInt(map.get("id"));
                if(tId==JwtUtils.getUserId(request.getHeader("token"))){
                    rep.setResp(0,userService.userInfoById(tId),"查询成功");
                    return rep;
                }
            }
            //(管理员)权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                //判断查询字段
                if(map.get("name")!=null){
                    returnBody=userService.findInfoByName(map.get("name"),Integer.parseInt(map.get("current")),Integer.parseInt(map.get("size")));
                    if(returnBody.getFlag()==0){
                        rep.setResp(0,returnBody.getData(),"查询成功");
                    }else {
                        rep.setResp(returnBody.getFlag(),null,"出现错误");
                    }
                }else if (map.get("relName")!=null){
                    returnBody=userService.findInfoByRelName(map.get("relName"),Integer.parseInt(map.get("current")),Integer.parseInt(map.get("size")));
                    if(returnBody.getFlag()==0){
                        rep.setResp(0,returnBody.getData(),"查询成功");
                    }else {
                        rep.setResp(returnBody.getFlag(),null,"出现错误");
                    }
                }
                else if (map.get("id")!=null){
                    rep.setResp(0,userService.userInfoById(Integer.parseInt(map.get("id"))),"查询成功");
                }
            }else {
                rep.setResp(1,null,"权限不足");
            }


        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
