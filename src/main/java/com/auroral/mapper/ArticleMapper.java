package com.auroral.mapper;


import com.auroral.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("INSERT INTO article (title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "VALUES (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //查询文章列表
    List<Article> list(Integer userId, Integer categoryId, String state);

    //根据id查询文章
    @Select("SELECT * FROM article WHERE id = #{id}")
    Article findById(Integer id);
    //更新文章
    @Update("Update article set title = #{title},content = #{content},cover_img = #{coverImg},state = #{state}," +
            "category_id = #{categoryId},update_time = #{updateTime} where id = #{id}")
    void update(Article article);
    //删除文章
    @Update("DELETE FROM article WHERE id = #{id}")
    void delete(Integer id);
}
