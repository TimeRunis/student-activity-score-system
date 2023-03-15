package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.entity.College;
import com.sass.studentactivityscoresystem.service.CollegeService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class CollegeController extends BaseController{
    private final CollegeService collegeService;
    private College college;

    @Autowired
    CollegeController(CollegeService collegeService,College college){
        this.collegeService=collegeService;
        this.college=college;
    }

    @GetMapping("/college")
    public Object doGet(String id){
        if(id!=null){
            rep.setResp(0,collegeService.collegeInfoById(id),"查询成功");
        }
        return rep;
    }

    @PostMapping("/college")
    public Object doPost(@RequestBody Map<String,Object> map, HttpServletRequest request){
        //参数检查
        if (!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                college.setCollegeName(map.get("collegeName").toString());
                int flag=collegeService.addCollege(college);
                if (flag==1){
                    rep.setResp(0,null,"添加成功");
                }else {
                    rep.setResp(flag,null,"重复添加");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"参数错误");
        }
        return rep;
    }

    @DeleteMapping("/college")
    public Object doDelete(String name,HttpServletRequest request){
        //参数检查
        if(!name.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                int flag=collegeService.removeCollegeByName(name);
                if(flag==0){
                    rep.setResp(0,null,"删除成功");
                }else {
                    rep.setResp(flag,null,"错误");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"参数错误");
        }
        return rep;
    }
}
