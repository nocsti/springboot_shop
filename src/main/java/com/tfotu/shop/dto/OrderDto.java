package com.tfotu.shop.dto;

import com.tfotu.shop.entity.Goods;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class OrderDto {
    private Integer id;
    private String number;
    private Integer uid;
    private BigDecimal total;
    private Integer status;
    private Integer delete;
    private BigInteger ctime;
    private List<OrderGoodsDto> goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public BigInteger getCtime() {
        return ctime;
    }

    public void setCtime(BigInteger ctime) {
        this.ctime = ctime;
    }


    public List<OrderGoodsDto> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderGoodsDto> goods) {
        this.goods = goods;
    }
}
