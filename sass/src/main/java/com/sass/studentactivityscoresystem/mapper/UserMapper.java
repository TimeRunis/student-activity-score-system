package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper extends BaseMapper<User>{
    //邮箱查询所有用户信息
    @Select("select * from user where user_mail=#{mail}")
    User selectByMail(String mail);
    //id查询用户邮箱
    @Select("select user_mail from user where user_id=#{id}")
    User getUserMailById(int id);
    //用户邮箱查询用户id
    @Select("select user_id from user where user_mail=#{mail}")
    User getUserIdByMail(String mail);

}
