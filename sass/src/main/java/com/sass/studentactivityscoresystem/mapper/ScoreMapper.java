package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScoreMapper extends BaseMapper<User> {
    //增减活跃积分
    @Update("update user set activity_score=activity_score+#{number} where user_id=#{id}")
    boolean updateScore(int id,int number);
    //设置活跃积分
    @Update("update user set activity_score=#{number} where user_id=#{id}")
    boolean setScore(int id,int number);
    //增减总活跃积分
    @Update("update user set total_activity_score=total_activity_score+#{number} where user_id=#{id}")
    boolean updateTotalScore(int id,int number);
    //设置总活跃积分
    @Update("update user set total_activity_score=#{number} where user_id=#{id}")
    boolean setTotalScore(int id,int number);
}
