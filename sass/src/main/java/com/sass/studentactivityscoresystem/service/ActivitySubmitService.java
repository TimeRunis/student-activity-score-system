package com.sass.studentactivityscoresystem.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;
import com.sass.studentactivityscoresystem.entity.ReturnBody;

public interface ActivitySubmitService extends IService<ActivitySubmit> {
    Page findByAcidPage(int acId, int current, int size);
}
