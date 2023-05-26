package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sass.studentactivityscoresystem.entity.ScoreCode;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ScoreCodeMapper extends BaseMapper<ScoreCode> {
    @Select("select * from score_code")
    @Results(
            {
                    @Result(column = "code",property = "code"),
                    @Result(column = "number",property = "number"),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "creator_id",property = "creatorId"),
                    @Result(column = "creator_id",property = "creator",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "create_time",property = "createTime"),
                    @Result(column = "dead_line",property = "deadLine"),
                    @Result(column = "use_time",property = "useTime"),
                    @Result(column = "is_used",property = "used"),
            }
    )
    Page<ScoreCode> getAllByPage(Page<ScoreCode> page);


    @Select("select * from score_code where code=#{code}")
    @Results(
            {
                    @Result(column = "code",property = "code"),
                    @Result(column = "number",property = "number"),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "creator_id",property = "creatorId"),
                    @Result(column = "creator_id",property = "creator",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "create_time",property = "createTime"),
                    @Result(column = "dead_line",property = "deadLine"),
                    @Result(column = "use_time",property = "useTime"),
                    @Result(column = "is_used",property = "used"),
            }
    )
    Page<ScoreCode> getOneByPage(Page<ScoreCode> page,String code);
}
