package com.dao;


import com.pojo.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {
    @Select("select * from t_admin where username = #{username}")
    Admin selectByUsername(@Param("username") String username);

    @Insert("insert into  t_admin(username,password) values (#{username},#{password})")
    int insert(Admin admin);

    @Delete("delete from t_admin where id = #{id}")
    int delete(@Param("id") int id);

    @Select("select * from t_admin where username = #{username} and password = #{password}")
    Admin selectByUsernameAndPassword(Admin admin);

    @Select("select * from t_admin")
    List<Admin> selectAll();
}
