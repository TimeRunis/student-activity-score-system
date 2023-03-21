package com.sass.studentactivityscoresystem.controller.method;

import com.sass.studentactivityscoresystem.entity.RespBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface GetController {
    RespBody doGet(@RequestParam Map<Object,String> map, HttpServletRequest request);
}
