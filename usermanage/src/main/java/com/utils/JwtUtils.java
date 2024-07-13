package com.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/*
 * JWT的工具类
 */
public class JwtUtils {
    //加密
    private static final String str = "@lo&Abd@112c";

    public static String getToken(JWTCreator.Builder builder) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7); //默认到期时间 7天
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(Algorithm.HMAC256(str));
    }

    //解密
    public static DecodedJWT verify(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(str)).build();
        DecodedJWT verify = verifier.verify(token);
        return verify;
    }

}
