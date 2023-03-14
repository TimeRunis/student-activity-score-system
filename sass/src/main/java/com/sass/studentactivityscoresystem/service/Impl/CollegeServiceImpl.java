package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.entity.College;
import com.sass.studentactivityscoresystem.mapper.CollegeMapper;
import com.sass.studentactivityscoresystem.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeServiceImpl implements CollegeService {

    private final CollegeMapper collegeMapper;

    @Autowired
    CollegeServiceImpl(CollegeMapper collegeMapper){
        //注入mapper
        this.collegeMapper=collegeMapper;
    }

    @Override
    public Object collegeInfoById(String id) {
        //检查id是否为空
        if(id!=null){
            return collegeMapper.selectById(id);
        }
        return -1;
    }

    @Override
    public Object collegeInfoByName(String name) {
        //检查名字是否为空
        if(name!=null){
            return collegeMapper.selectByName(name);
        }
        return -1;
    }

    @Override
    public int addCollege(College college) {
        //判断参数是否为空
        if(college!=null){
            //查询是否已经存在
            if(collegeMapper.selectByName(college.getCollegeName())==null){
                return collegeMapper.insert(college);
            }else {
                //已存在的对象
                return -2;
            }
        }else {
            //参数为空
            return -1;
        }
    }

    @Override
    public int removeCollegeById(String id) {
        //判断参数是否为空
        if(id!=null){
            //查询是否已经存在
            if(collegeMapper.selectById(id)==null){
                //不存在的学院
                return -2;
            }else {
                //执行删除
                collegeMapper.deleteById(id);
                return 0;
            }
        }else {
            //参数为空
            return -1;
        }
    }

    @Override
    public int removeCollegeByName(String name) {
        //判断参数是否为空
        if(name!=null){
            //查询是否已经存在
            if(collegeMapper.selectByName(name)==null){
                //不存在的学院
                return -2;
            }else {
                //执行删除
                collegeMapper.deleteName(name);
                return 0;
            }
        }else {
            //参数为空
            return -1;
        }
    }
}
