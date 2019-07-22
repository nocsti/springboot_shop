package com.tfotu.shop.dao;

import com.tfotu.shop.entity.Order;
import com.tfotu.shop.entity.where.OrderWhere;
import com.tfotu.shop.dto.OrderDto;

import java.util.List;

public interface OrderMapper {
    List<Order> getSearch(OrderWhere orderWhere);
    Integer getSearchCount(OrderWhere orderWhere);
    Order getById(OrderWhere orderWhere);
    void insert(Order item);
    void update(Order item);
}
