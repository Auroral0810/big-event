package com.auroral.mapper;

import com.auroral.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户信息
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(String username);

    @Insert("INSERT INTO user (username, password,create_time, update_time" +
            ") VALUES (#{username}, #{password}, NOW(), NOW())")
        // 添加用户信息
    void add(String username, String password);

    @Update("UPDATE user SET nickname = #{nickname}, email = #{email},  update_time = #{updateTime} WHERE id = #{id}")
    void update(User user);

    @Update("UPDATE user SET user_pic = #{avatarUrl} , update_time =NOW() WHERE id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("UPDATE user SET password = #{password}  , update_time =NOW()WHERE id = #{id}")
    void updatePwd(String password, Integer id);
}
