package com.tfotu.shop.entity;

import java.math.BigInteger;

public class Sms {
    private Integer id;
    private Integer utype;
    private Integer uid;
    private String mobile;
    private String code;
    private BigInteger ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigInteger getCtime() {
        return ctime;
    }

    public void setCtime(BigInteger ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", utype=" + utype +
                ", uid=" + uid +
                ", mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
