package com.auroral.mapper;

import com.auroral.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户信息
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(String username);

    @Insert("INSERT INTO user (username, password,create_time, update_time" +
            ") VALUES (#{username}, #{password}, NOW(), NOW())")
    // 添加用户信息
    void add(String username, String password);
}
