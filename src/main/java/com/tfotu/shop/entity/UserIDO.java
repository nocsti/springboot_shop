package com.tfotu.shop.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class UserIDO {

    @NotBlank(message = "手机号码不能为空！")
    @Length(min = 11,max = 11, message = "手机号码长度不正确！")
    private String mobile;
    @NotBlank(message = "密码不能为空！")
    private String password;
    @NotBlank(message = "昵称不能为空！")
    @Length(min = 2,max = 12, message = "昵称长度不正确，2 - 12的字符！")
    private String name;
    @NotBlank(message = "短信验证码不能为空！")
    private String smscode;

    @Override
    public String toString() {
        return "UserIDO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }
}
