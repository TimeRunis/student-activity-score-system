package com.sass.studentactivityscoresystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {
    private static final String SK="hhhaaaaaaa114514";
    //token过期时间
    private static final Long EXPIRATION=604800L;
    public static String getToken(String userId,int authorityLevel,String userName){
        //创建token
        return JWT.create()
                //写入用户id
                .withAudience(userId)
                //写入用户权限等级
                .withClaim("authorityLevel", authorityLevel)
                //写入用户名
                .withClaim("userName",userName)
                //过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                //签发时间
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SK));
    }
    public static int getUserId(String token){
        DecodedJWT decodedJWT =JWT.decode(token);
        return Integer.parseInt(decodedJWT.getAudience().get(0));
    }
    public static int getLevel(String token){
        DecodedJWT decodedJWT =JWT.decode(token);
        return decodedJWT.getClaim("authorityLevel").asInt();
    }
    public static String getSk(){
        return SK;
    }

    public static boolean checkPermission(String token,int level){
        DecodedJWT decodedJWT =JWT.decode(token);
        return decodedJWT.getClaim("authorityLevel").asInt() >= level;
    }

    public static String getUserName(String token) {
        DecodedJWT decodedJWT =JWT.decode(token);
        return decodedJWT.getClaim("userName").asString();
    }
}
