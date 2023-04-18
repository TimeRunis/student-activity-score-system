package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;

public interface GoodsOrderService extends IService<GoodsOrder> {
    boolean createOrder(Goods goods, int userId);
}
