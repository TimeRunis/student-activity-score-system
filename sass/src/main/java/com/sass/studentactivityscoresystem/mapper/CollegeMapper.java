package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.College;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CollegeMapper extends BaseMapper<College> {
    @Select("select * from college where college_name=#{name}")
    public College selectByName(String name);

    @Delete("delete from college where college_name=#{name}")
    public int deleteName(String name);
}
