package com.tfotu.shop.dao;

import com.tfotu.shop.dto.CartDto;
import com.tfotu.shop.entity.Cart;
import com.tfotu.shop.entity.where.CartWhere;

import java.util.List;

public interface CartMapper {
    Cart getById(Cart car);
    List<Cart> getSearch(CartWhere cartWhere);
    Integer getSearchCount(CartWhere cartWhere);
    List<Cart> getFormIdStr(CartWhere cartWhere);
    void insert(Cart car);
    void update(Cart car);
    void delete(Cart car);
}
