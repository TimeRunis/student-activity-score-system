package com.sass.studentactivityscoresystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class GoodsOrder {
    @TableId("go_id")
    Integer goId;
    Integer goodsId;
    @TableField(exist = false)
    Goods goods;
    Integer userId;
    @TableField(exist = false)
    User user;
    String transportInfo;
    Date buyTime;
    Date endTime;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTransportInfo() {
        return transportInfo;
    }

    public Integer getGoId() {
        return goId;
    }

    public void setGoId(Integer goId) {
        this.goId = goId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTransportInfo(String transportInfo) {
        this.transportInfo = transportInfo;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

