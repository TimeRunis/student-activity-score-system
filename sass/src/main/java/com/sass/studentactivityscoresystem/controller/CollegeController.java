package com.sass.studentactivityscoresystem.controller;

import com.alibaba.fastjson.JSON;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.College;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.service.CollegeService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Scope("prototype")
@CrossOrigin(origins = "*")
public class CollegeController extends BaseController implements GetController, PutController {
    private final CollegeService collegeService;
    private College college;

    @Autowired
    CollegeController(CollegeService collegeService,College college){
        this.collegeService=collegeService;
        this.college=college;
    }

    @GetMapping("/college")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        if (!map.isEmpty()){
            if(map.containsKey("id")){
                rep.setResp(0,collegeService.collegeInfoById(Integer.parseInt(map.get("id"))),"查询成功");
            }else if(map.containsKey("key")&&map.containsKey("current")&&map.containsKey("size")){
                rep.setResp(0,collegeService.findByName(map.get("key"),map.get("current"),map.get("size")),"查询成功");
            } else if(map.containsKey("current")&&map.containsKey("size")){
                rep.setResp(0,collegeService.findAllCollege(map.get("current"),map.get("size")),"查询成功");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PostMapping("/college")
    public Object doPost(@RequestBody Map<String,Object> map, HttpServletRequest request){
        //参数检查
        if (!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    college.setCollegeName(map.get("collegeName").toString());
                    int flag=collegeService.addCollege(college);
                    if (flag==1){
                        rep.setResp(0,null,"添加成功");
                    }else {
                        rep.setResp(flag,null,"重复添加");
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

    @DeleteMapping("/college")
    public Object doDelete(int id,HttpServletRequest request){
        //参数检查
        if(id>0){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    int flag=collegeService.removeCollegeById(id);
                    if(flag==0){
                        rep.setResp(0,null,"删除成功");
                    }else {
                        rep.setResp(flag,null,"错误");
                    }
                }catch (Exception e){
                    rep.setResp(-1,null,"参数错误");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"参数错误");
        }
        return rep;
    }


    @PutMapping("/college")
    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        if(!map.isEmpty()){
            try {
                this.college= JSON.parseObject(JSON.toJSONString(map),college.getClass());
                if(this.collegeService.collegeInfoById(this.college.getCollegeId())!=null){
                    this.collegeService.updateById(this.college);
                    rep.setResp(0,null,"修改成功");
                }else {
                    rep.setResp(-1,null,"不存在的学院");
                }
            }catch (Exception e){
                rep.setResp(-1,null,"非法参数");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
