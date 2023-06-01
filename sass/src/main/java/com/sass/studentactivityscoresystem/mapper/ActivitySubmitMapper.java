package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sass.studentactivityscoresystem.entity.Activity;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ActivitySubmitMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<ActivitySubmit> {

    @Select("select * from activity_submit where user_id=#{userId} order by submit_time desc")
    @Results(
            {
                    @Result(column = "as_id",property = "asId"),
                    @Result(column = "user_id",property = "user",javaType = Activity.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "user_name",property = "userName"),
                    @Result(column = "activity_id",property = "activityId"),
                    @Result(column = "activity_id",property = "activity",javaType = Activity.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.ActivityMapper.selectById")
                    ),
                    @Result(column = "submit_time",property = "submitTime"),
            }
    )
    Page<ActivitySubmit> getOneByUserId(Page<ActivitySubmit> page,int userId);

    @Select("select * from activity_submit where activity_id=#{acId} order by submit_time desc")
    @Results(
            {
                    @Result(column = "as_id",property = "asId"),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = Activity.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "activity_id",property = "activityId"),
                    @Result(column = "activity_id",property = "activity",javaType = Activity.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.ActivityMapper.selectById")
                    ),
                    @Result(column = "submit_time",property = "submitTime"),
            }
    )
    Page<ActivitySubmit> getOneByAcId(Page<ActivitySubmit> page,int acId);

    @Select("select * from activity_submit")
    @Results(
            {
                    @Result(column = "as_id",property = "asId"),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = Activity.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "activity_id",property = "activityId"),
                    @Result(column = "activity_id",property = "activity",javaType = Activity.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.ActivityMapper.selectById")
                    ),
                    @Result(column = "submit_time",property = "submitTime"),
            }
    )
    Page<ActivitySubmit> getAll(Page<ActivitySubmit> page);

}
