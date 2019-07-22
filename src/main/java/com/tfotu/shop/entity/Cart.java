package com.tfotu.shop.entity;

import java.math.BigInteger;

public class Cart {
    Integer id;
    Integer uid;
    Integer goodsId;
    Integer number;
    Integer check;
    BigInteger uptime;
    BigInteger ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigInteger getCtime() {
        return ctime;
    }

    public void setCtime(BigInteger ctime) {
        this.ctime = ctime;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public BigInteger getUptime() {
        return uptime;
    }

    public void setUptime(BigInteger uptime) {
        this.uptime = uptime;
    }
}
