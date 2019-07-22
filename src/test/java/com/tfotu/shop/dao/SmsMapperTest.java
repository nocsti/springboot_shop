package com.tfotu.shop.dao;

import com.tfotu.shop.Utils;
import com.tfotu.shop.entity.Sms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsMapperTest {

    @Autowired
    private SmsMapper smsMapper;

    @Test
    public void insert() {
        Sms sms = new Sms();
        sms.setUtype(0);
        sms.setUid(0);
        sms.setMobile("18680884457");
        sms.setCode("6025");
        sms.setCtime( Utils.getTime() );
        smsMapper.insert(sms);
        System.out.println(sms);
    }

    @Test
    public void demo(){
        System.out.println(smsMapper);
    }
}