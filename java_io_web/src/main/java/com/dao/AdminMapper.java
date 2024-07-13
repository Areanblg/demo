package com.dao;


import com.pojo.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {
    @Select("select * from t_admin;")
    List<Admin> selectAll();
}
