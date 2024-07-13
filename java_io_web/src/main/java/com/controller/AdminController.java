package com.controller;


import com.pojo.Admin;
import com.service.AdminService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("all")
    public String all(HttpServletRequest request){
        List<Admin> admins = adminService.searchAll();
        request.setAttribute("admins",admins);
        return "admin";
    }

    @PostMapping("uploadact")
    public void upload(MultipartFile file,HttpServletRequest request) throws IOException {
        //获取真实文件名
        String originalFilename = file.getOriginalFilename();
        String servletPath = request.getContextPath();
        System.out.println(servletPath);

        File dir = new File(servletPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
    }


}
