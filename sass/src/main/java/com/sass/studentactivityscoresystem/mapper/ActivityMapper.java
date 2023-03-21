package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    //通过名字模糊搜索
    @Select("select * from activity where activity_name like CONCAT('%',#{name},'%')")
    public List<Activity> getInfoByName(String name);
}
