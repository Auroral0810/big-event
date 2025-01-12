package com.auroral.mapper;

import com.auroral.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增分类
    @Insert("INSERT INTO category (category_name,category_alias,create_user,create_time,update_time) " +
            "VALUES (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查询所有分类
    @Select("SELECT * FROM Category WHERE create_user = #{userId}")
    List<Category> list(Integer userId);
}
