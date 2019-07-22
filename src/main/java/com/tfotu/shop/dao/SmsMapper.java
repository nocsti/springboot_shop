package com.tfotu.shop.dao;

import com.tfotu.shop.entity.Sms;

import java.util.List;

public interface SmsMapper {
    List<Sms> getAll();
    Sms getById(Integer id);
    void insert(Sms item);
    Sms update(Sms item);
    Sms delete(Sms item);
}
