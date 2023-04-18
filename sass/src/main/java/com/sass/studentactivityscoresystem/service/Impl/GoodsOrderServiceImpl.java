package com.sass.studentactivityscoresystem.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.GoodsOrderMapper;
import com.sass.studentactivityscoresystem.service.GoodsOrderService;
import com.sass.studentactivityscoresystem.service.GoodsService;
import com.sass.studentactivityscoresystem.service.UserScoreService;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {
    private User user;
    private GoodsOrder goodsOrder;
    private GoodsService goodsService;
    private UserScoreService userScoreService;
    private UserService userService;

    public GoodsOrderServiceImpl(User user, GoodsOrder goodsOrder, GoodsService goodsService, UserScoreService userScoreService, UserService userService) {
        this.user = user;
        this.goodsOrder = goodsOrder;
        this.goodsService = goodsService;
        this.userScoreService = userScoreService;
        this.userService = userService;
    }

    @Override
    public synchronized boolean createOrder(Goods goods, int userId){
        this.user= (User) userService.userInfoById(userId);
        if(goods.getGoodsPrice()<=user.getActivityScore()){
            //创建订单
            this.goodsOrder.setGoodsId(goods.getGoodsId());
            this.goodsOrder.setBuyTime(new Date());
            this.goodsOrder.setEndTime(null);
            this.goodsOrder.setGoId(0);
            this.goodsOrder.setTransportInfo(null);
            this.goodsOrder.setUserId(userId);
            this.getBaseMapper().insert(goodsOrder);
            //库存结算
            goods.setGoodsNumber(goods.getGoodsNumber()-1);
            this.goodsService.updateById(goods);
            //积分结算
            this.userScoreService.minusUserScore(userId,goods.getGoodsPrice());
            return true;
        }else {
            return false;
        }
    }
}
