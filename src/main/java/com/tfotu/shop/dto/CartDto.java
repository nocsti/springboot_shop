package com.tfotu.shop.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CartDto {
    Integer id;
    Integer goodsId;
    Integer number;
    Integer check;
    BigInteger uptime;
    BigInteger ctime;

    private String name;
    private String thumb;
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
