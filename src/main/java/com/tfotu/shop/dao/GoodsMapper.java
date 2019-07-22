package com.tfotu.shop.dao;


import com.tfotu.shop.entity.Goods;
import com.tfotu.shop.entity.where.GoodsWhere;

import java.util.List;

public interface GoodsMapper {
    List<Goods> getAll();
    List<Goods> getSearch(GoodsWhere goodsSearch);
    Integer getSearchNumber(GoodsWhere goodsSearch);
    List<Goods> getFormIdStr(GoodsWhere goodsSearch);
    Goods getById(Integer id);
    void insert(Goods goods);
    void update(Goods goods);
    void delete(Integer id);
}
