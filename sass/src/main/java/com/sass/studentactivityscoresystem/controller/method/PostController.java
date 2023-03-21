package com.sass.studentactivityscoresystem.controller.method;

import com.sass.studentactivityscoresystem.entity.RespBody;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PostController {
    RespBody doPost(@RequestBody Map<Object,String> map, HttpServletRequest request);
}
