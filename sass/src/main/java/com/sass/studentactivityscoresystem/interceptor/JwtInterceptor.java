package com.sass.studentactivityscoresystem.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.User;
import com.sass.studentactivityscoresystem.mapper.UserMapper;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PrintWriter writer = response.getWriter();;
        RespBody respBody=new RespBody();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if (StringUtils.isBlank(token)) {
            respBody.setResp(401,null,"用户未登录，请登录");
        }else {
            // 获取 token中的userId,检查用户是否存在
            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                respBody.setResp(400,null,"token错误");
                writer.print(respBody.Oj2Json());
                writer.close();
                return true;
            }
            User user = userMapper.selectById(userId);
            if (user == null) {
                respBody.setResp(400,null,"用户不存在，请重新登录");
            }else {
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtUtils.getSk())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    respBody.setResp(401,null,"登录已经失效,请重新登录");
                }
            }
        }
        writer.print(respBody.Oj2Json());
        writer.close();
        return true;
    }
}
