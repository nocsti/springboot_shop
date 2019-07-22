package com.tfotu.shop.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class UserAddr {
    private Integer id;
    private Integer uid;

    @NotBlank(message = "收货人姓名不能为空！")
    @Length(min = 2,max = 12,message = "收货人姓名长度是：2 - 12个字！")
    private String consignee;

    @NotBlank(message = "收货人手机号不能为空！")
    @Length(min = 11,max = 11,message = "收货人手机号填写错误！")
    private String mobile;

    @NotBlank(message = "收货地址不能为空！")
    @Length(min = 2,max = 12,message = "收货人地址长度是：2 - 128个字！")
    private String address;
    private Integer def;
    private BigInteger ctime;

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

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public BigInteger getCtime() {
        return ctime;
    }

    public void setCtime(BigInteger ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserAddr{" +
                "id=" + id +
                ", uid=" + uid +
                ", consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
