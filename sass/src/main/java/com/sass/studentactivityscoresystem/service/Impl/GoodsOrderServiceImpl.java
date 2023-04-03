package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;
import com.sass.studentactivityscoresystem.mapper.GoodsOrderMapper;
import com.sass.studentactivityscoresystem.service.GoodsOrderService;
import org.springframework.stereotype.Service;

@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {
}
