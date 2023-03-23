package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController {
    @Autowired
    public RespBody rep;
    @Autowired
    public ReturnBody returnBody;


}
