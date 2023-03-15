package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper extends BaseMapper<User>{
    @Select("select * from user where user_mail=#{mail}")
    User selectByMail(String mail);

    @Select("select user_mail from user where user_id=#{id}")
    User getUserMailById(int id);

    @Select("select user_id from user where user_mail=#{mail}")
    User getUserIdByMail(String mail);

    @Update("update user set activity_score=activity_score+#{number} where user_id=#{id}")
    void updateScore(int id,int number);
}
