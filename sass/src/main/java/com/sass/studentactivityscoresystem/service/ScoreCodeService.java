package com.sass.studentactivityscoresystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.ScoreCode;

public interface ScoreCodeService extends IService<ScoreCode> {
    String generateCode();

    Boolean removeCode(String code);

    Page<ScoreCode> findAll(String current, String size);
}
