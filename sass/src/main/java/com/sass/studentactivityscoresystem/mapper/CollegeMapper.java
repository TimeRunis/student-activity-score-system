package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.College;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollegeMapper extends BaseMapper<College> {
    //名字模糊查找
    @Select("select * from college where college_name like CONCAT('%',#{name},'%')")
    List<College> findByName(String name);
    //名字精确查找
    @Select("select * from college where college_name=#{name}")
    College selectByName(String name);
    //名字删除
    @Delete("delete from college where college_name=#{name}")
    int deleteName(String name);
}
