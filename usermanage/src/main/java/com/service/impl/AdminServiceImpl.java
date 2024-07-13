package com.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.dao.AdminMapper;
import com.pojo.Admin;
import com.service.AdminService;
import com.utils.CodeEnum;
import com.utils.JwtUtils;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void register(Admin admin) {
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (dbAdmin!=null){
            return;
        }
        adminMapper.insert(admin);
    }

    @Override
    public Result<String> login(Admin admin) {
        Admin admins = adminMapper.selectByUsernameAndPassword(admin);
        if (admins == null){
            return Result.error("用户名或密码错误");
        }
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("username",admins.getUsername());
        builder.withClaim("userId",admins.getId());
        String token = JwtUtils.getToken(builder);
        return  Result.success(token);
    }

    @Override
    public List<Admin> searchAll() {

        return adminMapper.selectAll();
    }
}
