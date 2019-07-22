package com.tfotu.shop.entity.where;

import com.tfotu.shop.control.BaseControl;

public class CartWhere extends BaseWhere {
    private Integer id;
    private Integer uid;
    private Integer check;
    private Integer goodsId;
    private String cartIdStr;

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

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCartIdStr() {
        return cartIdStr;
    }

    public void setCartIdStr(String cartIdStr) {
        this.cartIdStr = cartIdStr;
    }
}
