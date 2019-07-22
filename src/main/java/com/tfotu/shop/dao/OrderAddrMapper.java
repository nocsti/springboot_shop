package com.tfotu.shop.dao;

import com.tfotu.shop.entity.Order;
import com.tfotu.shop.entity.OrderAddr;

import java.util.List;

public interface OrderAddrMapper {
    List<Order> getAll();
    OrderAddr getById(Integer id);
    OrderAddr getByOid(Integer oid);
    void insert(OrderAddr orderAddr);
    void update(OrderAddr orderAddr);
    void delete(Integer oid);
}
