package com.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pojo.Admin;
import com.service.AdminService;
import com.utils.JwtUtils;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("registact")
    public String registact(Admin admin, HttpServletRequest request){
        System.out.println(admin);
        adminService.register(admin);
        request.setAttribute("message","注册成功");
        return "login";
    }

    @PostMapping("loginact")
    @ResponseBody
    public Result loginact(Admin admin, HttpServletRequest request){
        return adminService.login(admin);
    }

    @GetMapping("searchAll")
    @ResponseBody
    public Result searchAll(@RequestHeader("token") String userAgent){
        List<Admin> admins = adminService.searchAll();
        System.out.println("token===================================="+userAgent);
        return Result.success(admins);
    }

    @GetMapping("test")
    @ResponseBody
    public Result test(@RequestHeader("token") String token){
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT verify = null;
        try{
            verify = JwtUtils.verify(token);
        }catch (SignatureVerificationException e){
            map.put("message","token无效");
           return Result.success(map);
        }catch (TokenExpiredException e){
            map.put("message","token过期");
            return Result.success(map);
        }catch (AlgorithmMismatchException e){
            map.put("message","token算法不匹配");
            return Result.success(map);
        }catch (Exception e){
            map.put("message","token无效！！");
            return Result.success(map);
        }
        String username = verify.getClaim("username").asString();
        int userId = verify.getClaim("userId").asInt();
        map.put("token",token);
        map.put("usernaame",username);
        map.put("userId",String.valueOf(userId));

        return Result.success(map);

    }




}
