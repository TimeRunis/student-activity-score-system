package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.College;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import com.sass.studentactivityscoresystem.mapper.CollegeMapper;
import com.sass.studentactivityscoresystem.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper,College> implements CollegeService {

    private final CollegeMapper collegeMapper;

    @Autowired
    CollegeServiceImpl(CollegeMapper collegeMapper){
        //注入mapper
        this.collegeMapper=collegeMapper;
    }

    @Override
    public Object collegeInfoById(int id) {
        //检查id是否为空
        if(id>0){
            return collegeMapper.selectById(id);
        }
            return -1;
    }

    @Override
    public Page<College> findByName(String name, String current, String size) {
        ReturnBody returnBody =new ReturnBody();
        //分页参数
        Page<College> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(College::getCollegeName,name);
        return this.getBaseMapper().selectPage(page,queryWrapper);
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
    public int removeCollegeById(int id) {
        //判断参数是否为空
        if(id>0){
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

    @Override
    public Page<College> findAllCollege(String current, String size) {
        //分页参数
        Page<College> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        this.getBaseMapper().selectPage(page,queryWrapper);
        return page;
    }
}
