package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;

public interface GoodsOrderService extends IService<GoodsOrder> {
    boolean createOrder(Goods goods, int userId);
    Page<GoodsOrder> findAll(String current, String size);

    Page<GoodsOrder> findFinished(String current, String size);

    Page<GoodsOrder> findUnfinished(String current, String size);

    Page<GoodsOrder> findOneByUser(String userId, String current, String size);

    Page<GoodsOrder> findOneByGoods(String goodsId, String current, String size);

    Page<GoodsOrder> findOneByGoid(String goId, String current, String size);
}
