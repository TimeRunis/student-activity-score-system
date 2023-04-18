package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.DeleteController;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PostController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.service.GoodsOrderService;
import com.sass.studentactivityscoresystem.service.GoodsService;
import com.sass.studentactivityscoresystem.service.UserScoreService;
import com.sass.studentactivityscoresystem.service.UserService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@Scope("prototype")
@CrossOrigin(origins = "*")
public class GoodsOrderController extends BaseController implements PostController , GetController, PutController, DeleteController {
    private GoodsOrderService goodsOrderService;
    private GoodsService goodsService;
    private Goods goods;

    public GoodsOrderController(GoodsOrderService goodsOrderService, GoodsService goodsService, Goods goods) {
        this.goodsOrderService = goodsOrderService;
        this.goodsService = goodsService;
        this.goods = goods;
    }

    @Override
    public RespBody doDelete(Map<Object, String> map, HttpServletRequest request) {
        return rep;
    }

    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        return rep;
    }

    @PostMapping("/goodsOrder")
    @Override
    public RespBody doPost(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            if(map.containsKey("goodsId")){
                //获取商品信息
                goods=goodsService.getById(map.get("goodsId"));
                //校验
                if(goods!=null){
                    //创建订单
                    if(this.goodsOrderService.createOrder(goods,JwtUtils.getUserId(request.getHeader("token")))){
                        rep.setResp(0,null,"下单成功");
                    }else {
                        rep.setResp(-1,null,"积分不足");
                    }
                }else {
                    rep.setResp(-1,null,"不存在商品");
                }
            }else {
                rep.setResp(-1,null,"非法参数");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        return rep;
    }
}
