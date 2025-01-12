package com.auroral.service;

import com.auroral.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);
    //查询所有分类
    List<Category> list();
}
