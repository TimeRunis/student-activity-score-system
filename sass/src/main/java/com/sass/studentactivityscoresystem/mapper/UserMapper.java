package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User>{
    //用户名模糊查询
    @Select("select * from user where user_name like CONCAT('%',#{name},'%')")
    List<User> findUserByName(String name);
    //真实姓名模糊查询
    @Select("select * from user where rel_name like CONCAT('%',#{name},'%')")
    List<User> findUserByRelname(String name);
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
