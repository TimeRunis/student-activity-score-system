package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.ScoreCode;
import com.sass.studentactivityscoresystem.mapper.ScoreCodeMapper;
import com.sass.studentactivityscoresystem.service.ScoreCodeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class ScoreCodeServiceImpl extends ServiceImpl<ScoreCodeMapper, ScoreCode> implements ScoreCodeService {
    @Override
    public String generateCode() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public Boolean useCode(String code,String userId){
        try{
            //查询
            ScoreCode scoreCode = this.getBaseMapper().selectById(code);
            //更新数据
            scoreCode.setUserId(Integer.valueOf(userId));
            scoreCode.setUseTime(new Date());
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<ScoreCode> getOneByPage(String code) {
        //分页参数
        Page<ScoreCode> page = new Page<>(1, 10);
        return this.getBaseMapper().getOneByPage(page,code);
    }

    @Override
    public Boolean removeCode(String code){
        try{
            //查询是否存在
            ScoreCode scoreCode = this.getBaseMapper().selectById(code);
            if(scoreCode!=null){
                this.getBaseMapper().deleteById(code);
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<ScoreCode> findAll(String current, String size) {
        //分页参数
        Page<ScoreCode> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getAllByPage(page);
    }
}
