package com.sass.studentactivityscoresystem.service;

import com.sass.studentactivityscoresystem.entity.College;

public interface CollegeService {
    //id查询学院信息
    Object collegeInfoById(String id);
    //学院名查询学院信息
    Object collegeInfoByName(String name);
    //新增学院
    int addCollege(College college);
    //通过ID移除学院
    int removeCollegeById(String id);
    //通过学院名移除学院
    int removeCollegeByName(String Name);
}
