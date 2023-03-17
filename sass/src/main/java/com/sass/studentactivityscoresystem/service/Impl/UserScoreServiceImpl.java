package com.sass.studentactivityscoresystem.service.Impl;

import com.sass.studentactivityscoresystem.mapper.ScoreMapper;
import com.sass.studentactivityscoresystem.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserScoreServiceImpl implements UserScoreService {
    private final ScoreMapper scoreMapper;

    @Autowired
    UserScoreServiceImpl(ScoreMapper scoreMapper){
        this.scoreMapper = scoreMapper;
    }

    @Override
    public int addUserScore(int id, int number) {
        if(id>0){
            if(scoreMapper.updateScore(id,number)){
                scoreMapper.updateTotalScore(id,number);
                return 0;
            }
        }
        return -1;
    }

    @Override
    public int minusUserScore(int id, int number) {
        if(id>0){
            if(scoreMapper.updateScore(id,-number)) {
                return 0;
            }
        }
        return -1;
    }


    @Override
    public int setUserScore(int id, int number) {
        if(id>0){
            if(scoreMapper.setScore(id,number)){
                return 0;
            }
        }
        return -1;
    }

    @Override
    public int changeUserTotalActivityScore(int id, int number) {
        if(id>0){
            if(scoreMapper.updateTotalScore(id,number)){
                return 0;
            }
        }
        return -1;
    }


    @Override
    public int setUserTotalActivityScore(int id, int number) {
        if(id>0){
            if(scoreMapper.setTotalScore(id,number)){
                return 0;
            }
        }
        return -1;

    }
}
