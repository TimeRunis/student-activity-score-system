package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.College;
import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface CollegeService extends IService<College> {
    //id查询学院信息
    Object collegeInfoById(int id);
    //模糊查询名字
    public Page<College> findByName(String name, String current, String size);
    //新增学院
    int addCollege(College college);
    //通过ID移除学院
    int removeCollegeById(int id);
    //通过学院名移除学院
    int removeCollegeByName(String Name);

    Page<College> findAllCollege(String current, String size);
}
