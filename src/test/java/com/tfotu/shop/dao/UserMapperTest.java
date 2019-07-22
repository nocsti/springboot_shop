package com.tfotu.shop.dao;

import com.tfotu.shop.Utils;
import com.tfotu.shop.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getById() {
        System.out.println(userMapper);
    }

    //   @RunWith @SpringBootTest
    @Test
    public void insert() {
        User user = new User();
        user.setPassword("123456");
        user.setPasswordRandstr("randstr");
        user.setMobile("13345679865");
        user.setName("小精灵");
        user.setAge(0);
        user.setSex(0);
        user.setRegTime( Utils.getTime() );
        userMapper.insert(user);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}