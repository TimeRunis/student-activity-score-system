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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GoodsOrderController extends BaseController implements PostController , GetController, PutController, DeleteController {
    private GoodsOrderService goodsOrderService;
    private GoodsService goodsService;
    private UserService userService;
    private UserScoreService userScoreService;
    private User user;
    private Goods goods;
    private GoodsOrder goodsOrder;

    public GoodsOrderController(GoodsOrderService goodsOrderService, GoodsService goodsService, UserService userService, UserScoreService userScoreService, User user, Goods goods, GoodsOrder goodsOrder) {
        this.goodsOrderService = goodsOrderService;
        this.goodsService = goodsService;
        this.userService = userService;
        this.userScoreService = userScoreService;
        this.user = user;
        this.goods = goods;
        this.goodsOrder = goodsOrder;
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
            if(map.containsKey("goodsId")&&map.containsKey("number")){
                int number=Integer.parseInt(map.get("number"));
                //检查数量是否异常
                if(number<=0){
                    rep.setResp(-1,null,"非法参数");
                    return rep;
                }
                //获取商品和用户信息
                goods=goodsService.getById(map.get("goodsId"));
                user=userService.getById(JwtUtils.getUserId(request.getHeader("token")));
                //校验
                if(goods!=null){
                    //检查用户积分是否充足
                    if(goods.getGoodsPrice()*number<=user.getActivityScore()){
                        //创建订单
                        goodsOrder.setGoodsId(Integer.parseInt(map.get("goodsId")));
                        goodsOrder.setBuyTime(new Date());
                        goodsOrder.setEndTime(null);
                        goodsOrder.setGoId(0);
                        goodsOrder.setTransportInfo(null);
                        goodsOrder.setUserId(JwtUtils.getUserId(request.getHeader("token")));
                        goodsOrderService.getBaseMapper().insert(goodsOrder);
                        //库存结算
                        goods.setGoodsNumber(goods.getGoodsNumber()-number);
                        goodsService.updateById(goods);
                        //积分结算
                        userScoreService.minusUserScore(JwtUtils.getUserId(request.getHeader("token")),goods.getGoodsPrice()*number);
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
