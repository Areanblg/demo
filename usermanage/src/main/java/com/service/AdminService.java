package com.service;

import com.pojo.Admin;
import com.utils.Result;

import java.util.List;

public interface AdminService {
    void register(Admin admin);


    Result login(Admin admin);

    List<Admin> searchAll();
}
