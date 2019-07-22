package com.tfotu.shop.entity.where;

public class OrderWhere extends BaseWhere {
    private Integer id;
    private Integer uid;
    private String word;
    private Integer status;
    private Integer delete;
    private Integer payType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer oid) {
        this.id = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getWord() {
        return word;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
