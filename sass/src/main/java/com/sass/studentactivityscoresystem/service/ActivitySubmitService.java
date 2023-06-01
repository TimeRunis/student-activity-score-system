package com.sass.studentactivityscoresystem.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sass.studentactivityscoresystem.entity.ActivitySubmit;

import java.util.Map;

public interface ActivitySubmitService extends IService<ActivitySubmit> {
    Page<ActivitySubmit> getByAcidPage(String acId, String current, String size);

    Page<ActivitySubmit> getByUserIdPage(String userId, String current, String size);

    Page<ActivitySubmit> findAll(String current, String size);

    Map<String, Object> countActivitySubmit(String acId);
}
