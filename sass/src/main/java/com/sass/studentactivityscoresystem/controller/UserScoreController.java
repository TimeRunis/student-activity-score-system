package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.service.UserScoreService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserScoreController extends BaseController{
    private final UserScoreService userScoreService;

    @Autowired
    UserScoreController(UserScoreService userScoreService) {
        this.userScoreService=userScoreService;
    }

    @PutMapping("/activityScore")
    public Object doPut(@RequestBody Map<String,Object> map, HttpServletRequest request){
        //是否为空数据
        if(!map.isEmpty()){
            try{
                //获取更新模式
                int mode=Integer.parseInt(map.get("mode").toString());
                //获取需要更新的数据
                int source=Integer.parseInt(map.get("source").toString());
                //获取用户id
                int userId=Integer.parseInt(map.get("userId").toString());
                //获取需要修改的数值
                int number=Integer.parseInt(map.get("number").toString());
                //权限检查
                if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                    //根据需要修改的数据源分类
                    switch (source){
                        //activityScore为数据源
                        case 0:
                            //根据修改模式分类
                            switch (mode){
                                //增加模式
                                case 0:
                                    int flag=userScoreService.addUserScore(userId,number);
                                    if(flag==0){
                                        rep.setResp(0,null,"增加成功");
                                    }else {
                                        rep.setResp(flag,null,"非法参数");
                                    }
                                    break;
                                //减少模式
                                case 1:
                                    flag=userScoreService.minusUserScore(userId,number);
                                    if(flag==0){
                                        rep.setResp(0,null,"减少成功");
                                    }else {
                                        rep.setResp(flag,null,"非法参数");
                                    }
                                    break;
                                //设置模式
                                case 2:
                                    flag=userScoreService.setUserScore(userId,number);
                                    if(flag==0){
                                        rep.setResp(0,null,"设置成功");
                                    }else {
                                        rep.setResp(flag,null,"非法参数");
                                    }
                                    break;
                                default:
                                    rep.setResp(-1,null,"非法参数");
                            }
                            break;
                        //totalActivityScore为数据源
                        case 1:
                            switch (mode){
                                //增加模式
                                case 0:
                                    int flag=userScoreService.changeUserTotalActivityScore(userId,number);
                                    if(flag==0){
                                        rep.setResp(0,null,"增加成功");
                                    }else {
                                        rep.setResp(flag,null,"非法参数");
                                    }
                                    break;
                                //减少模式
                                case 1:
                                    flag=userScoreService.changeUserTotalActivityScore(userId,-number);
                                    if(flag==0){
                                        rep.setResp(0,null,"减少成功");
                                    }else {
                                        rep.setResp(flag,null,"非法参数");
                                    }
                                    break;
                                //设置模式
                                case 2:
                                    flag=userScoreService.setUserTotalActivityScore(userId,number);
                                    if(flag==0){
                                        rep.setResp(0,null,"设置成功");
                                    }else {
                                        rep.setResp(flag,null,"非法参数");
                                    }
                                    break;
                                default:
                                    rep.setResp(-1,null,"非法参数");
                            }
                            break;
                        default:
                            rep.setResp(-1,null,"未知数据源");
                    }
                }else {
                    rep.setResp(-1,null,"权限不足");
                }
            }catch (Exception e){
                rep.setResp(-1,null,"非法参数");
            }
        }
        return rep;
    }
}
