package com.interceptors;


import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.utils.JwtUtils;
import com.utils.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置响应类型为JSON，字符编码为utf-8
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        // 从请求头中获取token
        String token = request.getHeader("token");
        // 创建结果对象
        Result<Object> objectResult = new Result<>();
        // 判断token是否为空
        if (token == null || token.isEmpty()) {
            // 如果token为空，设置响应的状态码和信息
            objectResult.setCode(401);
            objectResult.setMessage("token不能为空");
            System.out.println("进入到拦截器，token为空");
            // 将结果对象转换为JSON字符串并写入响应
            String jsonStr = JSONUtil.toJsonStr(objectResult);
            response.getWriter().write(jsonStr);
            return false;
        }
        // 创建一个存储验证信息的map
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT verify = null;
        try {
            // 验证token
            verify = JwtUtils.verify(token);
        } catch (SignatureVerificationException e) {
            // token签名无效
            map.put("message", "token无效");
            objectResult.setCode(401);
            objectResult.setMessage("token无效");
            objectResult.setData(map);
            String jsonStr = JSONUtil.toJsonStr(objectResult);
            response.getWriter().write(jsonStr);
            return false;
        } catch (TokenExpiredException e) {
            // token过期
            map.put("message", "token过期");
            objectResult.setCode(401);
            objectResult.setMessage("token过期");
            objectResult.setData(map);
            String jsonStr = JSONUtil.toJsonStr(objectResult);
            response.getWriter().write(jsonStr);
            return false;
        } catch (AlgorithmMismatchException e) {
            // token算法不匹配
            map.put("message", "token算法不匹配");
            objectResult.setCode(401);
            objectResult.setMessage("token算法不匹配");
            objectResult.setData(map);
            String jsonStr = JSONUtil.toJsonStr(objectResult);
            response.getWriter().write(jsonStr);
            return false;
        } catch (Exception e) {
            // 其他异常
            map.put("message", "token无效！！");
            objectResult.setCode(401);
            objectResult.setMessage("token无效");
            objectResult.setData(map);
            String jsonStr = JSONUtil.toJsonStr(objectResult);
            response.getWriter().write(jsonStr);
            return false;
        }
        // 从token中获取用户信息
        Integer userId = verify.getClaim("userId").asInt();
        String username = verify.getClaim("username").asString();
        // 将用户信息存入map
        map.put("userId", userId + "");
        map.put("username", username);
        map.put("message", "认证成功");
        map.put("token", token);
        // 设置结果对象的状态码和数据
        objectResult.setCode(200);
        objectResult.setData(map);
        // 将结果对象转换为JSON字符串并写入响应
        String jsonStr = JSONUtil.toJsonStr(objectResult);
        response.getWriter().write(jsonStr);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
