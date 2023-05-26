package com.sass.studentactivityscoresystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.GoodsOrder;
import com.sass.studentactivityscoresystem.entity.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface GoodsOrderMapper extends BaseMapper<GoodsOrder> {
    @Select("select * from goods_order")
    @Results(
            {
                    @Result(column = "go_id",property = "goId"),
                    @Result(column = "goods_id",property = "goodsId"),
                    @Result(column = "goods_id",property = "goods",javaType = Goods.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.GoodsMapper.selectById")
                    ),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "transport_info",property = "transportInfo"),

                    @Result(column = "buy_time",property = "buyTime"),
                    @Result(column = "end_time",property = "endTime"),
            }
    )
    Page<GoodsOrder> getAllByPage(Page<GoodsOrder> page);

    @Select("select * from goods_order where end_time is not null")
    @Results(
            {
                    @Result(column = "go_id",property = "goId"),
                    @Result(column = "goods_id",property = "goodsId"),
                    @Result(column = "goods_id",property = "goods",javaType = Goods.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.GoodsMapper.selectById")
                    ),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "transport_info",property = "transportInfo"),

                    @Result(column = "buy_time",property = "buyTime"),
                    @Result(column = "end_time",property = "endTime"),
            }
    )
    Page<GoodsOrder> getFinishedByPage(Page<GoodsOrder> page);

    @Select("select * from goods_order where end_time is null")
    @Results(
            {
                    @Result(column = "go_id",property = "goId"),
                    @Result(column = "goods_id",property = "goodsId"),
                    @Result(column = "goods_id",property = "goods",javaType = Goods.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.GoodsMapper.selectById")
                    ),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "transport_info",property = "transportInfo"),

                    @Result(column = "buy_time",property = "buyTime"),
                    @Result(column = "end_time",property = "endTime"),
            }
    )
    Page<GoodsOrder> getUnfinishedByPage(Page<GoodsOrder> page);

    @Select("select * from goods_order where user_id =#{userId}")
    @Results(
            {
                    @Result(column = "go_id",property = "goId"),
                    @Result(column = "goods_id",property = "goodsId"),
                    @Result(column = "goods_id",property = "goods",javaType = Goods.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.GoodsMapper.selectById")
                    ),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "transport_info",property = "transportInfo"),

                    @Result(column = "buy_time",property = "buyTime"),
                    @Result(column = "end_time",property = "endTime"),
            }
    )
    Page<GoodsOrder> getByUserId(Page<GoodsOrder> page,String userId);

    @Select("select * from goods_order where goods_id =#{goodsId}")
    @Results(
            {
                    @Result(column = "go_id",property = "goId"),
                    @Result(column = "goods_id",property = "goodsId"),
                    @Result(column = "goods_id",property = "goods",javaType = Goods.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.GoodsMapper.selectById")
                    ),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "transport_info",property = "transportInfo"),

                    @Result(column = "buy_time",property = "buyTime"),
                    @Result(column = "end_time",property = "endTime"),
            }
    )
    Page<GoodsOrder> getByGoodsId(Page<GoodsOrder> page,String goodsId);

    @Select("select * from goods_order where go_id =#{goId}")
    @Results(
            {
                    @Result(column = "go_id",property = "goId"),
                    @Result(column = "goods_id",property = "goodsId"),
                    @Result(column = "goods_id",property = "goods",javaType = Goods.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.GoodsMapper.selectById")
                    ),
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "user_id",property = "user",javaType = User.class,
                            one=@One(select = "com.sass.studentactivityscoresystem.mapper.UserMapper.selectById")
                    ),
                    @Result(column = "transport_info",property = "transportInfo"),

                    @Result(column = "buy_time",property = "buyTime"),
                    @Result(column = "end_time",property = "endTime"),
            }
    )
    Page<GoodsOrder> getByGoId(Page<GoodsOrder> page,String goId);
}
