package com.tfotu.shop.dao;


import com.tfotu.shop.entity.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> getAll();
    List<Category> getByPid(Integer pid);
    Category getById(Integer id);
    void insert(Category item);
    void update(Category item);
    void delete(Integer id);
}
