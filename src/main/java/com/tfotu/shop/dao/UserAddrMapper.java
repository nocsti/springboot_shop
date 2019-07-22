package com.tfotu.shop.dao;

import com.tfotu.shop.entity.UserAddr;

import java.util.List;

public interface UserAddrMapper {
    List<UserAddr> getAll(Integer uid);
    UserAddr getById(UserAddr userAddr);
    UserAddr getDef(Integer uid);
    void insert(UserAddr item);
    void update(UserAddr item);
    void delete(UserAddr addr);
    void updateSetDef(UserAddr addr);
    void updateSetUndef(UserAddr addr);
}
