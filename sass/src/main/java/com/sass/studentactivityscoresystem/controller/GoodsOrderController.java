package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.DeleteController;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PostController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.TransportInfo;
import com.sass.studentactivityscoresystem.service.GoodsOrderService;
import com.sass.studentactivityscoresystem.service.GoodsService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@CrossOrigin(origins = "*")
public class GoodsOrderController extends BaseController implements PostController , GetController, PutController, DeleteController {
    private GoodsOrderService goodsOrderService;
    private GoodsService goodsService;
    private Goods goods;
    private List<TransportInfo> transportInfoList;
    private TransportInfo transportInfo;

    public GoodsOrderController(GoodsOrderService goodsOrderService, GoodsService goodsService, Goods goods, List<TransportInfo> transportInfoList, TransportInfo transportInfo) {
        this.goodsOrderService = goodsOrderService;
        this.goodsService = goodsService;
        this.goods = goods;
        this.transportInfoList = transportInfoList;
        this.transportInfo = transportInfo;
    }

    @Override
    public RespBody doDelete(Map<Object, String> map, HttpServletRequest request) {
        return rep;
    }

    @GetMapping("/goodsOrder")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        if(!map.isEmpty()){
            if(map.containsKey("current")&&map.containsKey("size")){
                //管理员
                if(JwtUtils.checkPermission(request.getHeader("token"),9)&&map.containsKey("admin")) {
                    if (map.containsKey("userId")) {
                        rep.setResp(0, goodsOrderService.findOneByUser(map.get("userId"), map.get("current"), map.get("size")), null);
                    } else if (map.containsKey("goodsId")) {
                        rep.setResp(0, goodsOrderService.findOneByGoods(map.get("goodsId"), map.get("current"), map.get("size")), null);
                    } else if(map.containsKey("goId")){
                        rep.setResp(0, goodsOrderService.findOneByGoid(map.get("goId"), map.get("current"), map.get("size")), null);
                    }else{
                        if(map.containsKey("filter")){
                            switch (map.get("filter")){
                                case "0":
                                    //查询所有
                                    rep.setResp(0,goodsOrderService.findAll(map.get("current"),map.get("size")),null);
                                    break;
                                case "1":
                                    //已完成的筛选
                                    rep.setResp(0,goodsOrderService.findFinished(map.get("current"),map.get("size")),null);
                                    break;
                                case "2":
                                    //未完成的筛选
                                    rep.setResp(0,goodsOrderService.findUnfinished(map.get("current"),map.get("size")),null);
                                    break;                             }
                        }else {
                            //查询所有
                            rep.setResp(0,goodsOrderService.findAll(map.get("current"),map.get("size")),null);
                        }
                    }
                }else {
                    //用户
                    rep.setResp(0,goodsOrderService.findOneByUser(String.valueOf(JwtUtils.getUserId(request.getHeader("token"))),map.get("current"),map.get("size")),null);
                }
            }else {
                rep.setResp(-1,null,"非法参数");
            }

        }else {
            rep.setResp(-1,null,"空参数");
        }
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

    @PutMapping("goodsOrder")
    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()&&map.containsKey("goId")){
            transportInfoList= goodsOrderService.getById(map.get("goId")).getTransportInfo();
            transportInfo.setUpdateTime(new Date());
            if(map.containsKey("info")){
                if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                    transportInfo.setInfo(map.get("info"));
                    transportInfoList.add(transportInfo);
                    if(goodsOrderService.updateOrder(map.get("goId"),transportInfoList)){
                        rep.setResp(0,null,"更新成功");
                    }else {
                        rep.setResp(-1,null,"更新失败");
                    }
                }else {
                    rep.setResp(-1,null,"非法权限");
                }
            }else if(map.containsKey("userReceive")){
                transportInfo.setInfo("用户收货");
                transportInfoList.add(transportInfo);
                if(goodsOrderService.updateOrder(map.get("goId"),transportInfoList)&&goodsOrderService.endOrder(map.get("goId"),new Date())){
                    rep.setResp(0,null,"收货成功");
                }else {
                    rep.setResp(-1,null,"收货失败");
                }
            }else if(map.containsKey("adminEnd")){
                transportInfoList= goodsOrderService.getById(map.get("goId")).getTransportInfo();
                transportInfo.setInfo("管理员关闭订单");
                transportInfoList.add(transportInfo);
                if(goodsOrderService.updateOrder(map.get("goId"),transportInfoList)&&goodsOrderService.endOrder(map.get("goId"),new Date())){
                    rep.setResp(0,null,"关闭订单成功");
                }else {
                    rep.setResp(-1,null,"关闭订单失败");
                }
            }else {
                rep.setResp(-1,null,"非法参数");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
