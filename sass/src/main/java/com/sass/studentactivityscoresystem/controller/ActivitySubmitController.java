package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.service.ActivitySubmitService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ActivitySubmitController extends BaseController implements GetController {
    private ActivitySubmitService activitySubmitService;

    @Autowired
    public ActivitySubmitController(ActivitySubmitService activitySubmitService) {
        this.activitySubmitService = activitySubmitService;
    }

    @GetMapping("/activitySubmit")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    int acid= Integer.parseInt(map.get("acid"));
                    rep.setResp(0,activitySubmitService.findByAcidPage(acid,Integer.parseInt(map.get("current")),Integer.parseInt(map.get("size"))),"查询成功");
                }catch (Exception e){
                    rep.setResp(-1,null,"非法参数");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"参数为空");
        }

        return rep;
    }
}
