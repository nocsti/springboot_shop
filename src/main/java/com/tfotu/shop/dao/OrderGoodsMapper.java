package com.tfotu.shop.dao;

import com.tfotu.shop.dto.GoodsDto;
import com.tfotu.shop.entity.Order;
import com.tfotu.shop.entity.OrderGoods;

import java.util.List;

public interface OrderGoodsMapper {
    List<Order> getAll();
    OrderGoods getById(Integer id);
    List<OrderGoods> getGroupOid(Integer oid);
    void insert(OrderGoods orderGoods);
    void update(OrderGoods orderGoods);
    void delete(Integer oid);
}
