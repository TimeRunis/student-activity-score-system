package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.DeleteController;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PostController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.service.ActivityService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ActivityController extends BaseController implements GetController, PostController , PutController, DeleteController {
    private ActivityService activityService;
    private Activity activity;

    @Autowired
    ActivityController(ActivityService activityService,Activity activity){
        this.activityService=activityService;
        this.activity=activity;
    }

    @GetMapping("/activity")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    //判断id查询还是name查询
                    if (map.get("id")!=null) {
                        //id精确查询
                        returnBody = activityService.getActivityInfoById(Integer.parseInt(map.get("id")));
                        if (returnBody.getFlag() == 0) {
                            rep.setResp(0, returnBody.getData(), "查询成功");
                        } else {
                            rep.setResp(returnBody.getFlag(), null, "出现错误");
                        }

                    } else if (map.get("name")!=null) {
                        //名字模糊查询
                        returnBody = activityService.findByName(map.get("name"),Integer.parseInt(map.get("current")),Integer.parseInt(map.get("size")));
                        if (returnBody.getFlag() == 0) {
                            rep.setResp(0, returnBody.getData(), "查询成功");
                        } else {
                            rep.setResp(returnBody.getFlag(), null, "出现错误");
                        }
                    }
                }catch (Exception e){
                    rep.setResp(-1,null,"参数错误");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PostMapping("/activity")
    @Override
    public RespBody doPost(Map<Object, String> map,HttpServletRequest request) {
        //空参数检测
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    activity.setActivityName(map.get("name"));
                    activity.setActivityContent(map.get("content"));
                    activity.setStartTime(map.get("startTime"));
                    activity.setDeadLine(map.get("deadLine"));
                    activity.setUserId(JwtUtils.getUserId(request.getHeader("token")));
                }catch (Exception e){
                    rep.setResp(-1,null,"非法参数");
                }
                returnBody= activityService.addActivity(activity);
                if (returnBody.getFlag()==0){
                    rep.setResp(0,returnBody.getData(),"发布成功");
                }else {
                    rep.setResp(returnBody.getFlag(),null,"发布失败");
                }

            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PutMapping("/activity")
    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        //空参数检测
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    activity= (Activity) activityService.getActivityInfoById(Integer.parseInt(map.get("activityId"))).getData();
                    activity.setActivityName(map.get("name"));
                    activity.setActivityContent(map.get("content"));
                    activity.setStartTime(map.get("startTime"));
                    activity.setDeadLine(map.get("deadLine"));
                    activity.setUserId(JwtUtils.getUserId(request.getHeader("token")));
                }catch (Exception e){
                    rep.setResp(-1,null,"非法参数");
                }
                returnBody= activityService.updateActivity(activity);
                if (returnBody.getFlag()==0){
                    rep.setResp(0,returnBody.getData(),"修改成功");
                }else {
                    rep.setResp(returnBody.getFlag(),null,"修改失败");
                }

            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @DeleteMapping("/activity")
    @Override
    public RespBody doDelete(Map<Object, String> map, HttpServletRequest request) {
        if(!map.isEmpty()){
            //获取参数
            int acId=Integer.parseInt(map.get("id"));
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    if(activityService.getActivityInfoById(acId).getFlag()==0){
                        activityService.removeActivityById(acId);
                        rep.setResp(0,null,"删除成功");
                    }else {
                        rep.setResp(-1,null,"不存在的活动");
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
}
