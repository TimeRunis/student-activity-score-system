package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PostController;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.service.ActivityService;
import com.sass.studentactivityscoresystem.service.ActivitySubmitService;
import com.sass.studentactivityscoresystem.service.UserService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import com.sass.studentactivityscoresystem.utils.TimeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
@Scope("prototype")
@CrossOrigin(origins = "*")
public class ActivitySubmitController extends BaseController implements GetController, PostController {
    private final ActivitySubmitService activitySubmitService;
    private final UserService userService;
    private ActivityService activityService;
    private ActivitySubmit activitySubmit;

    @Autowired
    public ActivitySubmitController(ActivitySubmitService activitySubmitService, ActivityService activityService, UserService userService, ActivitySubmit activitySubmit) {
        this.activitySubmitService = activitySubmitService;
        this.activityService = activityService;
        this.userService = userService;
        this.activitySubmit = activitySubmit;
    }

    @GetMapping("/activitySubmit")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //可选参数
            String[] params={"acId", "userName","userId"};
            try{
                int mode=this.getSwitchWay(params,map);
                switch (mode){
                    //acid查询
                    case 0:
                        if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                            rep.setResp(0,activitySubmitService.getByAcidPage(map.get(params[mode]),map.get("current"),map.get("size")),"查询成功");
                        }else {
                            rep.setResp(-1,null,"权限不足");
                        }
                        break;
                    //username查询
                    case 1:
                        if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                            rep.setResp(0,activitySubmitService.getByUserNamePage(map.get(params[mode]),map.get("current"),map.get("size")),"查询成功");
                        }else {
                            rep.setResp(-1,null,"权限不足");
                        }
                        break;
                    //userid查询
                    case 2:
                        if(JwtUtils.checkPermission(request.getHeader("token"),9)|| Objects.equals(map.get(params[mode]), String.valueOf(JwtUtils.getUserId(request.getHeader("token"))))){
                            rep.setResp(0,activitySubmitService.getByUserIdPage(map.get(params[mode]),map.get("current"),map.get("size")),"查询成功");
                        }else {
                            rep.setResp(-1,null,"权限不足");
                        }
                        break;
                    default:
                        rep.setResp(0,activitySubmitService.findAll(map.get("current"),map.get("size")),"查询成功");
                        break;
                }
            }catch (Exception e){
                rep.setResp(-1,null,"非法参数");
            }
        }else {
            rep.setResp(-1,null,"参数为空");
        }
        return rep;
    }

    @PostMapping("/activitySubmit")
    @Override
    public RespBody doPost(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            try{
                //获取参数
                int userId=JwtUtils.getUserId(request.getHeader("token"));
                int acId= Integer.parseInt(map.get("acId"));
                //获取活动
                Activity activity= activityService.getById(acId);
                //查询用户是否存在
                if (activity!=null&&userService.isExist(userId)){
                    //查询是否已报名
                    if(activitySubmitService.query().eq("user_id",userId).eq("activity_id",acId).one()!=null){
                        rep.setResp(-1,null,"已经报名过了");
                    }else {
                        //校验活动是否过期
                        Date date = new Date();
                        Long s = TimeTransformer.time2Long(activity.getStartTime());
                        Long e = TimeTransformer.time2Long(activity.getDeadLine());
                        if (date.getTime() >= s && date.getTime() <= e) {
                            activitySubmit.setActivityId(activity.getActivityId());
                            activitySubmit.setUserName(JwtUtils.getUserName(request.getHeader("token")));
                            activitySubmit.setUserId(JwtUtils.getUserId(request.getHeader("token")));
                            activitySubmit.setSubmitTime(TimeTransformer.date2Format(date));
                            if (activitySubmitService.save(activitySubmit)) {
                                rep.setResp(0, null, "报名成功");
                            } else {
                                rep.setResp(-1, null, "报名失败");
                            }
                        } else {
                            rep.setResp(-1, null, "不在可用时间范围内");
                        }
                    }
                }
            }catch (Exception e){
                rep.setResp(-1,null,"非法参数");
            }
        }else {
            rep.setResp(-1,null,"参数为空");
        }
        return rep;
    }

}
