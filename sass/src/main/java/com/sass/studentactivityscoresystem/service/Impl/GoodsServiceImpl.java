package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.GoodsMapper;
import com.sass.studentactivityscoresystem.service.GoodsService;

import org.springframework.stereotype.Service;


@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Override
    public Page<Goods> findAll(String current, String size) {
        //分页参数
        Page<Goods> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }

    @Override
    public Page<Goods> find(String key, String current, String size) {
        //分页参数
        Page<Goods> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Goods::getGoodsName,key).or().like(Goods::getGoodsDetail,key);
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }
}
