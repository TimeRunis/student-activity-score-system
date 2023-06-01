package com.sass.studentactivityscoresystem.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sass.studentactivityscoresystem.entity.*;
import com.sass.studentactivityscoresystem.mapper.GoodsOrderMapper;
import com.sass.studentactivityscoresystem.service.GoodsOrderService;
import com.sass.studentactivityscoresystem.service.GoodsService;
import com.sass.studentactivityscoresystem.service.UserScoreService;
import com.sass.studentactivityscoresystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {
    private User user;
    private GoodsOrder goodsOrder;
    private GoodsService goodsService;
    private UserScoreService userScoreService;
    private UserService userService;
    private List<TransportInfo> transportInfoList;
    private TransportInfo transportInfo;


    public GoodsOrderServiceImpl(User user, GoodsOrder goodsOrder, GoodsService goodsService, UserScoreService userScoreService, UserService userService, List<TransportInfo> transportInfoList, TransportInfo transportInfo) {
        this.user = user;
        this.goodsOrder = goodsOrder;
        this.goodsService = goodsService;
        this.userScoreService = userScoreService;
        this.userService = userService;
        this.transportInfoList = transportInfoList;
        this.transportInfo = transportInfo;
    }

    @Override
    public synchronized boolean createOrder(Goods goods, int userId){
        this.user= (User) userService.userInfoById(userId);
        if(goods.getGoodsPrice()<=user.getActivityScore()){
            //设置物流信息
            transportInfoList.clear();
            transportInfo.setInfo("用户下单");
            transportInfo.setUpdateTime(new Date());
            transportInfoList.add(transportInfo);
            //创建订单
            this.goodsOrder.setGoodsId(goods.getGoodsId());
            this.goodsOrder.setBuyTime(new Date());
            this.goodsOrder.setEndTime(null);
            this.goodsOrder.setGoId(0);
            this.goodsOrder.setTransportInfo(transportInfoList);
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

    @Override
    public Page<GoodsOrder> findAll(String current, String size) {
        //分页参数
        Page<GoodsOrder> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getAllByPage(page);
    }

    @Override
    public Page<GoodsOrder> findFinished(String current, String size) {
        //分页参数
        Page<GoodsOrder> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getFinishedByPage(page);
    }

    @Override
    public Page<GoodsOrder> findUnfinished(String current, String size) {
        //分页参数
        Page<GoodsOrder> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getUnfinishedByPage(page);
    }

    @Override
    public Page<GoodsOrder> findOneByUser(String userId,String current, String size) {
        //分页参数
        Page<GoodsOrder> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getByUserId(page,userId);
    }

    @Override
    public Page<GoodsOrder> findOneByGoods(String goodsId,String current, String size) {
        //分页参数
        Page<GoodsOrder> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getByGoodsId(page,goodsId);
    }

    @Override
    public Page<GoodsOrder> findOneByGoid(String goId, String current, String size) {
        //分页参数
        Page<GoodsOrder> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        return this.getBaseMapper().getByGoId(page,goId);
    }

    @Override
    public Boolean updateOrder(String goId,List<TransportInfo> transportInfoList){
        return getBaseMapper().updateTransportInfo(goId, JSON.toJSONString(transportInfoList));
    }

    @Override
    public Boolean endOrder(String goId,Date endTime){
        return getBaseMapper().endOrder(goId,endTime);
    }

}
