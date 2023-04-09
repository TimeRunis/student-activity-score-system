package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


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

    @Update("update user SET user_name=#{userName}, real_name=#{realName}, user_mail=#{userMail}, gender=#{gender}, birthday=#{birthday}, college_id=#{collegeId} WHERE user_id=#{userId}")
    Boolean updateBaseInfo(User user);

    @Update("update user SET user_psw=#{userPsw} where user_id=#{userId}")
    Boolean updateUserPsw(int userId,String userPsw);

}
