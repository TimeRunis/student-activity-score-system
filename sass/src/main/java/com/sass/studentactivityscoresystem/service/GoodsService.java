package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.Goods;
import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface GoodsService extends IService<Goods> {
    Page<Goods> findAll(String current, String size);

    Page<Goods> find(String key, String current, String size);
}
