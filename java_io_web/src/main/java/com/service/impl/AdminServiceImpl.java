package com.service.impl;

import com.dao.AdminMapper;
import com.pojo.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Override
    public List<Admin> searchAll() {
        List<Admin> admins = adminMapper.selectAll();

        return admins;
    }
}
