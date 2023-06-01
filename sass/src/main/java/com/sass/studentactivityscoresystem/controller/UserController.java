package com.sass.studentactivityscoresystem.controller;

import com.alibaba.fastjson.JSON;
import com.sass.studentactivityscoresystem.controller.method.DeleteController;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.service.UserService;
import com.sass.studentactivityscoresystem.utils.CheckEntity;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;


@RestController
@Scope("prototype")
@CrossOrigin(origins = "*")
public class UserController extends BaseController implements GetController, PutController , DeleteController {
    private final UserService userService;
    private User user;
    private CheckEntity checkEntity;

    @Autowired
    UserController(UserService userService, User user, CheckEntity checkEntity){
        this.userService=userService;
        this.user=user;
        this.checkEntity = checkEntity;
    }

    @GetMapping("/user")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //(管理员)权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)&&map.containsKey("admin")){
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
                    rep.setResp(0,userService.userInfoByIdPage(Integer.parseInt(map.get("id")),map.get("current"),map.get("size")),"查询成功");
                }else if(map.containsKey("current")&&map.containsKey("size")){
                    rep.setResp(0,userService.findAll(map.get("current"),map.get("size")).getData(),"查询成功");
                }
            }else {
                //用户查询自己信息
                if(map.get("id")!=null){
                    int tId=Integer.parseInt(map.get("id"));
                    if(tId==JwtUtils.getUserId(request.getHeader("token"))){
                        rep.setResp(0,userService.userInfoById(tId),"查询成功");
                        return rep;
                    }
                }
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PutMapping("/user")
    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            int userId= JwtUtils.getUserId(request.getHeader("token"));
            //存在密码参数则为修改密码
            if(map.containsKey("userPsw")){
                userService.updateUserPsw(userId,map.get("userPsw"));
                rep.setResp(0,null,"修改密码成功");
            }else {
                try{
                    //不可为空参数
                    String[] param={"userName","userMail","realName","gender","birthday","collegeId"};
                    checkEntity.setParam(param);

                    //确认必须参数是否为空
                    if(!checkEntity.checkParam(map)){
                        rep.setResp(-1,null,"缺少参数");
                    }else {
                        //获取的参数转实体
                        user= JSON.parseObject(JSON.toJSONString(map),user.getClass());
                        //确认是本人或者是管理员
                        if(userId==user.getUserId()||JwtUtils.checkPermission(request.getHeader("token"),9)){
                            userService.updateBaseInfo(user);
                            rep.setResp(0,null,"更新成功");
                        }else {
                            rep.setResp(-1,null,"非法用户");
                        }
                    }
                }catch (Exception e){
                    rep.setResp(-1,null,"非法参数");
                }
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @DeleteMapping("/user")
    @Override
    public RespBody doDelete(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()) {
            //管理员权限检查
            if (JwtUtils.checkPermission(request.getHeader("token"), 9)) {
                try{
                    //获取参数
                    int userId= Integer.parseInt(map.get("userId"));
                    if(userService.isExist(userId)){
                        userService.removeById(userId);
                        rep.setResp(0,null,"删除成功");
                    }else {
                        rep.setResp(-1,null,"用户不存在");
                    }
                }catch (Exception e){
                    rep.setResp(-1,null,"获取参数失败");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
