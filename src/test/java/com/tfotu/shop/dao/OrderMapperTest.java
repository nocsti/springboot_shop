package com.tfotu.shop.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void getAll() {
        System.out.println(categoryMapper);
        System.out.println(goodsMapper);
        System.out.println(orderMapper);
    }

    @Test
    public void getSearch() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }
}