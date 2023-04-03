package com.sass.studentactivityscoresystem.controller;

import com.alibaba.fastjson.JSON;
import com.sass.studentactivityscoresystem.controller.method.DeleteController;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PostController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.service.GoodsService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GoodsController extends BaseController implements PostController, PutController, GetController, DeleteController {
    private Goods goods;
    private GoodsService goodsService;

    @Autowired
    public GoodsController(Goods goods, GoodsService goodsService) {
        this.goods = goods;
        this.goodsService = goodsService;
    }

    @PostMapping("/goods")
    @Override
    public RespBody doPost(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    goods=JSON.parseObject(JSON.toJSONString(map),goods.getClass());
                    goods.setGoodsId(0);
                    goods.setCreatorId(JwtUtils.getUserId(request.getHeader("token")));
                    goods.setCreateTime(new Date());
                    goods.setShow(false);
                    goods.setUpdateTime(null);
                    if(goodsService.getBaseMapper().insert(goods)==1){
                        rep.setResp(0,null,"成功添加商品");
                    }else {
                        rep.setResp(-1,null,"添加商品失败");
                    }
                }catch (Exception e){
                    rep.setResp(-1,goods,"非法参数");
                    e.printStackTrace();
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PutMapping("/goods")
    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    goods=JSON.parseObject(JSON.toJSONString(map),goods.getClass());
                    goods.setCreatorId(JwtUtils.getUserId(request.getHeader("token")));
                    goods.setCreateTime(new Date());
                    goods.setShow(false);
                    goods.setUpdateTime(null);
                    if(goodsService.getBaseMapper().updateById(goods)==1){
                        rep.setResp(0,null,"商品信息修改成功");
                    }else {
                        rep.setResp(-1,null,"商品信息修改失败");
                    }
                }catch (Exception e){
                    rep.setResp(-1,goods,"非法参数");
                    e.printStackTrace();
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @GetMapping("/goods")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            if(map.containsKey("current")&&map.containsKey("size")){
                rep.setResp(0,goodsService.findAll(map.get("current"),map.get("size")),"查询成功");
            }else if (map.containsKey("id")){
                rep.setResp(0,goodsService.getById(map.get("id")),"查询成功");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @DeleteMapping("/goods")
    @Override
    public RespBody doDelete(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                if(map.containsKey("id")){
                    if(goodsService.getById(map.get("id"))!=null){
                        goodsService.removeById(map.get("id"));
                        rep.setResp(0,null,"删除商品成功");
                    }else {
                        rep.setResp(-1,null,"不存在的商品");
                    }
                }else {
                    rep.setResp(-1,null,"非法参数");
                }
            }else {
                rep.setResp(-1,null,"权限不足");
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }
}
