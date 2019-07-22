package com.tfotu.shop.dao;

import com.tfotu.shop.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> getAll();
    User getById(Integer id);
    User getByMobile(String mobile);
    void insert(User item);
    void insertSelective(User item);
    User update(User item);
    User delete(Integer id);
}
